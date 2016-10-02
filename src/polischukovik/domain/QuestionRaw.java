package polischukovik.domain;

import polischukovik.domain.Question.TYPE;

public class QuestionRaw {
	private Question.TYPE type;
	private String question;
	private String[] answers;
	private int[] correct;
	public QuestionRaw(TYPE type, String question, String[] answers, int[] correct) {
		super();
		this.type = type;
		this.question = question;
		this.answers = answers;
		this.correct = correct;
	}
	
	

}
