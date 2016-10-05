package polischukovik.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.naming.directory.InvalidAttributesException;

import polischukovik.mslibrary.Numerator;
import polischukovik.mslibrary.Properties;
import polischukovik.mslibrary.Numerator.TYPE;
import polischukovik.mslibrary.TestFactory;

public class Variant {
	private String name;
	private List<Question> questions;
	private int questionNumber;
	private Map<String, List<String>> keys; //could be <Question, List<String>>>
	
	public Variant(String name, List<QuestionRaw> newVariant) {
		super();
		this.name = name;
		questionNumber = 0;
		questions = new ArrayList<>();
		/*
		 * Set question numeration style
		 */
		Numerator nums = new Numerator(TestFactory.getNumerationStyle(Numerator.TYPE.NUMERIC, Properties.NAMES.P_QUESTION_NUMERATION));
		
		for(QuestionRaw q : newVariant){
			List<Answer> answers = new ArrayList<>();
			String[] answerRaw = q.getAnswers();
			Integer [] correct = q.getCorrect();
			List<Integer> correctList = Arrays.asList(correct);
			/*
			 * Set answer numeration style
			 */
			Numerator answerNums = new Numerator(TestFactory.getNumerationStyle(Numerator.TYPE.ALPHABETIC, Properties.NAMES.P_ANSWER_NUMERATION));
			
			for(int i = 0; i < answerRaw.length; i++){
				answers.add(new Answer(answerNums.getNext(), answerRaw[i] ));
			}
			Question prep = new Question(nums.getNext(), q.getType(), q.getQuestion(), answers);
			add(prep);
		}
		
	}

	public void add(Question question){
		questions.add(question);
		++questionNumber;
	}

	public String getName() {
		return name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public Map<String, List<String>> getKeys() {
		return keys;
	}
	
}
