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
	private static final String VARIANT_NAME = "Варіант";
	private static final String F_QUESTION_BOLD = "Y";
	private static final String F_QUESTION_SPACING = "y";
	private static final String P_VARIANT_NUMERATION = "ROMAN";
	private static final String P_QUESTION_NUMERATION = "NUMERIC";
	private static final String P_ANSWER_NUMERATION = "ALPHABETIC";
	
	
	private static Set<QuestionRaw> questions;
	
	//C:\Users\opolishc\workspaceNeon\ms-document-testimg
	private static String sourceFilePath = "source_.txt";

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
		prop.add(Properties.NAMES.F_QUESTION_BOLD, F_QUESTION_BOLD);
		prop.add(Properties.NAMES.F_QUESTION_SPACING, F_QUESTION_SPACING);
//		prop.add(Properties.NAMES.P_VARIANT_NUMERATION, P_VARIANT_NUMERATION);
//		prop.add(Properties.NAMES.P_QUESTION_NUMERATION, P_QUESTION_NUMERATION);
//		prop.add(Properties.NAMES.P_ANSWER_NUMERATION, P_ANSWER_NUMERATION);
		
	
		questions = QuestioRawnHandler.parseSource(sourceFilePath, prop);
		
		TestFactory tf = new TestFactory(questions);
		 
		Test test = tf.createTests(prop);
		
		if(test == null){
			System.err.println("Failed to generate test. Exiting");
			return;
		}
		
		DocumentFactory df = new DocumentFactory(test, prop);

		try(OutputStream os = new FileOutputStream(new File("file.docx"))){
		
			df.write(os);

		}catch(Exception e){
			System.err.println("Error occured");
			e.printStackTrace();
			return;
		}
		
		System.err.println("done");
	
	}

	


}