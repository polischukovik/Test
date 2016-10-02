package polischukovik.domain;

import java.util.ArrayList;
import java.util.List;

public class Variant {
	private String name;
	private List<Question> questions;
	private int questionNumber;
	
	public Variant(String name) {
		super();
		this.name = name;
		questionNumber = 0;
		questions = new ArrayList<>();
	}

	public void add(Question question){
		questions.add(question);
		++questionNumber;
	}
}
