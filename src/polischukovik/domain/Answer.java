package polischukovik.domain;

public class Answer {
	
	private String answer;
	//private boolean isCorrect;
	private String label;
	
//	public Answer(String answer, boolean isCorrect, String label) {
	public Answer(String answer, String label) {
		super();
		this.answer = answer;
		//this.isCorrect = isCorrect;
		this.label = label;
	}

	public String getAnswer() {
		return answer;
	}

	public String getLabel() {
		return label;
	}
	
	
	
	
}
