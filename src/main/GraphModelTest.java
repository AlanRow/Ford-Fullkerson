package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class GraphModelTest {

	void testCase(int[][] graph, int expMCount) {
		GraphModel model = new GraphModel(graph);
		List<Edge> act = model.FindMaxMatching();
		assertEquals(expMCount, act.size());
	}

	@Test
	void simpleTests() {
		/*testCase(new int[][] {{0}, 
							  {1}}, 1);
		testCase(new int[][] {{1, 0},
							  {1, 1}}, 2);*/
		testCase(new int[][] {{0, 1, 1},
			  				  {1, 0, 0},
							  {1, 0, 0}}, 2);
	}
}
