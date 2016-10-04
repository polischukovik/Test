package polischukovik.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Test {
	private String caption;
	private List<Variant> variants;
	private Set<Question> sourceQuestions;
	private int variantNumber;
	
	public Test(String caption) {
		super();
		this.caption = caption;
		this.variantNumber = 0;
		this.variants = new ArrayList<>();
	}
	
	/*
	 * Adds variant to the test
	 */
	public void add(Variant variant){
		variants.add(variant);
		++variantNumber;
	}

	public String getCaption() {
		return caption;
	}

	public List<Variant> getVariants() {
		return variants;
	}
	
	

}
