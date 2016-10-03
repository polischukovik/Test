package polischukovik.domain;

import polischukovik.domain.Question.TYPE;

public class QuestionRaw {
	private Question.TYPE type;
	private String question;
	private String[] answers;
	private Integer[] correct;
	public QuestionRaw(TYPE type, String question, String[] answers, Integer[] correct) {
		super();
		this.type = type;
		this.question = question;
		this.answers = answers;
		this.correct = correct;
	}
	public Question.TYPE getType() {
		return type;
	}
	public String getQuestion() {
		return question;
	}
	public String[] getAnswers() {
		return answers;
	}
	public Integer[] getCorrect() {
		return correct;
	}
	
	

}
