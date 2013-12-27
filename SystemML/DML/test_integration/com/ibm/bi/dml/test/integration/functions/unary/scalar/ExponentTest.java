/**
 * IBM Confidential
 * OCO Source Materials
 * (C) Copyright IBM Corp. 2010, 2014
 * The source code for this program is not published or otherwise divested of its trade secrets, irrespective of what has been deposited with the U.S. Copyright Office.
 */

package com.ibm.bi.dml.test.integration.functions.unary.scalar;

import org.junit.Test;

import com.ibm.bi.dml.api.DMLException;
import com.ibm.bi.dml.test.integration.AutomatedTestBase;
import com.ibm.bi.dml.test.integration.TestConfiguration;
import com.ibm.bi.dml.test.utils.TestUtils;



/**
 * <p><b>Positive tests:</b></p>
 * <ul>
 * 	<li>constant (int, double)</li>
 * 	<li>variable (int, double)</li>
 * 	<li>random constant (int, double)</li>
 * 	<li>random variable (int, double)</li>
 * 	<li>negative constant (int, double)</li>
 * </ul>
 * <p><b>Negative tests:</b></p>
 * <ul>
 * 	<li>two parameters</li>
 * </ul>
 * 
 * 
 */
public class ExponentTest extends AutomatedTestBase 
{
	@SuppressWarnings("unused")
	private static final String _COPYRIGHT = "Licensed Materials - Property of IBM\n(C) Copyright IBM Corp. 2010, 2014\n" +
                                             "US Government Users Restricted Rights - Use, duplication  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
		
	@Override
	public void setUp() {
		baseDirectory = SCRIPT_DIR + "functions/unary/scalar/";
		
		// positive tests
		availableTestConfigurations.put("ConstTest", new TestConfiguration("ExponentTest",
				new String[] { "int", "double" }));
		availableTestConfigurations.put("VarTest", new TestConfiguration("ExponentTest",
				new String[] { "int", "double" }));
		availableTestConfigurations.put("RandomConstTest", new TestConfiguration("ExponentTest",
				new String[] { "int", "double" }));
		availableTestConfigurations.put("RandomVarTest", new TestConfiguration("ExponentTest",
				new String[] { "int", "double" }));
		availableTestConfigurations.put("NegativeTest", new TestConfiguration("ExponentTest",
				new String[] { "int", "double" }));
		
		// negative tests
		availableTestConfigurations.put("TwoParametersTest", new TestConfiguration("ExponentBinaryTest",
				new String[] { "computed" }));
	}
	
	@Test
	public void testConst() {
		int intValue = 2;
		double doubleValue = 2.5;
		
		TestConfiguration config = availableTestConfigurations.get("ConstTest");
		config.addVariable("intvardeclaration", "");
		config.addVariable("intop", intValue);
		config.addVariable("doubledeclaration", "");
		config.addVariable("doubleop", doubleValue);
		
		loadTestConfiguration("ConstTest");
		
		double computedIntValue = Math.exp(intValue);
		double computedDoubleValue = Math.exp(doubleValue);
		
		createHelperMatrix();
		writeExpectedHelperMatrix("int", computedIntValue);
		writeExpectedHelperMatrix("double", computedDoubleValue);
		
		runTest();
		
		compareResults();
	}
	
	@Test
	public void testVar() {
		int intValue = 2;
		double doubleValue = 2.5;
		
		TestConfiguration config = availableTestConfigurations.get("VarTest");
		config.addVariable("intvardeclaration", "intValue = " + intValue + ";");
		config.addVariable("intop", "intValue");
		config.addVariable("doublevardeclaration", "doubleValue = " +
				TestUtils.getStringRepresentationForDouble(doubleValue) + ";");
		config.addVariable("doubleop", "doubleValue");
		
		loadTestConfiguration("VarTest");
		
		double computedIntValue = Math.exp(intValue);
		double computedDoubleValue = Math.exp(doubleValue);
		
		createHelperMatrix();
		writeExpectedHelperMatrix("int", computedIntValue);
		writeExpectedHelperMatrix("double", computedDoubleValue);
		
		runTest();
		
		compareResults();
	}
	
	@Test
	public void testRandomConst() {
		int intValue = TestUtils.getRandomInt();
		double doubleValue = TestUtils.getRandomDouble();
		
		TestConfiguration config = availableTestConfigurations.get("RandomConstTest");
		config.addVariable("intvardeclaration", "");
		config.addVariable("intop", intValue);
		config.addVariable("doubledeclaration", "");
		config.addVariable("doubleop", doubleValue);
		
		loadTestConfiguration("RandomConstTest");
		
		double computedIntValue = Math.exp(intValue);
		double computedDoubleValue = Math.exp(doubleValue);
		
		createHelperMatrix();
		writeExpectedHelperMatrix("int", computedIntValue);
		writeExpectedHelperMatrix("double", computedDoubleValue);
		
		runTest();
		
		compareResults();
	}
	
	@Test
	public void testRandomVar() {
		int intValue = TestUtils.getRandomInt();
		double doubleValue = TestUtils.getRandomDouble();
		
		TestConfiguration config = availableTestConfigurations.get("RandomVarTest");
		config.addVariable("intvardeclaration", "intValue = " + intValue + ";");
		config.addVariable("intop", "intValue");
		config.addVariable("doublevardeclaration", "doubleValue = " +
				TestUtils.getStringRepresentationForDouble(doubleValue) + ";");
		config.addVariable("doubleop", "doubleValue");
		
		loadTestConfiguration("RandomVarTest");
		
		double computedIntValue = Math.exp(intValue);
		double computedDoubleValue = Math.exp(doubleValue);
		
		createHelperMatrix();
		writeExpectedHelperMatrix("int", computedIntValue);
		writeExpectedHelperMatrix("double", computedDoubleValue);
		
		runTest();
		
		compareResults();
	}
	
	@Test
	public void testNegative() {
		int intValue = -2;
		double doubleValue = -2.5;
		
		TestConfiguration config = availableTestConfigurations.get("NegativeTest");
		config.addVariable("intvardeclaration", "");
		config.addVariable("intop", intValue);
		config.addVariable("doublevardeclaration", "");
		config.addVariable("doubleop", doubleValue);
		
		loadTestConfiguration("NegativeTest");
		
		double computedIntValue = Math.exp(intValue);
		double computedDoubleValue = Math.exp(doubleValue);
		
		createHelperMatrix();
		writeExpectedHelperMatrix("int", computedIntValue);
		writeExpectedHelperMatrix("double", computedDoubleValue);
		
		runTest();
		
		compareResults();
	}
	
	@Test
	public void testTwoParameters() {
		TestConfiguration config = availableTestConfigurations.get("TwoParametersTest");
		config.addVariable("vardeclaration", "");
		config.addVariable("op1", 1);
		config.addVariable("op2", 2);
		
		loadTestConfiguration("TwoParametersTest");
		
		createHelperMatrix();
		
		runTest(true, DMLException.class);
	}
	
}
