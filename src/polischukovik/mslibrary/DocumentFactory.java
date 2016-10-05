package polischukovik.mslibrary;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.BreakType;
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
import polischukovik.mslibrary.Properties.NAMES;

public class DocumentFactory {
	private Test test;
	XWPFDocument doc;
	Properties prop;
	
	String pMark;
	String pQuestionPunctuation;
	boolean pFQuestionBold;
	String pAnswerPuncuation;

	public DocumentFactory(Test test, Properties prop) {
		this.test = test;
		this.prop = prop;
		try {
			pMark = prop.get(Properties.NAMES.VARIANT_NAME);
			pQuestionPunctuation = prop.get(NAMES.QUESTION_PUNCTUATION);
			pFQuestionBold = true;
			boolean bold = true;
			if(paramBold != null && (paramBold.toLowerCase().equals("y") || (paramBold.toLowerCase().equals("n")))){
				bold = "y".equals(paramBold.toLowerCase());
			}	
			pFQuestionBold = Boolean.valueOf(prop.get(Properties.NAMES.F_QUESTION_BOLD));
			pAnswerPuncuation
		}catch (Exception e) {
			e.printStackTrace();
			return;
		}
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
				boolean bold = true;
				String paramBold = ;
				if(paramBold != null && (paramBold.toLowerCase().equals("y") || (paramBold.toLowerCase().equals("n")))){
					bold = "y".equals(paramBold.toLowerCase());
				}				
				questionRun.setBold(bold);
				
				List<Answer> answers = q.getAnswers();		
				XWPFParagraph answerParagraph = doc.createParagraph();
				/*
				 * Remove spacing between paragraphs
				 */
				String paramSpacing = prop.get(Properties.NAMES.F_QUESTION_SPACING);
				if(paramSpacing != null && paramSpacing.toLowerCase().equals("y")){
					setSingleLineSpacing(answerParagraph);
				}
				
				for(int i = 0; i < answers.size(); i++){
					Answer a = answers.get(i);
					XWPFRun answerRun = answerParagraph.createRun();
					
					answerRun.setText(String.format("%s%s %s", a.getLabel(), prop.get(NAMES.ANSWER_PUNCTUATION), a.getAnswer()));
					
					if(i != answers.size() - 1){
						answerRun.addBreak();
					}					
				}
			}			
			
			
		}
	}
	
	public void setSingleLineSpacing(XWPFParagraph para) {
	    CTPPr ppr = para.getCTP().getPPr();
	    if (ppr == null) ppr = para.getCTP().addNewPPr();
	    CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
	    spacing.setAfter(BigInteger.valueOf(0));
	    spacing.setBefore(BigInteger.valueOf(0));
	    spacing.setLineRule(STLineSpacingRule.AUTO);
	    spacing.setLine(BigInteger.valueOf(240));
	}

	
	

}