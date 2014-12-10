package GKA.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumSpanningTree {
	private final GKAGraphInterface minimumSpanningTree = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
	private final Set<GKAEdge> edgeSet = new HashSet<>();
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
				edgeSet.add(edge);
				minimumSpanningTree.addEdge(edge.getSource().toString(), edge.getTarget().toString(), null, edge.getWeight());
			}
		}
	}

	public Set<GKAEdge> getEdgeSet() {
		return Collections.unmodifiableSet(edgeSet);
	}
}
