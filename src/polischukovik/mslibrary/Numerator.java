package polischukovik.mslibrary;

import java.util.TreeMap;

import javax.naming.directory.InvalidAttributesException;

import org.apache.poi.ss.usermodel.PrintOrientation;

public class Numerator {
	public static enum TYPE {
		NUMERIC,
		ALPHABETIC,
		ALPHABETIC_CAPS,
		ROMAN
	}
	private TYPE type;
	private int cnt;
	private final static TreeMap<Integer, String> romanDictMap = new TreeMap<>();
	private final static TreeMap<String, TYPE> namingMap = new TreeMap<>();
	static {

        romanDictMap.put(1000, "M");
        romanDictMap.put(900, "CM");
        romanDictMap.put(500, "D");
        romanDictMap.put(400, "CD");
        romanDictMap.put(100, "C");
        romanDictMap.put(90, "XC");
        romanDictMap.put(50, "L");
        romanDictMap.put(40, "XL");
        romanDictMap.put(10, "X");
        romanDictMap.put(9, "IX");
        romanDictMap.put(5, "V");
        romanDictMap.put(4, "IV");
        romanDictMap.put(1, "I");
        
        namingMap.put("ROMAN", Numerator.TYPE.ROMAN);
        namingMap.put("NUMERIC", Numerator.TYPE.NUMERIC);
        namingMap.put("ALPHABETIC", Numerator.TYPE.ALPHABETIC);
        namingMap.put("ALPHABETIC_CAPS", Numerator.TYPE.ALPHABETIC_CAPS);	
    }
	
	public Numerator(TYPE type) {
		super();
		this.type = type;
		cnt = 0;
	}
	
	public String getNext(){
		switch (type) {
		case ROMAN:
			return nextGreece();
		case NUMERIC:
			return nextInt();
		case ALPHABETIC:
			return nextLetter();
		case ALPHABETIC_CAPS:
			return nextLetter().toUpperCase();

		default:
			return "0";
		}
	}

	private String nextInt(){
		return String.valueOf(++cnt);		
	}
	
	private String nextLetter() {
		int first = 96;
		++cnt;
		
		return String.valueOf(Character.toChars(first + cnt));
	}
	
	private String nextGreece(){
		return toRoman(++cnt);		
	}

    private final static String toRoman(int number) {
        int l =  romanDictMap.floorKey(number);
        if ( number == l ) {
            return romanDictMap.get(number);
        }
        return romanDictMap.get(l) + toRoman(number-l);
    }
    
    public static TYPE valueOf(String str) throws InvalidAttributesException{ 
    	TYPE type = namingMap.get(str);
    	
    	if(type == null) throw new InvalidAttributesException(String.format("Unaccepable value %s.", str));
    	
    	return type;		
    }

}
