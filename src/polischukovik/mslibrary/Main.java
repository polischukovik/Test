package polischukovik.mslibrary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import polischukovik.domain.Question;
import polischukovik.domain.QuestionRaw;
import polischukovik.domain.Test;
import polischukovik.domain.enums.PropertyNames;

public class Main {
	
	private static final String ANSWER_PUNCTUATION = ")";
	private static final String QUESTION_PUNCTUATION = ".";
	//THe number of resulting variants
	private static final int VARIANTS= 4;
	//The number of questions in each variant
	private static final int QUESTIONS = 30;
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
	private static final String SHUFFLE_QUESTIONS = "y";
	private static final String SHUFFLE_ANSWERS = "y";
	private static final String T_KEY_TITLE = "Ключі";

	
	private static List<QuestionRaw> questions;
	
	//C:\Users\opolishc\workspaceNeon\ms-document-testimg
	private static String sourceFilePath = "source_.txt";

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		prop.add(PropertyNames.PARSING_MARK_QUESTION, MARK);
		prop.add(PropertyNames.BASIC_VARIANTS, String.valueOf(VARIANTS));
		prop.add(PropertyNames.BASIC_QUESTIONS, String.valueOf(QUESTIONS));
		prop.add(PropertyNames.IO_SOURCE_FILE_NAME, FILENAME);
		prop.add(PropertyNames.RES_TEST_NAME, TEST_NAME);
		prop.add(PropertyNames.P_PUNCTUATION_ANSWER, ANSWER_PUNCTUATION);
		prop.add(PropertyNames.P_PUNCTUATION_QUESTION, QUESTION_PUNCTUATION);
		prop.add(PropertyNames.RES_VARIANT_NAME, VARIANT_NAME);
		prop.add(PropertyNames.F_QUESTION_BOLD, F_QUESTION_BOLD);
		prop.add(PropertyNames.F_QUESTION_SPACING, F_QUESTION_SPACING);
		prop.add(PropertyNames.S_NUMERATION_VARIANT, P_VARIANT_NUMERATION);
		prop.add(PropertyNames.S_NUMERATION_QUESTION, P_QUESTION_NUMERATION);
		prop.add(PropertyNames.S_ANSWER_NUMERATION, P_ANSWER_NUMERATION);
		prop.add(PropertyNames.SHUFFLE_ANSWERS, SHUFFLE_ANSWERS);
		prop.add(PropertyNames.SHUFFLE_QUESTION, SHUFFLE_QUESTIONS);
		prop.add(PropertyNames.RES_KEY_TITLE, T_KEY_TITLE);
	
		questions = QuestioRawnHandler.parseSource(sourceFilePath, prop);
		
		TestFactory tf = new TestFactory(questions);
		 
		Test test = tf.createTest(prop);
		
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