package GKA.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinimumSpanningTree {
	private final GKAGraphInterface minimumSpanningTree = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
	public MinimumSpanningTree(GKAGraphInterface graph){
		generateMinimumSapnningTree(graph);
	}
	
	public GKAGraphInterface getMinimumSpanningTree() {
		return minimumSpanningTree;
	}
	
	private void generateMinimumSapnningTree(GKAGraphInterface graph){
		List<GKAEdge> edges = new ArrayList<>(graph.getjGraph().edgeSet());
		
		edges.sort(new Comparator<GKAEdge>() {
			@Override
			public int compare(GKAEdge o1, GKAEdge o2) {
				return o1.getWeight().compareTo(o2.getWeight());
			}
		});
		
		for(String v: graph.getjGraph().vertexSet()){
			minimumSpanningTree.addVertex(v);
		}
		for(GKAEdge edge: edges){
			if (minimumSpanningTree.shortesPathBroad(edge.getSource().toString(), edge.getTarget().toString())==null){
				minimumSpanningTree.addEdge(edge.getSource().toString(), edge.getTarget().toString(), null, edge.getWeight());
			}
		}
	}
}
