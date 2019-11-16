/**
 * 
 */
package main;

import java.util.List;

/**
 * @author Алексей
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean[][] lights = new boolean[2][];
		lights[0] = new boolean[] {true, false};
		lights[1] = new boolean[] {true, true};
		
		GraphModel graph = new GraphModel(lights);
		
		List<Edge> edges = graph.FindMaxMatching();
		
		System.out.println(edges.size());
		for (Edge edge : edges) {
			System.out.println(String.format("(%1d, %2d)", edge.From, edge.To));
		}

	}

}
