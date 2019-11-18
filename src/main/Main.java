/**
 * 
 */
package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Алексей
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InputStream reader = new FileInputStream("in.txt");
		int[][] graphArr = GraphParser.getAdjArray(reader);
		
		GraphModel graph = new GraphModel(graphArr);
		
		List<Edge> edges = graph.FindMaxMatching();

		OutputStream writer = new FileOutputStream("out.txt");
		GraphParser.printAdjArr(edges, writer, graphArr.length);

	}

}
