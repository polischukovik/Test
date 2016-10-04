package polischukovik.mslibrary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

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
	private static final String TEST_NAME = "Тести для групи продовженого дня ЫИПРАТ";
	
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
		
	
		questions = QuestioRawnHandler.parseSource(sourceFilePath, prop);
		
		TestFactory tf = new TestFactory(questions);
		 
		Test test = tf.createTests(prop);
		
		DocumentFactory df = new DocumentFactory(test, prop);

		OutputStream os = new FileOutputStream(new File("file.docx"));
		df.write(os);
		
		
		System.err.println("done");
		//DocumentCompieler dc = new DocumentCompieler(questions);
		
		//dc.compile(prop);
		
		
	}


}
