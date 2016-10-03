package polischukovik.mslibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
		List<QuestionRaw> sorted = new ArrayList<>(questions);
		long seed = System.nanoTime();
		
		for(int i = 0; i < Integer.valueOf(prop.get(Properties.NAMES.VARIANTS)); i++){
			List<QuestionRaw> newVariant = new ArrayList<>(sorted);
			Collections.shuffle(newVariant, new Random(seed));
			
			Variant v = new Variant(String.valueOf(i), newVariant);
			test.add(v);
		}
		return test;
	}

}
