package GKA.Graph;

public enum GraphType {
	Directed(true,false),
	Undirected(false,false),
	DirectedWeighted(true,true),
	UndirectedWeighted(false,true);
	
	private boolean isDirected;
	private boolean isWeighted;

	private GraphType(boolean isDirected, boolean isWeighted) {
		this.isDirected = isDirected;
		this.isWeighted = isWeighted;
	}

	public boolean isDirected() {
		return isDirected;
	}
	public boolean isWeighted(){
		return isWeighted;
	}
}
