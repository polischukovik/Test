package polischukovik.mslibrary;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import polischukovik.domain.Answer;
import polischukovik.domain.Question;
import polischukovik.domain.Test;
import polischukovik.domain.Variant;
import polischukovik.mslibrary.Properties.NAMES;

public class DocumentFactory {
	private Test test;
	XWPFDocument doc;
	Properties prop;

	public DocumentFactory(Test test, Properties prop) {
		this.test = test;
		this.prop = prop;
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
		
		String v_label = v.getName();
		String v_mark = prop.get(Properties.NAMES.VARIANT_NAME);
		r0.setText(String.format("%s %s", v_mark, v_label));
		r0.setBold(true);
	}

	private void addVariants() {
		List<Variant> variants = test.getVariants();		
		
		for(Variant v : variants){
			addVariantCaption(v);
						
			List<Question> questions = v.getQuestions();
			for(Question q : questions){
				XWPFParagraph questionParagpaph = doc.createParagraph();
				questionParagpaph.setAlignment(ParagraphAlignment.CENTER);
				//questionParagpaph.setPageBreak(true);
				
				XWPFRun questionRun = questionParagpaph.createRun();
							
				questionRun.setText(String.format("%s%s %s",q.getId(), prop.get(NAMES.QUESTION_PUNCTUATION), q.getQuestion()));
				
				List<Answer> answers = q.getAnswers();		
				XWPFParagraph answerParagraph = doc.createParagraph();
				
				for(Answer a : answers){
					XWPFRun answerRun = answerParagraph.createRun();
					
					answerRun.setText(String.format("%s%s %s", a.getLabel(), prop.get(NAMES.ANSWER_PUNCTUATION), a.getAnswer()));
					answerRun.addBreak();
				}
			}			
			
			
		}
	}

	
	

}
