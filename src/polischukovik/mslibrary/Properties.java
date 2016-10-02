package polischukovik.mslibrary;

import java.util.HashMap;
import java.util.Map;

public class Properties {
	
	public static enum NAMES {VARIANTS,QUESTIONS,MARK,MIX_ANSWERS, OUTPUT_FILE_NAME, TEST_NAME};
	private Map<NAMES, String> properties;
	
	public Properties() {
		super();
		this.properties = new HashMap<>();
	}

	public void add(NAMES nameEnum, String value){
		properties.put(nameEnum, value);
	}

	public String get(NAMES nameEnum){		
		return properties.get(nameEnum);
		
	}

	public static void loadDefaultProperties() {
		// TODO Auto-generated method stub
		
	}
}
