import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.tdd.Calculator.Calculator;
import com.fdmgroup.tdd.Calculator.Values;

class CalculatorTest4 {

	Calculator calculator;
	Values values;
	
	@BeforeEach
	void setup()
	{
		calculator = new Calculator();
		values = new Values();
	}
	
	@Test
	void remove_white_space_with_2_Arguments() {
		
		assertEquals("1+3",values.deleteWhiteSpace("1 + 3"));
	}
	
	@Test
	void remove_white_space_with_3_Arguments() {
		
		assertEquals("1+4+5",values.deleteWhiteSpace("1 + 4 + 5"));
	}
	
	@Test
	void remove_white_space_with_2_Different_Operators() {
		
		assertEquals("1+4-5",values.deleteWhiteSpace("1 + 4 - 5"));
	}
	
	@Test
	void remove_white_space_with_3_Different_Operators() {
		
		assertEquals("1+4-5*4",values.deleteWhiteSpace("1 + 4 - 5 * 4"));
	}
	
	@Test
	void test__clean_Expression()
	{
		assertEquals("1+1+3",values.cleanExpression("1++1--3"));
	}
	
	@Test
	void test__clean_Expression2()
	{
		assertEquals("1+5-7+2+6",values.cleanExpression("1--5+-7--2++6"));
	}
	
	@Test
	void test__clean_Expression3()
	{
		assertEquals("2+5*7+5",values.cleanExpression("2--5*--7+5"));
	}
	
	@Test
	void test__check_negative_power_in_expression()
	{
		values.setOperator("^");
		assertEquals(true,values.checkForNegativePowerinExpression("-5"));
	}
	
	@Test
	void test__check_no_negative_power_in_expression()
	{
		values.setOperator("^");
		assertEquals(false,values.checkForNegativePowerinExpression("+10"));
	}
	
	@Test
	void test_2_plus_3_equals_5() {
		
		assertEquals(5.0,values.add(2.00,  3.00));
	}
	
	@Test
	void test_50_plus_20_equals_70() {
		
		assertEquals(70.0,values.add(50.00,  20.00));
	}
	
	@Test
	void test_1point5_plus_3_equals_4point5() {
		
		assertEquals(4.5,values.add(1.50,  3.00));
	}
	
	@Test
	void test_minus2_plus_3_equals()
	{
		assertEquals(1.0,values.add(-2.00,  3.00));
	}
	
	@Test
	void test_2_minus_3_equals_minus1() {
		
		assertEquals(-1.0,values.subtract(2.00,  3.00));
	}
	
	@Test
	void test_minus2_minus_3_equals_minus5() {
		
		assertEquals(-5.0,values.subtract(-2.00,  3.00));
	}
	
	@Test
	void test_2point5_minus_1_equals_1point5() {
		
		assertEquals(1.50,values.subtract(2.50,  1.00));
	}
	
	@Test
	void test_1_multiply_5_equals_5() {
		
		assertEquals(5.00,values.multiply(1.00,  5.00));
	}
	
	@Test
	void test_10_multiply_3_equals_30() {
		
		assertEquals(30.00,values.multiply(10.00,  3.00));
	}
	
	@Test
	void test_point5_multiply_3_equals_1point5() {
		
		assertEquals(1.50,values.multiply(0.50,  3.00));
	}
	
	@Test
	void test_minus5_multiply_3_equals_minus15() {
		
		assertEquals(-15.00,values.multiply(-5.00,  3.00));
	}
	
	@Test
	void test_minus5_multiply_minus3_equals_15() {
		
		assertEquals(15.00,values.multiply(-5.00,  -3.00));
	}
	
	@Test
	void test_5_divide_1_equals_5() {
		
		assertEquals(5.00,values.divide(5.00,  1.00));
	}
	
	@Test
	void test_minus15_divide_minus3_equals_5() {
		
		
		assertEquals(5.00,values.divide(-15.00,  -3.00));
	}
	
