package polischukovik.mslibrary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import polischukovik.domain.Question;
import polischukovik.domain.QuestionRaw;
import polischukovik.domain.Test;

public class Main {
	
	private static final String ANSWER_PUNCTUATION = ")";
	private static final String QUESTION_PUNCTUATION = ".";
	//THe number of resulting variants
	private static final int VARIANTS= 4;
	//The number of questions in each variant
	private static final int QUESTIONS = 5;
	//Question mark
	private static final String MARK = "&";
	//Flag to mix answers. Default true
	private static final boolean MIX_ANSWERS = true;
	//output
	private static final String FILENAME = "tests.docx";
	//test name
	private static final String TEST_NAME = "Назва тесту";
	private static final String VARIANT_NAME = "Variant";
	
	private static Set<QuestionRaw> questions;
	
	//C:\Users\opolishc\workspaceNeon\ms-document-testimg
	private static String sourceFilePath = "source.txt";

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		prop.add(Properties.NAMES.MARK, MARK);
		prop.add(Properties.NAMES.VARIANTS, String.valueOf(VARIANTS));
		prop.add(Properties.NAMES.QUESTIONS, String.valueOf(QUESTIONS));
		prop.add(Properties.NAMES.MIX_ANSWERS, MIX_ANSWERS ? "1" : "0");
		prop.add(Properties.NAMES.OUTPUT_FILE_NAME, FILENAME);
		prop.add(Properties.NAMES.TEST_NAME, TEST_NAME);
		prop.add(Properties.NAMES.ANSWER_PUNCTUATION, ANSWER_PUNCTUATION);
		prop.add(Properties.NAMES.QUESTION_PUNCTUATION, QUESTION_PUNCTUATION);
		prop.add(Properties.NAMES.VARIANT_NAME, VARIANT_NAME);
		
	
		questions = QuestioRawnHandler.parseSource(sourceFilePath, prop);
		
		TestFactory tf = new TestFactory(questions);
		 
		Test test = tf.createTests(prop);
		
		DocumentFactory df = new DocumentFactory(test, prop);
//

		try(OutputStream os = new FileOutputStream(new File("file.docx"))){
		
			df.write(os);
//			XWPFDocument doc = new XWPFDocument();
//			
//			XWPFParagraph p = doc.createParagraph();
//			p.setAlignment(ParagraphAlignment.CENTER);
//			XWPFRun r = p.createRun();
//			r.addBreak();
//			r.addBreak();
//			r.addBreak();
//			r.addBreak();
//			r.setText(TEST_NAME);		
//			r.addBreak();		
//			
//			//r.addBreak(BreakType.PAGE);
//			
//			//////////////////////////
//			//createVariantTitle(doc);
//			/////////////////////////
//			XWPFParagraph p1 = doc.createParagraph();
//			XWPFRun r1 = p1.createRun();
//			p1.setPageBreak(true);
//			
//			String q_label = "1";
//			String question = "What is your question?";
//			r1.setText(String.format("%s%s %s", q_label, ".", question));
//			
//			XWPFParagraph p3 = doc.createParagraph();
//			XWPFRun r3 = p3.createRun();
//			
//			String l_num = "1";
//			String l_answ = "Yes";
//			r3.setText(String.format("%s%s %s", l_num, ")", l_answ));
//			r3.addBreak();
//			
//			//XWPFParagraph p4 = doc.createParagraph();
//			XWPFRun r4 = p3.createRun();
//			
//			String l_num1 = "2";
//			String l_answ1 = "No";
//			r4.setText(String.format("%s%s %s", l_num1, ")", l_answ1));
//			r4.addBreak();
//			///////
//			
//			XWPFParagraph p2 = doc.createParagraph();
//			XWPFRun r2 = p2.createRun();
//			//p2.setPageBreak(false);
//			
//			String q_label2 = "2";
//			String question2 = "What is your question?";
//			r2.setText(String.format("%s%s %s", q_label2, ".", question2));
//			
//			doc.write(os);
		}catch(Exception e){
			System.err.println("Error occured");
			e.printStackTrace();
			return;
		}
		System.err.println("done");
	
	}

	


}
