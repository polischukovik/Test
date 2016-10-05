package polischukovik.mslibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.naming.directory.InvalidAttributesException;

import polischukovik.domain.QuestionRaw;
import polischukovik.domain.Test;
import polischukovik.domain.Variant;
import polischukovik.mslibrary.Numerator.TYPE;

public class TestFactory {
	Set<QuestionRaw> questions;
	private static Properties prop;
	
	private String pTestName;
	private int pVariants;

	public TestFactory(Set<QuestionRaw> questions) {
		this.questions = questions;
	}

	public Test createTests(Properties prop) {
		TestFactory.prop = prop;
		try {
			pTestName = TestFactory.prop.get(Properties.NAMES.TEST_NAME);
			pVariants = Integer.valueOf(TestFactory.prop.get(Properties.NAMES.VARIANTS));
		}catch (NumberFormatException e) {
			System.err.println("ERROR: Failed to convert property to numeric");
			return null;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		Test test = new Test(pTestName);
		List<QuestionRaw> sorted = new ArrayList<>(questions);
		/*
		 * Set variant numeration style
		 */
		Numerator nums = new Numerator(getNumerationStyle(Numerator.TYPE.ROMAN, Properties.NAMES.P_VARIANT_NUMERATION));
		
		long seed = System.nanoTime();
		List<QuestionRaw> newVariant = new ArrayList<>(sorted);
		
		for(int i = 0; i < pVariants; i++){			
			Collections.shuffle(newVariant, new Random(seed));
			
			Variant v = new Variant(nums.getNext(), newVariant);
			test.add(v);
		}
		return test;
	}
	
	public static Properties getProperties(){
		return prop;
	}
	
	public static Numerator.TYPE getNumerationStyle(Numerator.TYPE defaultStyle, Properties.NAMES property) {
		Numerator.TYPE numStyle = defaultStyle; //DEFAULT
		try{
			String propNumerationStyle = TestFactory.getProperties().get(property);
			if(propNumerationStyle == null){
				throw new IllegalArgumentException(String.format("Warning: There is no property %s. Using default",property));
			}
			Numerator.TYPE tmpNumerationStyle = Numerator.valueOf(propNumerationStyle); //throws IllegalArgumentException
			
			numStyle = tmpNumerationStyle;
			
		}catch(IllegalArgumentException e){
			System.err.println(String.format("Warning: The property %s has inaccepable value. Using default",property));
			System.err.println(String.format(" Reason:",e.getMessage()));
		}
		catch(InvalidAttributesException e){		
			System.err.println(String.format("Warning: There is no property %s. Using default",property));
			System.err.println(String.format(" Reason:",e.getMessage()));
		}
		return numStyle;
	}

}
