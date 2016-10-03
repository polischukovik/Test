package polischukovik.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import polischukovik.mslibrary.Numerator;
import polischukovik.mslibrary.Numerator.TYPE;

public class Variant {
	private String name;
	private List<Question> questions;
	private int questionNumber;
	private Map<String, List<String>> keys;
	
	public Variant(String name, List<QuestionRaw> newVariant) {
		super();
		this.name = name;
		questionNumber = 0;
		questions = new ArrayList<>();
		Numerator nums = new Numerator(TYPE.NUMERIC);
		
		for(QuestionRaw q : newVariant){
			List<Answer> answers = new ArrayList<>();
			String[] answerRaw = q.getAnswers();
			Integer [] correct = q.getCorrect();
			List<Integer> correctList = Arrays.asList(correct);
			
			Numerator answerNums = new Numerator(TYPE.NUMERIC);
			
			for(int i = 0; i < answerRaw.length; i++){
				answers.add(new Answer(answerRaw[i], answerNums.getNext()));
			}
			Question prep = new Question(nums.getNext(), q.getType(), q.getQuestion(), answers);
		}
		
	}

	public void add(Question question){
		questions.add(question);
		++questionNumber;
	}
}
