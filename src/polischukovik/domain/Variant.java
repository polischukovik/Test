package polischukovik.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.naming.directory.InvalidAttributesException;

import polischukovik.mslibrary.Numerator;
import polischukovik.mslibrary.Properties;
import polischukovik.mslibrary.Numerator.TYPE;
import polischukovik.mslibrary.TestFactory;

public class Variant {
	private String name;
	private List<Question> questions;
	private Map<String, List<String>> keys; //could be <Question, List<String>>>
	Numerator.TYPE pQuestionNumStyle, pAnswerNumStyle;
	private int pQuestions;
	private boolean pShuffleAnswers;
	
	public Variant(String name, List<QuestionRaw> newVariant) {
		super();
		this.name = name;
		questions = new ArrayList<>();
		pQuestionNumStyle = TestFactory.getNumerationStyle(Numerator.TYPE.NUMERIC, Properties.NAMES.P_QUESTION_NUMERATION);
		pAnswerNumStyle = TestFactory.getNumerationStyle(Numerator.TYPE.ALPHABETIC, Properties.NAMES.P_ANSWER_NUMERATION);
		pQuestions = Integer.valueOf(TestFactory.getProperties().get(Properties.NAMES.QUESTIONS, "30"));
		pShuffleAnswers = TestFactory.getProperties().getBoolean(Properties.NAMES.SHUFFLE_ANSWERS, true);
		
		/*
		 * Set question numeration style
		 */
		Numerator nums = new Numerator(pQuestionNumStyle);
		
		for(int j = 0; (j < pQuestions) && (j < newVariant.size() - 1); j++){
			List<Answer> answers = new ArrayList<>();
			List<String> listAnswer = Arrays.asList(newVariant.get(j).getAnswers());
			Integer [] correct = newVariant.get(j).getCorrect();
			List<Integer> correctList = Arrays.asList(correct);

			/*
			 * Shuffle answers
			 */
			if(pShuffleAnswers){
				long seed = System.nanoTime();	
				Collections.shuffle(listAnswer, new Random(seed));
			}	
			
			/*
			 * Set answer numeration style
			 */
			Numerator answerNums = new Numerator(pAnswerNumStyle);
			
			for(String s : listAnswer){				
				answers.add(new Answer(answerNums.getNext(), s ));
			}
			
			Question prep = new Question(nums.getNext(), newVariant.get(j).getType(), newVariant.get(j).getQuestion(), answers);
			
			makeKeys(correctList);
			
			add(prep);
		}
		
	}

	private void makeKeys(List<Integer> correctList) {
		//for
		//keys
	}

	public void add(Question question){
		questions.add(question);
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
