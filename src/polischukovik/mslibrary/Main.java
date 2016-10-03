package polischukovik.mslibrary;

import java.io.IOException;
import java.util.Set;

import polischukovik.domain.Question;
import polischukovik.domain.QuestionRaw;
import polischukovik.domain.Test;

public class Main {
	
	//THe number of resulting variants
	private static int VARIANTS= 4;
	//The number of questions in each variant
	private static int QUESTIONS = 5;
	//Question mark
	private static String MARK = "&";
	//Flag to mix answers. Default true
	private static boolean MIX_ANSWERS = true;
	//output
	private static String FILENAME = "tests.docx";
	//test name
	private static String TEST_NAME = "Теорія держави і права";
	
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
		
	
		questions = QuestioRawnHandler.parseSource(sourceFilePath, prop);
		
		TestFactory tf = new TestFactory(questions);
		 
		Test test = tf.createTests(prop);
		
		
		System.err.println(test);
		//DocumentCompieler dc = new DocumentCompieler(questions);
		
		//dc.compile(prop);
		
		
	}


}
