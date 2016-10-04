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
		
		addTite(test.getCaption());
		addVariants();		
		
		return doc;
	}

	private void addTite(String string) {
		XWPFParagraph p = doc.createParagraph();
		XWPFRun r = p.createRun();
		
		r.setText(string);
		r.addBreak(BreakType.PAGE);
	}

	private void addVariants() {
		List<Variant> variants = test.getVariants();
		
		
		for(Variant v : variants){
			XWPFParagraph p = doc.createParagraph();
			XWPFRun r = p.createRun();
			//Create Variant caption			
			p.setAlignment(ParagraphAlignment.CENTER);
			r.setText(v.getName());
			
			List<Question> questions = v.getQuestions();
			for(Question q : questions){
				XWPFParagraph pv = doc.createParagraph();
				XWPFRun rv = p.createRun();
				pv = doc.createParagraph();
				rv = p.createRun();
				
				List<Answer> answers = q.getAnswers();
				
				rv.setText(String.format("%s%s\t%s",q.getId(), prop.get(NAMES.QUESTION_PUNCTUATION), q.getQuestion()));
				
				for(Answer a : answers){
					XWPFParagraph pa = doc.createParagraph();
					XWPFRun ra = p.createRun();
					
					pa = doc.createParagraph();
					ra = p.createRun();
					
					ra.setText(String.format("%s%s\t%s",a.getLabel(), prop.get(NAMES.ANSWER_PUNCTUATION), a.getAnswer()));
				}
			}			
			
			//get last run and add page break to indicte end of vatiant
			p.createRun().addBreak(BreakType.PAGE);			
			
		}
	}
	
	

}
