package main;

public class Edge {
	public final int From;
	public final int To;
	
	public Edge(int from, int to){
		From = from;
		To = to;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Edge))
			return false;
		
		Edge other = (Edge)obj;
		return (From == other.From) && (To == other.To);
	}
	
	@Override
	public int hashCode() {
		return 131071 * From + To;
	}
}
