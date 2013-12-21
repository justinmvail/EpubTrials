package main;

public class Replacement {
	
	private String expression;
	private String replacement;
	
	public Replacement(String expression, String replacement){
		setExpression(expression);
		setReplacement(replacement);
	}	
	
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getReplacement() {
		return replacement;
	}
	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}
	
}
