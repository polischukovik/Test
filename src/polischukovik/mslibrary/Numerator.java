package polischukovik.mslibrary;

public class Numerator {
	public static enum TYPE {
		NUMERIC,
		ALPHABETIC,
		ALPHABETIC_CAPS,
		GREECE
	}
	private TYPE type;
	private int cnt;
	
	public Numerator(TYPE type) {
		super();
		this.type = type;
		cnt = 0;
	}
	
	public String getNext(){
		switch (type) {
		case NUMERIC:
			return String.valueOf(++cnt);

		default:
			return "0";
		}
	}


}
