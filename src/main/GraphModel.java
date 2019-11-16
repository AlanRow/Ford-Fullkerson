package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GraphModel {
	private boolean[][] graph;
	private int Xlen;
	private int Ylen;
	
	public GraphModel(boolean[][] adjArray) {
		graph = adjArray;
		Ylen = graph.length;
		Xlen = graph[0].length;
	}
	
	/*
	 * Find the maximum matching in graph and return edges in it.
	 */
	public List<Edge> FindMaxMatching() {
		//Step 1: create two queues and two boolean arrays, for X and for Y
		Queue<Integer> X = new LinkedList<Integer>();
		Queue<Integer> Y = new LinkedList<Integer>();
		//add all x to X:
		for (int i = 0; i < Xlen; i++) {
			X.add(i);
		}
		//matching graph
		boolean[][] lights = new boolean[Ylen][];
		for (int i = 0; i < Ylen; i++) {
			lights[i] = new boolean[Xlen];
			for (int j = 0; j < Xlen; j++) {
				lights[i][j] = graph[i][j];
			}
		}
		//matching arrays
		boolean[] matchX = new boolean[Xlen];
		boolean[] matchY = new boolean[Ylen];
		
		//Step 2: Start loop - while is any in both queues
		while (!X.isEmpty() || !Y.isEmpty()) {
			//previous maps
			Map<Integer, Integer> Xprev = new HashMap<Integer, Integer>();
			Map<Integer, Integer> Yprev = new HashMap<Integer, Integer>();
			
			//Step 3: Start subloop - for X, while X not empty
			boolean isFoundChain = false;
			while (!X.isEmpty() && !isFoundChain) {
				int x = X.poll();
				
				//if x matched then continue it
				if (matchX[x])
					continue;
				
				//if x first, then mark it as walked
				if (Xprev.get(x) == null) {
					Xprev.put(x, -1);
				}
				//Step 3.1: sub-sub-loop - for y from Y, that adj. to x 
				//			and edge is light
				for (int i = 0; i < Ylen; i++) {
					if (graph[i][x] && lights[i][x] && Yprev.get(i) == null) {
						Yprev.put(i, x);
						if (!matchY[i]) {
							relight(i, Yprev, Xprev, lights);
							rematch(lights, matchY, matchX);
							isFoundChain = true;
							break;
						}
						Y.add(i);
					}
				}
			}
			//Step 4: Start subloop - for Y, while Y not empty
			while (!Y.isEmpty()) {
				int y = Y.poll();
				//Step 4.1: sub-sub-loop for x from X, that adj. to y and edge is dark
				for (int i = 0; i < Xlen; i++) {
					if (graph[y][i] && !lights[y][i] && Xprev.get(i) == null) {
						Xprev.put(i, y);
						X.add(i);
					}
				}
			}
		}
		
		return getEdges(lights, graph);
	}
	
	private void relight(int last, Map<Integer, Integer> Yprev, 
						 Map<Integer, Integer> Xprev, boolean[][] lights) {
		int cur = last;
		boolean isY = true;
		
		while((isY && Yprev.get(cur) != -1) || (!isY && Xprev.get(cur) != -1)) {
			int prev = (isY) ? Yprev.get(cur) : Xprev.get(cur);
			
			if (isY) {
				lights[cur][prev] = false;
			} else {
				lights[prev][cur] = true;
			}
			
			cur = prev;
			isY = !isY;
		}
	}
	
	private void rematch(boolean[][] lights, boolean[] matchX, boolean[] matchY) {
		matchX = new boolean[matchX.length];
		matchY = new boolean[matchY.length];
		for (int i = 0; i < matchY.length; i++) {
			for (int j = 0; j < matchX.length; j++) {
				if (!lights[i][j]) {
					matchY[i] = true;
					matchX[j] = true;
				}
			}
		}
	}
	
	private List<Edge> getEdges(boolean[][] lights, boolean[][] graph){
		List<Edge> edges = new LinkedList<Edge>();
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length; j++) {
				if (!lights[i][j] && graph[i][j]) {
					edges.add(new Edge(i, j));
				}
			}
		}
		return edges;
	}
}