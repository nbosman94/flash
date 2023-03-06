

/**
 * @author natashabosman
 *
 */
public class Values{
	
	private String expression;
	private String expressionBuffer;
	private double firstNum;
	private double runningTotal = 0;
	private double result;
	private double lhsValue;
	private double rhsValue;
	private String lhsBuffer;
	private String rhsBuffer;
	private String operator;
	
	public Values ()
	{
		
	}
	public Values (String expression, String expressionBuffer, double firstNum, double secondNum, double runningTotal,double result, double lhsValue, double rhsValue, String lhsBuffer, String rhsBuffer, String operator)
	{
		this.expression = expression;
		this.expressionBuffer = expressionBuffer;
		this.firstNum = firstNum;
		this.runningTotal = runningTotal;
		this.result = result;
		this.lhsValue = lhsValue;
		this.rhsValue = rhsValue;
		this.lhsBuffer = lhsBuffer;
		this.rhsBuffer = rhsBuffer;
		this.operator = operator;	
	}
	
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getExpressionBuffer() {
		return expressionBuffer;
	}
	public void setExpressionBuffer(String expressionBuffer) {
		this.expressionBuffer = expressionBuffer;
	}
	public double getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(double firstNum) {
		this.firstNum = firstNum;
	}
	public double getRunningTotal() {
		return runningTotal;
	}
	public void setRunningTotal(double runningTotal) {
		this.runningTotal = runningTotal;
	}
	public double getResult() {
		return result;
	}
	public double getLhsValue() {
		return lhsValue;
	}
	public void setLhsValue(double lhsValue) {
		this.lhsValue = lhsValue;
	}
	public double getRhsValue() {
		return rhsValue;
	}
	public void setRhsValue(double rhsValue) {
		this.rhsValue = rhsValue;
	}
	public String getLhsBuffer() {
		return lhsBuffer;
	}
	public void setLhsBuffer(String lhsBuffer) {
		this.lhsBuffer = lhsBuffer;
	}
	public String getRhsBuffer() {
		return rhsBuffer;
	}
	public void setRhsBuffer(String rhsBuffer) {
		this.rhsBuffer = rhsBuffer;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * This method checks if the right hand string from the splitExpression method contains a negative at the front
	 * It will return true if a negative number is present or false if it is not a negative.
	 * 
	 * @param rhs
	 * @return
	 */
	public boolean checkForNegativePowerinExpression(String rhs)
	{
		String operator = getOperator();
		String sign = rhs.substring(0, 1);
		boolean containsNegative = false;
		if(operator.equals("^") && sign.equals("-"))
		{
			containsNegative = true;
		}
		return containsNegative;
	}
	/**
	 * 
	 * @param expression
	 * @return expression with white space removed
	 */
	public String deleteWhiteSpace(String expression)
	{
		String withoutWhiteSpace = expression.replace(" ", "");
		setExpression(withoutWhiteSpace);
		return withoutWhiteSpace;
	}
	/**
	 * This method handles *- , +-, -- and *-- updates the signs accordingly.
	 * 
	 * @param expression
	 * @return updated expression
	 */
	public String cleanExpression(String expression) 
	{
		String ex1 = expression.replace("+-", "-");
		String ex2 = ex1.replace("*--", "*"); 
		String ex3 = ex2.replace("++", "+");
		String ex4 = ex3.replace("--", "+");
		return ex4;
	}
	
	/**
	 * This method obtains operator from expression and removes it from the expression.
	 * It will then adjust the running total with the newly adjusted expression. 
	 * 
	 * @param expression
	 */
	public void operatorIndexIsZero(String expression)
	{
		String operator = expression.substring(0, 1);
		flushFirstValueInExpressionBuffer(expression);
		int index = checkFirstIndexOfOperator(getExpressionBuffer());

		double firstNum;
		if(index == -1)
		{
			firstNum = Double.parseDouble(getExpressionBuffer());
			checkOperator(operator, firstNum, getRunningTotal());
		}
		else if (index >= 0)
		{
		    setOperator(operator); 
			adjustRunningTotal(getExpressionBuffer(), index);
		}
	}
	
	/**
	 * This method sets the number from the inputed String.
	 * If the operator is empty, the method sets the result as just the number from string.
	 * If the operator is not empty, it sets the result as the current running total.
	 * 
	 * @param expression
	 */
	public void setResult(String expression)
	{
		double firstNum = Double.parseDouble(expression.substring(0));
		setFirstNum(firstNum);
	
		if (getOperator() == null)
		{
			setResult(firstNum);
		}
		else
		{
			double result = getRunningTotal();
			setResult(result);
		}
	}
	
	/**
	 * This method is implemented once the first calculation has been obtained from splitting the expression.
	 * It checks the operator position. 
	 * If the operator position is zero, it calls operatorIndexIsZero method.
	 * If the operator position is -1, it sets the result as no further operators are present in string
	 * It will then evaluate the remaining expression if operators are still present in the expression.
	 *
	 * @param expression
	 * @param index value of operator
	 */
	public void adjustRunningTotal(String expression, int index)
	{
		double firstNum;
		int length = expression.length();
	
		if(index == 0) 
		{
			operatorIndexIsZero(expression);
		}
		else if(index == -1)
		{
			setResult(expression);
		}
		else if(index == length-1)
		{
			setOperator(expression.substring(index));
			firstNum = Double.parseDouble(expression.substring(0, index));
			checkOperator(getOperator(), firstNum, getRunningTotal());
		}
		else
		{
			firstNum = Double.parseDouble(expression.substring(0, index));
			setFirstNum(firstNum);
			if(getRunningTotal() == 0)
			{
				setRunningTotal(firstNum);
				setExpressionBuffer(expression.substring(index));
				assignOperator(getExpressionBuffer());
			}
			else
			{
				setExpressionBuffer(expression.substring(index));
				checkOperator(getOperator(), firstNum, getRunningTotal());
				assignOperator(getExpressionBuffer());
				adjustRunningTotal(getExpressionBuffer(), index-1);
			}
		}		
	}
	
	/**
	 * 
	 * @param expression
	 * @return index of the first multiply position
	 */
	public int checkForMultiply(String expression)
	{
		int length = expression.length();
		int index = length;
		if(expression.substring(0, length).contains("*"));
		{    
	    	index = expression.indexOf("*",0);				
		}
		return index;
	}
	
	/**
	 * 
	 * @param expression
	 * @return index of the first divide position
	 */
	public int checkForDivide(String expression)
	{
		int length = expression.length();
		int index = length;
		if(expression.substring(0, length).contains("/"));
		{    
	    	 index = expression.indexOf("/",0);		
		}
		return index;
	}
	
	/**
	 * 
	 * @param expression
	 * @return index of the first plus position
	 */
	public int checkForPlus(String expression) {
		int length = expression.length();
		int index = length;
		if(expression.substring(0, length).contains("+"));
		{    
	    	index = expression.indexOf("+",0);			
		}
		return index;
	}
	
	/**
	 * 
	 * @param expression
	 * @return index of the first subtract position
	 */
	public int checkForSubtract(String expression)
	{
		int length = expression.length();
		int index;
		
		if(expression.substring(0, length).contains("-"));
		{    
	    	index = expression.indexOf("-",0);		
		}
		return index;
	}
	
	/**
	 * 
	 * @param expression
	 * @return index of the first power (exponent) position
	 */
	public int checkForPower(String expression)
	{
		int length = expression.length();
		int index;
		
		if(expression.substring(0, length).contains("^"));
		{    
	    	index = expression.indexOf("^",0);			
		}
		return index;
	}
	
	/**
	 * This will return the highest index of an operator in an expression, regardless of the precedence.
	 * 
	 * @param expression
	 * @return highest index of index in expression
	 */
	public int checkHighestIndexOfOperator(String expression)
	{
		int highestIndex = -1;
		int plusIndex = expression.lastIndexOf("+");
		int minusIndex = expression.lastIndexOf("-");
		int multiplyIndex = expression.lastIndexOf("*");
		int divideIndex = expression.lastIndexOf("/");
		
		int a = max(plusIndex, minusIndex);
		int b = max(multiplyIndex, divideIndex);
		
		highestIndex = max(a,b);
		
		return highestIndex;
	}

    /**
     * 
     * @param expression
     * @return first index of operator with highest precedence
     */
	public int checkFirstIndexOfOperator (String expression)
	{
		int lowestIndex = expression.length();
		
		int firstIndexOfMultiply = checkForMultiply(expression);
		int firstIndexOfDivide = checkForDivide(expression);
		int firstIndexOfPlus = checkForPlus(expression);
		int firstIndexOfSubtract = checkForSubtract(expression);
		int firstIndexOfPower = checkForPower(expression);
		
	    if(firstIndexOfPower != -1)
	    {
	    	lowestIndex = firstIndexOfPower;
	    }
		else if(firstIndexOfMultiply != -1 )
		{
			lowestIndex = firstIndexOfMultiply;
		}
		else if (firstIndexOfDivide != -1) 
		{
			lowestIndex = firstIndexOfDivide;
		}
		else if (firstIndexOfPlus != -1)
		{
			lowestIndex = firstIndexOfPlus;
		}
		else if (firstIndexOfSubtract != -1)
		{
			lowestIndex = firstIndexOfSubtract;
		}
		else
		{
			lowestIndex = -1;
		}
		return lowestIndex;
	}
	/**
	 * This method obtains the last number from the left hand string and the first number from the right hand string.
	 * It passes these values into the perfromMath method to obtain result.
	 * The method then sets the right and left side buffers.
	 * 
	 * @param lhs
	 * @param rhs
	 * @return result of negative power evaluated
	 */
	public double handleNegativePower(String lhs, String rhs)
	{
		double lhsValue, rhsValue;
		int highestIndexLHS = checkHighestIndexOfOperator(lhs);
		if (highestIndexLHS == -1) 
		{
			 lhsValue = Double.parseDouble(lhs.substring(0));
		}
		else
		{
			 lhsValue = Double.parseDouble(lhs.substring(highestIndexLHS));
			 setRhsBuffer(lhs.substring(0,highestIndexLHS+1));
		}
		
		int lowestIndexRHS = checkFirstIndexOfOperator(rhs);
		
		if (lowestIndexRHS ==0) 
		{
			rhsValue = Double.parseDouble(rhs.substring(0));
		}
		else
		{
			rhsValue = Double.parseDouble(rhs.substring(0,lowestIndexRHS ));
			setRhsBuffer(rhs.substring(lowestIndexRHS));
		}
		
		double result = performMath(getOperator(), lhsValue, rhsValue);
		
		return result;
	}
	/**
	 * This method will determine if there is a negative power from the first split.
	 * If present, it will call the handleNegativePower method
	 * Otherwise it will then call the setFirstValueFromRHS and setFirstValueFromLHS and perform the first calculation.
	 * It will continue to evaluate the expression as long as the buffers from both sides are not empty. 
	 * Once both sides have cleared and evaluated, the runningTotal will be set.
	 * 
	 * @param rhs
	 * @param lhs
	 */
	public void handleExpressions(String rhs, String lhs)
	{
		double result = 0;
		boolean powerResult = checkForNegativePowerinExpression(rhs);
		if(powerResult == true)
		{
			double negativePowerResult = handleNegativePower(lhs, rhs);
			setRunningTotal(negativePowerResult);
		}
		else
		{
			setFirstValueFromLHS(lhs);
			setFirstValueFromRHS(rhs);
			result = performMath(getOperator(), getLhsValue(), getRhsValue());
			setRunningTotal(result);
		}
		
		if (getRhsBuffer() != null)
		{
			int rhsIndex = checkFirstIndexOfOperator(getRhsBuffer());
			adjustRunningTotal(getRhsBuffer(),rhsIndex);
		
		}
		if (getLhsBuffer() != null)
		{
			int highestIndex = checkHighestIndexOfOperator(getLhsBuffer());
			flushLastValueInExpressionBuffer(getLhsBuffer(), highestIndex);
			int lhsIndex = checkFirstIndexOfOperator(getLhsBuffer());
			adjustRunningTotal(getLhsBuffer(),lhsIndex);	
		}
		else if (getLhsBuffer() == null && getRhsBuffer() == null)
		{
			setResult(getRunningTotal());
		}
	}
	
	/**
	 * This is the first method invoked once the string has had white space removed and had signs evaluated.
	 * It splits the expression to a left hand string and right hand string based on operator precendence.
	 * It will set the first operator and call the handleExpression method.
	 * 
	 * @param expression
	 * @param index
	 */
	public void splitExpression(String expression, int index)
	{
		
		String lhs = expression.substring(0,index);
		String rhs = expression.substring(index+1);
		setOperator(expression.substring(index, index +1));
		setExpression(expression);
		handleExpressions(rhs, lhs);	
	}
	/**
	 * This method sets the first value from the right hand side of the split expression.
	 * It first checks to see the operator in the expression to see if there are other values remaining
	 * in the expression.
	 * 
	 * If there is just a number left, it will set the right hand side value to the number found.
	 * Otherwise, it will continue to evaluate the expression.
	 * 
	 * @param expression
	 */
	public void setFirstValueFromRHS(String expression)
	{
		int index = checkFirstIndexOfOperator(expression);
		
		if (index == -1)
		{
			setRhsValue(Double.parseDouble(expression.substring(0)));
		}
		else
		{
			setRhsBuffer(expression.substring(index));
			setRhsValue(Double.parseDouble(expression.substring(0, index)));
		}
	}
	
	/**
	 * This method will set the final operator from expression and then set the number
	 * It will then call the checkOperator method to update the running total.
	 * 
	 * @param expression
	 */
	public void lastNumInStringLHS(String expression)
	{
		String operator = expression.substring(0,1);
		double firstNum = Double.parseDouble (expression.substring(1));
		checkOperator(operator, firstNum, getRunningTotal());
	}
	
	/**
	 * This method sets the first value from the left hand side of the split expression.
	 * It first checks to see the operator in the expression to see if there are other values remaining
	 * in the expression.
	 * 
	 * If there is just a number left, it will set the left hand side value to the number found.
	 * If the operator is at the front of the expression, it will call the lastNumInStringLHS method.
	 * Otherwise, it will continue to evaluate the expression.
	 * 
	 * @param expression
	 */
	public void setFirstValueFromLHS(String expression)
	{
		int index = checkFirstIndexOfOperator(expression);
		if (index == -1)
		{
			setLhsValue(Double.parseDouble(expression.substring(0)));
		}
		else if (index ==0) 
		{
			lastNumInStringLHS(expression);
			setLhsValue(getRunningTotal());		
		}
		else
		{
			setLhsBuffer(expression.substring(0,index+1));
			int highestIndex = checkHighestIndexOfOperator(expression);
			setLhsValue(Double.parseDouble(expression.substring(highestIndex)));
			setLhsBuffer(expression.substring(0,index+1));	
		}	
	}
	
	/**
	 * This sets the operator to the first element from expression buffer
	 * 
	 * @param expressionBuffer
	 */
	public void assignOperator(String expressionBuffer)
	{
		setOperator(expressionBuffer.substring(0,1));
	}
	
	/**
	 * This method will remove the last element from the expression and set the expression buffer value.
	 * 
	 * @param expressionBuffer
	 */
	public void flushFirstValueInExpressionBuffer(String expressionBuffer)
	{
			setExpressionBuffer(expressionBuffer.substring(1));
	}
	
	/**
	 * This method will remove the last element from the expression and set the expression buffer value.
	 * 
	 * @param expressionBuffer
	 * @param index
	 */
	public void flushLastValueInExpressionBuffer(String expressionBuffer, int index)
	{
		setExpressionBuffer(expressionBuffer.substring(0, index));
	}
	
	/**
	 * This method adjusts the current running total based on the number and operator it is provided.
	 * If the the running total is zero it will set the running total as the number if the
	 * operator is either multiply or divide.
	 * 
	 * @param operator
	 * @param firstNum
	 * @param runningTotal
	 */
	public void checkOperator(String operator, double firstNum, double runningTotal)
	{

		if(operator.equals("+"))
		{
			runningTotal += firstNum;
			setRunningTotal(runningTotal);	
		}
		else if(operator.equals("-"))
		{
			runningTotal = runningTotal - firstNum;
			setRunningTotal(runningTotal);	
		}
		else if (operator.equals("*"))
		{
			if(runningTotal == 0)
			{
			 setRunningTotal(firstNum);	
			}
			else
			{
				runningTotal *= firstNum;
				setRunningTotal(runningTotal);	
			}
	
		}
		else if (operator.equals("/"))
		{
			if(runningTotal == 0)
			{
			 setRunningTotal(firstNum);	
			}
			else
			{
				runningTotal /= firstNum;
				setRunningTotal(runningTotal);	
			}
		}
		  
	}
	
	/**
	 * This recursive method will multiply the base by itself.
	 *   
	 * 
	 * @param base
	 * @param power
	 * @return base^power
	 */
	public double powerOf(double base, double power)
	{
		if(power> 0)
		{
			return(base * powerOf(base, power-1));
		}
		else if(power <0)
		{
			return powerOf(base, power+1)/base;
		}
		else
		{
			return 1;
		}
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return x*y
	 */
	public double multiply(double x, double y)
	{
		return x * y;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return x/y
	 */
	public double divide (double x, double y)
	{
		return x/y;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return x +y
	 */
	public double add(double x, double y) 
	{
		return x + y;	
		
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return x - y
	 */
	public double subtract(double x, double y)
	{
		return x - y;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return greatest value
	 */
	public int max(int x, int y)
	{
		if( x > y)
		{
			return x;
		}
		else
		{
			return y;
		}
	}
	
	/**
	 * This method takes two numbers to perform the correct subtract, divide, multiply or add based on the 
	 * operater it is given.
	 * 
	 * 
	 * @param operator
	 * @param x
	 * @param y
	 * @return result of math expression invoked
	 */
	public double performMath(String operator, double x, double y)
	
	{
		double result = 0;
		
		if(operator.equals("+"))
		{
			result = add(x, y);
		}
		else if(operator.equals("-"))
		{
			result = subtract(x, y);
		}
		else if(operator.equals("/"))
		{
			result = divide(x, y);
		}
		else if(operator.equals("*"))
		{
			result = multiply(x, y);
		}
		else if(operator.equals("^"))
		{
			result = powerOf(x,y);
		}
		return result;
	}
}
