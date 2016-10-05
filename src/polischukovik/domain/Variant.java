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
	Numerator.TYPE pQuestionNumStyle, pAnswerNumStyle;
	private int pQuestions;
	
	public Variant(String name, List<QuestionRaw> newVariant) {
		super();
		this.name = name;
		questionNumber = 0;
		questions = new ArrayList<>();
		pQuestionNumStyle = TestFactory.getNumerationStyle(Numerator.TYPE.NUMERIC, Properties.NAMES.P_QUESTION_NUMERATION);
		pAnswerNumStyle = TestFactory.getNumerationStyle(Numerator.TYPE.ALPHABETIC, Properties.NAMES.P_ANSWER_NUMERATION);
		pQuestions = Integer.valueOf(TestFactory.getProperties().get(Properties.NAMES.QUESTIONS, "30"));
		
		/*
		 * Set question numeration style
		 */
		Numerator nums = new Numerator(pQuestionNumStyle);
		
		for(int j = 0; (j < pQuestions) || (j < newVariant.size() - 1); j++){
			List<Answer> answers = new ArrayList<>();
			String[] answerRaw = newVariant.get(j).getAnswers();
			Integer [] correct = newVariant.get(j).getCorrect();
			List<Integer> correctList = Arrays.asList(correct);
			
			makeKeys(correctList);
			/*
			 * Set answer numeration style
			 */
			Numerator answerNums = new Numerator(pAnswerNumStyle);
			
			for(int i = 0; i < answerRaw.length; i++){
				answers.add(new Answer(answerNums.getNext(), answerRaw[i] ));
			}
			Question prep = new Question(nums.getNext(), newVariant.get(j).getType(), newVariant.get(j).getQuestion(), answers);
			add(prep);
		}
		
	}

	private void makeKeys(List<Integer> correctList) {
		//for
		//keys
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