	@Test
	void test_checkHighestIndexOfOperator_1_plus_10_multiply_5_minus_2()
	{
		assertEquals(4,values.checkFirstIndexOfOperator("1+10*2"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_plus_1_equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("1+1"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_plus_1_plus_2_equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("1+1+2"));
	}
	
	@Test
	void test_operatorPositionBODMAN_1_minus_1_equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("1-1"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_plus_1_minus_2_equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("1+1-2"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_multiply_1_equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("1*1"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_multiply_1_multiply_2_equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("1*1*2"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_multiply_1_divide_2_equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("1*1/2"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_divide_1_equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("1/1"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_divide_1_plus_2_equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("1*1+2"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_minus_3_plus_2_multiply4_equals5()
	{
		assertEquals(5,values.checkFirstIndexOfOperator("1-3+2*4"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1_minus_3_plus_2_divide_4_equals5()
	{
		assertEquals(5,values.checkFirstIndexOfOperator("1-3+2/4"));
	}
	
	@Test
	void test_operatorPositionBODMAS_1__equalsminus1()
	{
		assertEquals(-1,values.checkFirstIndexOfOperator("1"));
	}
	
	@Test
	void test_operatorPositionBODMAS_40__equalsminus1()
	{
		assertEquals(-1,values.checkFirstIndexOfOperator("40"));
	}
	
	@Test
	void test_operatorPositionBODMAS_50point65__equalsminus1()
	{
		assertEquals(-1,values.checkFirstIndexOfOperator("50.65"));
	}
	
	@Test
	void test_operatorPositionBODMAS_plus2__equalZero()
	{
		assertEquals(0,values.checkFirstIndexOfOperator("+2"));
	}
	
	@Test
	void test_operatorPositionBODMAS_multiply2__equalZero()
	{
		assertEquals(0,values.checkFirstIndexOfOperator("*2"));
	}
	
	@Test
	void test_operatorPositionBODMAS_divide2__equalZero()
	{
		assertEquals(0,values.checkFirstIndexOfOperator("/2"));
	}
	
	@Test
	void test_operatorPositionBODMAS_minus2__equalZero()
	{
		assertEquals(0,values.checkFirstIndexOfOperator("-2"));
	}
	
	@Test
	void test_operatorPositionBODMAS_2plus__equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("2+"));
	}
	
	@Test
	void test_operatorPositionBODMAS_2multiply__equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("2*"));
	}
	
	@Test
	void test_operatorPositionBODMAS_2divide__equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("2/"));
	}
	
	@Test
	void test_operatorPositionBODMAS_2minus__equals1()
	{
		assertEquals(1,values.checkFirstIndexOfOperator("2-"));
	}
	
	@Test
	void test_checkContainsSubtractIndex_2_minus_2__equals1()
	{
		assertEquals(1,values.checkForSubtract("2-2"));
	}
	
	@Test
	void test_checkDoesNotContainsSubtractIndex_2_plus_2__equalsminus1()
	{
		assertEquals(-1,values.checkForSubtract("2+2"));
	}
	
	@Test
	void test_checkContainsSubtractIndex_2_minus_2_plus_1__equals1()
	{
		assertEquals(1,values.checkForSubtract("2-2+1"));
	}
	
	@Test
	void test_checkContainsPlusIndex_2_plus_2__equals1()
	{
		assertEquals(1,values.checkForPlus("2+2"));
	}
	
	@Test
	void test_checkDoesNotContainsPlusIndex_2_minus_2__equalsminus1()
	{
		assertEquals(-1,values.checkForPlus("2-2"));
	}
	
	@Test
	void test_checkContainsPlusIndex_2_plus_2_plus_1_multiply_3__equals3()
	{
		assertEquals(3,values.checkForPlus("2-2+1*3"));
	}
	
	@Test
	void test_checkContainsMultiplyIndex_3_multiply_2_plus_1__equals1()
	{
		assertEquals(1,values.checkForMultiply("3*2+1"));
	}
	
	@Test
	void test_checkContainsMultipluIndex_2_multiply_2__equals1()
	{
		assertEquals(1,values.checkForMultiply("2*2"));
	}
	
	@Test
	void test_checkDoesNotContainsMultipluIndex_2_plus_2__equalsminus1()
	{
		assertEquals(-1,values.checkForMultiply("2+2"));
	}
	
	@Test
	void test_checkContainsMultiplyIndex_3_plus_4_multiply_1__equals3()
	{
		assertEquals(3,values.checkForMultiply("3+4*1"));
	}
	
	@Test
	void test_checkContainsDivideIndex_3_divide_4_multiply_1__equals1()
	{
		assertEquals(1,values.checkForDivide("3/4*1"));
	}
	
	@Test
	void test_checkDoesNotContainsDivideIndex_3_plus_40_multiply_1__equalsminus1()
	{
		assertEquals(-1,values.checkForDivide("3+40*1"));
	}
	
	@Test
	void test_performMath_plusOperator_4_and_4__equals8()
	{
		assertEquals(8.00,values.performMath("+", 4.00, 4.00));
	}
	
	@Test
	void test_performMath_plusOperator_10_and_20__equals30()
	{
		assertEquals(30.00,values.performMath("+", 10.00, 20.00));
	}

	@Test
	void test_performMath_plusOperator_1point3_and_1point2__equals2point5()
	{
		assertEquals(2.50,values.performMath("+", 1.30, 1.20));
	}
	
	@Test
	void test_performMath_minusOperator_4_and_3__equals1()
	{
		assertEquals(1.00,values.performMath("-", 4.00, 3.00));
	}
	
	@Test
	void test_performMath_minusOperator_50_and_15__equals35()
	{
		assertEquals(35.00,values.performMath("-", 50.00, 15.00));
	}
	
	@Test
	void test_performMath_minusOperator_5_and_1point5__equals3point5()
	{
		assertEquals(3.50,values.performMath("-", 5.00, 1.50));
	}
	
	@Test
	void test_performMath_multiplyOperator_4_and_4__equals16()
	{
		assertEquals(16.00,values.performMath("*", 4.00, 4.00));
	}

	@Test
	void test_performMath_multiplyOperator_50_and_30__equals1500()
	{
		assertEquals(1500.00,values.performMath("*", 50.00, 30.00));
	}
	
	@Test
	void test_performMath_divideOperator_9_and_3__equals3()
	{
		assertEquals(3.00,values.performMath("/", 9.00, 3.00));
	}
	
	@Test
	void test_performMath_divideOperator_30_and_10__equals3()
	{
		assertEquals(3.00,values.performMath("/", 30.00, 10.00));
	}
	
	@Test
	void test_performMath_divideOperator_5_and_point5__equals10()
	{
		assertEquals(10.00,values.performMath("/", 5.00, 0.50));
	}
	
	@Test
	void test_evaluate_1_plus_1_equals2()
	{
		assertEquals(2.00,calculator.evaluate("1+1"));
	}
	
	@Test
	void test_evaluate_1_plus_1_plus_2equals4()
	{
		assertEquals(4.00,calculator.evaluate("1+1+2"));
	}
	
	@Test
	void test_evaluate_1_plus_2_plus_10_equals13()
	{
		assertEquals(13.00,calculator.evaluate("1+2+10"));
	}
	
	@Test
	void test_evaluate_1_plus_1_plus_2_plus_5_plus_7_plus10_equals26()
	{
		assertEquals(26.00,calculator.evaluate("1+1+2+5+7+10"));
	}
	
	@Test
	void test_evaluate_5_minus_2_equals3()
	{
		assertEquals(3.00,calculator.evaluate("5-2"));
	}
	
	@Test
	void test_evaluate_10_minus_1_minus_2_equals7()
	{
		assertEquals(7.00,calculator.evaluate("10-1-2"));
	}
	
	@Test
	void test_evaluate_5point5_minus_point5_minus_2_equals3()
	{
		assertEquals(3.00,calculator.evaluate("5.5-0.5-2"));
	}
	
	@Test
	void test_evaluate_5_plus_10_minus_2_equals13()
	{
		assertEquals(13.00,calculator.evaluate("5+10-2"));
	}
	
	@Test
	void test_evaluate_minus5_plus_2_equalsminus3()
	{
		assertEquals(-3.00,calculator.evaluate("-5+2"));
	}
	
	@Test
	void test_evaluate_minus5_plus_2_minus3_equalsminus6()
	{
		assertEquals(-6.00,calculator.evaluate("-5+2-3"));
	}
	
	@Test
	void test_evaluate_6_plus_2_plusplus3_equals11()
	{
		assertEquals(11.00,calculator.evaluate("6+2++3"));
	}
	
	@Test
	void test_evaluate_5_multiply_2_equals10()
	{
		assertEquals(10.00,calculator.evaluate("5*2"));
	}
	
	@Test
	void test_evaluate_10_multiply_3_equals30()
	{
		assertEquals(30.00,calculator.evaluate("10*3"));
	}
	
	@Test
	void test_evaluate_plus2_multiply_6_equals12()
	{
		assertEquals(12.00,calculator.evaluate("+2*6"));
	}
	
	@Test
	void test_evaluate_minus2_multiply_6_equalsminus12()
	{
		assertEquals(-12.00,calculator.evaluate("-2*6"));
	}
	
	@Test
	void test_evaluate_minus2_multiply_6_multiply_2_equalsminus24()
	{
		assertEquals(-24.00,calculator.evaluate("-2*6*2"));
	}
	
	@Test
	void test_evaluate_30_multiply_2_multiply_20_equals1200()
	{
		assertEquals(1200.00,calculator.evaluate("30*2*20"));
	}
	
	@Test
	void test_evaluate_2_plus_2_multiply_20_minus_2__equals40()
	{
		assertEquals(40.00,calculator.evaluate("2+2*20-2"));
	}
	
	@Test
	void test_evaluate_2_plus_5_multiply_2_mius_1_equals11() 
	{
		assertEquals(11.00,calculator.evaluate("2+5*2-1"));
	}
	
	@Test
	void test_evaluate_30_divide_2_equals15()
	{
		assertEquals(15.00,calculator.evaluate("30/2"));
	}
	
	@Test
	void test_evaluate_3_divide_2_equals1point5()
	{
		assertEquals(1.50,calculator.evaluate("3/2"));
	}
	
	@Test
	void test_evaluate_3_divide_2_plus_1_equals2point5()
	{
		assertEquals(2.50,calculator.evaluate("3/2+1"));
	}
	
	@Test
	void test_evaluate_3_plus_1_divide_2equals5point5()
	{
		assertEquals(3.50,calculator.evaluate("3+1/2"));
	}
	
	@Test
	void test_evaluate_4_multiply_2_multiply_5__plus2_plus_1_equals43()
	{
		assertEquals(43.00,calculator.evaluate("4*2*5+2+1"));
	}
	
	@Test
	void test_evaluate_800_multiply_20_divide_4__plus600_plus_1_equals43()
	{
		assertEquals(4718.5,calculator.evaluate("800*20/4+647+10.5+70.5"));
	}
	
	@Test
	void test_evaluate_1_plus_2_multiply_5__plus2_plus_1point5_equals43()
	{
		assertEquals(14.50,calculator.evaluate("1+2*5+2+1.5"));
	}
	
	@Test
	void test_evaluate_11_plus_10_plusminus2_equals19()
	{
		assertEquals(19.00,calculator.evaluate("11+10+-2"));
	}
	
	@Test
	void test_evaluate_2_divide_2_divide_2_divide_2_minus_minus1point5_equals1point75()
	{
		assertEquals(1.75,calculator.evaluate("2/2/2/2--1.5"));
	}
	
	@Test
	void test_evaluate_3_multiply_2_plus_1_plus_minus4point55_equals2point45()
	{
		assertEquals(2.45,calculator.evaluate("3*2+1+-4.55"));
	}
	
	// EXPONENTS
	
	@Test
	void test_evaluate_2_powerOf_5_equals32()
	{
		assertEquals(32.00,calculator.evaluate("2^5"));
	}
	
	@Test
	void test_evaluate_7_powerOf_0_equals1()
	{
		assertEquals(1.00,calculator.evaluate("7^0"));
	}
	
	@Test
	void test_evaluate2_plus_2_powerOf_5_plus11_equals45()
	{
		assertEquals(45.00,calculator.evaluate("2+2^5+11"));
	}
	
	@Test
	void test_evaluate_3_powerOf_2_multiply2_multiply2_minus_minus1point5_equals37point5()
	{
		assertEquals(37.50,calculator.evaluate("3^2*2*2--1.5"));
	}
	
	@Test
	void test_evaluate_2_powerOf_minus2_equals0point25()
	{
		assertEquals(0.25,calculator.evaluate("2^-2"));
	}
	
	@Test
	void test_evaluate_2_powerOf_minus2_plus4_plus10_equals14point25()
	{
		assertEquals(14.25,calculator.evaluate("2^-2+4+10"));
	}
	
	@Test
	void test_evaluate_4_powerOf_minus2_multiply2_multiply2_multiply2_minus_minus1point5_equals14point25()
	{
		assertEquals(1.75,calculator.evaluate("4^-2*2*2--1.5"));
	}

}
