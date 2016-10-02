package polischukovik.mslibrary;

import java.util.Set;

import polischukovik.domain.QuestionRaw;
import polischukovik.domain.Test;
import polischukovik.domain.Variant;

public class TestFactory {
	Set<QuestionRaw> questions;

	public TestFactory(Set<QuestionRaw> questions) {
		this.questions = questions;
	}

	public Test createTests(Properties prop) {
		Test test = new Test(prop.get(Properties.NAMES.TEST_NAME));
		
		for(int i = 0; i < Integer.valueOf(prop.get(Properties.NAMES.VARIANTS)); i++){
			Variant v = new Variant(String.valueOf(i), List);
			test.add(v);
		}
		return null;
	}

}
