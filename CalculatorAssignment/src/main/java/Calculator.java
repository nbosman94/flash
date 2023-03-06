


public class Calculator implements ICalculator {
	
	
	private Values values = new Values();
	
	public Calculator()
	{
		
	}

	@Override
	public double evaluate(String expression) {
		
		String withoutWhiteSpace = values.deleteWhiteSpace(expression);
		String ex = values.cleanExpression(withoutWhiteSpace);
		int firstIndex = values.checkFirstIndexOfOperator(ex);
		values.splitExpression(ex, firstIndex);
		return values.getRunningTotal();

	}


}
