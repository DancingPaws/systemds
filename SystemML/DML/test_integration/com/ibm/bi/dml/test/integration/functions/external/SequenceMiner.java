package com.ibm.bi.dml.test.integration.functions.external;

import java.util.HashMap;

import org.junit.Test;

import com.ibm.bi.dml.runtime.matrix.io.MatrixValue.CellIndex;
import com.ibm.bi.dml.test.integration.AutomatedTestBase;
import com.ibm.bi.dml.test.integration.TestConfiguration;
import com.ibm.bi.dml.test.utils.TestUtils;


public class SequenceMiner extends AutomatedTestBase {

	private final static String TEST_SEQMINER = "SeqMiner"; 
	
	@Override
	public void setUp() {
		baseDirectory = SCRIPT_DIR + "functions/external/";
		availableTestConfigurations.put(TEST_SEQMINER, new TestConfiguration(TEST_SEQMINER, new String[] { "fseq", "sup"}));
	}

	@Test
	public void testSequenceMiner() {
		
		int rows = 5;
		int cols = 10;

		TestConfiguration config = availableTestConfigurations.get("SeqMiner");
		config.addVariable("rows", rows);
		config.addVariable("cols", cols);

		/* This is for running the junit test by constructing the arguments directly */
		String SEQMINER_HOME = baseDirectory;
		fullDMLScriptName = SEQMINER_HOME + TEST_SEQMINER + ".dml";
		programArgs = new String[]{"-args",  SEQMINER_HOME + INPUT_DIR + "M" , 
				                        Integer.toString(rows), Integer.toString(cols), 
				                         SEQMINER_HOME + OUTPUT_DIR + "fseq" ,
				                         SEQMINER_HOME + OUTPUT_DIR + "sup" };
		
		double[][] M = {{1, 2, 3, 4, -1, 2, 3, 4, 5, -1}, 
				{1, 2, 3, 4, -1, 2, 3, 4, 5, -1}, 
				{1, 2, 3, 4, -1, 2, 3, 4, 5, -1}, 
				{1, 2, 3, 4, -1, 2, 3, 4, 5, -1}, 
				{1, 2, 3, 4, -1, 2, 3, 4, 5, -1}};
		
		
		writeInputMatrix("M", M);
		
		HashMap<CellIndex, Double> fseq = TestUtils.readDMLMatrixFromHDFS(baseDirectory + "seqMiner/FreqSeqFile");
		HashMap<CellIndex, Double> sup = TestUtils.readDMLMatrixFromHDFS(baseDirectory + "seqMiner/FreqSeqSupportFile");
		
		
		double [][] expected_fseq = TestUtils.convertHashMapToDoubleArray(fseq);
		double [][] expected_sup = TestUtils.convertHashMapToDoubleArray(sup);
		
		
		
		writeExpectedMatrix("fseq", expected_fseq);
		writeExpectedMatrix("sup", expected_sup);
		
		
		
		loadTestConfiguration(config);

		// no expected number of M/R jobs are calculated, set to default for now
		runTest(true, false, null, -1);

		compareResultsRowsOutOfOrder(0.0);
	}
}
