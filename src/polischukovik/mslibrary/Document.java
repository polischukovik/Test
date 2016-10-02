package polischukovik.mslibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

import polischukovik.domain.Question;

public class Document {
	public void createDocument() throws IOException{
		   //Blank Document
		   XWPFDocument document= new XWPFDocument(); 
		   //Write the Document in file system
		   FileOutputStream out = new FileOutputStream(
		   new File("createparagraph.docx"));
		        
		   //create Paragraph
		   XWPFParagraph paragraph = document.createParagraph();
		   XWPFRun run=paragraph.createRun();
		   run.setText("At tutorialspoint.com, we strive hard to " +
		   "provide quality tutorials for self-learning " +
		   "purpose in the domains of Academics, Information " +
		   "Technology, Management and Computer Programming Languages.");

		   paragraph = document.createParagraph();
		   run=paragraph.createRun();
		   run.setText("At tutorialspoint.com, we strive hard to " +
		   "provide quality tutorials for self-learning " +
		   "purpose in the domains of Academics, Information " +
		   "Technology, Management and Computer Programming Languages.");
		   
		   document.write(out);
		   out.close();
		   System.out.println("createparagraph.docx written successfully");
		   
	}

	public static List<XWPFDocument> compose(List<Question> questionList) {
		List<XWPFDocument> singleQuestionDocuments = new ArrayList<XWPFDocument>();
		
		
		for(Question q : questionList){
			singleQuestionDocuments.add(composeSingleQuestionParagraph(q));
		}
		 
		return singleQuestionDocuments;
	}

	private static XWPFDocument composeSingleQuestionParagraph(Question q) {
		XWPFDocument singleQuestion = new XWPFDocument();
		XWPFParagraph p = singleQuestion.createParagraph();
		XWPFRun questionRun = p.createRun();
		//Add question itserf
		//questionRun.setText(q.getQuestion());
		questionRun.addBreak();
		
		//String[] answers = q.getAnswers();
		
//		for(int i = 0; i < answers.length; i++){
//			XWPFRun r = p.createRun();
//			r.setText(answers[i]);
//		}
		
		return singleQuestion;
	}
}
