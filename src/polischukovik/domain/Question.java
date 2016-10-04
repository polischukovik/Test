package polischukovik.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import polischukovik.mslibrary.Numerator;

public class Question {
	public static enum TYPE {
		OPEN,
		SINGLE_ANSWER,
		MULTI_ANSWER,
		CORRESPONSANCE
	}
	
	private String id;
	private TYPE type;
	private String question;
	private List<Answer> answers;
	//public Question(int id, TYPE type, String question, String[] answers, int[] correctAnswers) {
	public Question(String id, TYPE type, String question, List<Answer> answers) {
		super();
		this.id = id;
		this.type = type;
		this.question = question;
		
		this.answers = new ArrayList<>();
		Numerator nums = new Numerator(Numerator.TYPE.NUMERIC);
		this.answers = answers;
	}
	@Override
	public String toString() {
		return "Question id=" + id + ", type=" + type + "\n"
				+ question + "\n"
				+ answers;
	}

	
	
	
	
	
}
