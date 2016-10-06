package polischukovik.mslibrary;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

import polischukovik.domain.Answer;
import polischukovik.domain.Question;
import polischukovik.domain.Test;
import polischukovik.domain.Variant;
import polischukovik.domain.enums.PropertyNames;

public class DocumentFactory {
	private Test test;
	XWPFDocument doc;
	Properties prop;
	
	String pMark;
	String pQuestionPunctuation;
	boolean pFQuestionBold;
	String pAnswerPuncuation;
	boolean pQuestionSpacing;

	public DocumentFactory(Test test, Properties prop) {
		this.test = test;
		this.prop = prop;
		
		pMark = prop.get(PropertyNames.RES_VARIANT_NAME, "Variant");
		pQuestionPunctuation = prop.get(PropertyNames.P_PUNCTUATION_QUESTION, ".");
		pFQuestionBold = prop.getBoolean(PropertyNames.F_QUESTION_BOLD, false);			
		pAnswerPuncuation = prop.get(PropertyNames.P_PUNCTUATION_ANSWER, ")");	
		pQuestionSpacing = prop.getBoolean(PropertyNames.F_QUESTION_SPACING, false);
		
		createDocument();
	}

	public void write(OutputStream os) throws IOException {
		if(doc == null){
			System.err.println("document is not created");
			return;
		}
		doc.write(os);		
	}
	
	public XWPFDocument createDocument(){
		doc = new XWPFDocument();
		
		addDocumentTite();
		addVariants();		
		addKeys();
		
		return doc;
	}

	private void addDocumentTite() {
		XWPFParagraph p = doc.createParagraph();
		p.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r = p.createRun();
		r.addBreak();
		r.addBreak();
		r.addBreak();
		r.addBreak();
		r.setText(test.getCaption());		
		r.addBreak();
	}
	
	private void addVariantCaption(Variant v) {
		XWPFParagraph p0 = doc.createParagraph();
		XWPFRun r0 = p0.createRun();
		p0.setAlignment(ParagraphAlignment.CENTER);
		p0.setPageBreak(true);
		
		String vLabel = v.getName();		
		r0.setText(String.format("%s %s", pMark, vLabel));
		r0.setBold(true);
	}

	private void addVariants() {
		List<Variant> variants = test.getVariants();		
		
		for(Variant v : variants){
			addVariantCaption(v);
						
			List<Question> questions = v.getQuestions();
			for(Question q : questions){
				XWPFParagraph questionParagpaph = doc.createParagraph();

				setSingleLineSpacing(questionParagpaph);
				//questionParagpaph.setAlignment(ParagraphAlignment.CENTER);
				//questionParagpaph.setPageBreak(true);
				
				XWPFRun questionRun = questionParagpaph.createRun();
				questionRun.setText(String.format("%s%s %s",q.getId(), pQuestionPunctuation, q.getQuestion()));
				/*
				 * Set question Run to bold if parameter presented
				 */
							
				questionRun.setBold(pFQuestionBold);
				
				List<Answer> answers = q.getAnswers();		
				XWPFParagraph answerParagraph = doc.createParagraph();
				/*
				 * Remove spacing between paragraphs
				 */
				if(pQuestionSpacing){
					setSingleLineSpacing(answerParagraph);
				}

				for(int i = 0; i < answers.size(); i++){
					Answer a = answers.get(i);
					XWPFRun answerRun = answerParagraph.createRun();
					
					answerRun.setText(String.format("%s%s %s", a.getLabel(), pAnswerPuncuation, a.getAnswer()));
					
					if(i != answers.size() - 1){
						answerRun.addBreak();
					}	
				}
			}						
		}
	}	

	private void addKeys() {
		//Add title
		XWPFParagraph p = doc.createParagraph();
		p.setAlignment(ParagraphAlignment.CENTER);
		p.setPageBreak(true);
		XWPFRun r = p.createRun();
		
		r.setText(prop.get(PropertyNames.RES_KEY_TITLE, "Key title"));
		
		//Add keys for each variants
		for(Variant v : test.getVariants()){
			XWPFParagraph pVariant = doc.createParagraph();
			XWPFRun rVariant = pVariant.createRun();
			
			rVariant.setText(String.format("%s", v.getName()));
			
			XWPFParagraph pQuestion = doc.createParagraph();
			
			//Create row for each answer
			for(Question q : v.getQuestions()){
				XWPFRun rQuestion = pQuestion.createRun();
				
				rQuestion.setText(String.format("%s %s %s", q.getId(), prop.get(PropertyNames.P_PUNCTUATION_KEY_ANSWER, "-"), v.getKeys().get(q)));
				rQuestion.addBreak();
			}
		}
	}
	
	private void setSingleLineSpacing(XWPFParagraph para) {
	    CTPPr ppr = para.getCTP().getPPr();
	    if (ppr == null) ppr = para.getCTP().addNewPPr();
	    CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
	    spacing.setAfter(BigInteger.valueOf(0));
	    spacing.setBefore(BigInteger.valueOf(0));
	    spacing.setLineRule(STLineSpacingRule.AUTO);
	    spacing.setLine(BigInteger.valueOf(240));
	}
}