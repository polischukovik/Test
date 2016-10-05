package polischukovik.mslibrary;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.directory.InvalidAttributesException;

import polischukovik.mslibrary.Numerator.TYPE;

public class Properties {
	
	public static enum NAMES {VARIANTS,QUESTIONS,MARK,MIX_ANSWERS, OUTPUT_FILE_NAME, TEST_NAME, QUESTION_PUNCTUATION, ANSWER_PUNCTUATION, VARIANT_NAME, F_QUESTION_BOLD, F_QUESTION_SPACING, P_VARIANT_NUMERATION, P_QUESTION_NUMERATION, P_ANSWER_NUMERATION};
	private Map<NAMES, String> properties;
//	private final static TreeMap<String, NAMES> namingMap = new TreeMap<>();
//	
//	static{
//		namingMap.put("VARIANTS", Properties.NAMES.VARIANTS);
//		namingMap.put("QUESTIONS", Properties.NAMES.QUESTIONS);
//		namingMap.put("MARK", Properties.NAMES.MARK);
//		namingMap.put("MIX_ANSWERS", Properties.NAMES.MIX_ANSWERS);
//		namingMap.put("OUTPUT_FILE_NAME", Properties.NAMES.OUTPUT_FILE_NAME);
//		namingMap.put("TEST_NAME", Properties.NAMES.TEST_NAME);
//		namingMap.put("QUESTION_PUNCTUATION", Properties.NAMES.QUESTION_PUNCTUATION);
//		namingMap.put("ANSWER_PUNCTUATION", Properties.NAMES.ANSWER_PUNCTUATION);
//		namingMap.put("VARIANT_NAME", Properties.NAMES.VARIANT_NAME);
//		namingMap.put("F_QUESTION_BOLD", Properties.NAMES.F_QUESTION_BOLD);
//		namingMap.put("F_QUESTION_SPACING", Properties.NAMES.F_QUESTION_SPACING);
//		namingMap.put("P_VARIANT_NUMERATION", Properties.NAMES.P_VARIANT_NUMERATION);
//		namingMap.put("P_QUESTION_NUMERATION", Properties.NAMES.P_QUESTION_NUMERATION);
//		namingMap.put("P_ANSWER_NUMERATION", Properties.NAMES.P_ANSWER_NUMERATION);
//		
//	}
	
	public Properties() {
		super();
		this.properties = new HashMap<>();
	}

	public void add(NAMES nameEnum, String value){
		properties.put(nameEnum, value);
	}

	public String get(NAMES nameEnum) throws InvalidAttributesException{	
		String value = properties.get(nameEnum);
		
		if(value == null) throw new InvalidAttributesException(String.format("Option [%s] value not found.", nameEnum.toString()));
		
		return value;
		
	}

	public static void loadDefaultProperties() {
		// TODO Auto-generated method stub
		
	}
}