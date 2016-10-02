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
	
	private int id;
	private TYPE type;
	private String question;
	private List<Answer> answers;
	public Question(int id, TYPE type, String question, String[] answers, int[] correctAnswers) {
		super();
		this.id = id;
		this.type = type;
		this.question = question;
		
		this.answers = new ArrayList<>();
		Numerator nums = new Numerator(Numerator.TYPE.NUMERIC);
		List<Integer> corr = new ArrayList<>();
		for(int i = 0; i < correctAnswers.length; i++){
			corr.add(correctAnswers[i]);
		}
		
		for(int i = 0; i < answers.length; i++){
			this.answers.add(new Answer(answers[i], corr.contains(answers[i]), nums.getNext()));
		}
	}

	
	
	
	
	
}
