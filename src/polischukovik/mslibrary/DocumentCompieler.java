package polischukovik.mslibrary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import polischukovik.domain.Question;

public class DocumentCompieler {
	
	List<Question> questionList;
	Set<Question> questions;
	Properties prop;
	long seed;

	public DocumentCompieler(Set<Question> questions) {
		this.questions = questions;
	}
	
	public void compile(Properties prop){
		this.prop = prop;		
		seed = System.nanoTime();
		questionList = new ArrayList(questions);
		XWPFDocument doc = generateTest();
		
		try(FileOutputStream fos = new FileOutputStream(new File(prop.get(Properties.NAMES.OUTPUT_FILE_NAME)))){
			doc.write(fos);
		} catch (IOException e) {
			System.err.println("Cannot write file");
			System.err.println(e.getMessage());
		}
		
	}
	
	private XWPFDocument generateTest(){
		int pVariants = Integer.valueOf(prop.get(Properties.NAMES.VARIANTS));
		
		//Create documment here and add PAGES
		XWPFDocument multipleVariants = new XWPFDocument();
		multipleVariants.createParagraph();
		
		for(int i = 0; i < pVariants; i++){
			generateSingleVariant();
		}
		return null;
	}

	private XWPFDocument generateSingleVariant() {
		int pQestions = Integer.valueOf(prop.get(Properties.NAMES.QUESTIONS));
		
		if(pQestions > questionList.size()){
			System.out.println("Warning: Number of questions for each variant provided exceeds the available question number");
			System.out.println("\t Eventual number would be limmited to available number");
		}
		
		Collections.shuffle(questionList, new Random(seed));
		
		XWPFDocument variant = new XWPFDocument();
		List<XWPFDocument> docList = Document.compose(questionList);
		for(int i = 0; i < docList.size(); i++){
			XWPFParagraph p = variant.createParagraph();
			XWPFParagraph n = docList.get(i).getLastParagraph();
			variant.setParagraph(n, variant.getPosOfParagraph(p));
		}
		
		return variant;
		// TODO Auto-generated method stub
		
	}

}
