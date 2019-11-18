package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class GraphParser {

	static int[][] getAdjArray(InputStream stream) throws IOException{
		
		int[] sizes = parseString(readLine(stream));
		
		int k = sizes[0];
		int l = sizes[1];
		
		int[][] arr = new int[k][];
		
		int cur = 0;
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < k; i++){
			arr[i] = parseString(readLine(stream));
		}
		
		return arr; 
	}
	
	static public void printAdjArr(List<Edge> edges, OutputStream dest, int k) throws IOException{

		String line = "";
		for (int n = 0; n < k; n++){
			int adjX = -1;
			for (Edge e : edges){
				if (n == e.From){
					adjX = e.To;
					break;
				}
			}
			
			line += (adjX + 1);
			
			if (n < k - 1) {
				line += " ";
			}
			
			//line += "0\r\n";
		}
		
		dest.write(line.getBytes());
	}
	
	static int[] parseString(String str){
		String[] strNums = str.split(" ");
		
		int[] nums = new int[strNums.length];
		
		for (int i = 0; i < nums.length; i++){
			nums[i] = Integer.parseInt(strNums[i]);
		}
		
		return nums;
	}
	
	static public String readLine(InputStream reader) throws IOException
	{
		String line = "";
		int code = 0;
		
		while ((code = reader.read()) > 0){
			if ((char)code == '\r' && (char)reader.read() == '\n'){
				return line;
			}
			
			line += (char)code;
		}
		
		return line;
	}
}
