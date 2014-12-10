package GKA.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class MSTHeuristic {
	private final GKAGraphInterface dupGraph;
	private final GKAGraphInterface tour;
	private List<String> way = new ArrayList<>();
	private double length = 0.0;
	public MSTHeuristic(GKAGraphInterface graph, String startNode){
		if(graph.getjGraph().vertexSet().size() < 2 ){
			way = new ArrayList<>(graph.getjGraph().vertexSet().size());
			dupGraph = graph;
			tour = graph;
		}else{
			dupGraph = generateDuplicatedGraph(graph.getMinimumSpanningTree());
			tour = generateTour(graph, dupGraph,startNode);
		}
	}
	private GKAGraphInterface generateTour(GKAGraphInterface graph, GKAGraphInterface dupGraph, String startnode) {
		GKAGraphInterface tour = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
		List<String> way = new ArrayList<>();
		way.add(startnode);
		Set<GKAEdge> movedEdges = new HashSet<>();
		Set<GKAEdge> movableEdges = findMovableEdges(startnode, movedEdges, dupGraph);
		while(!movableEdges.isEmpty()){
			GKAEdge nextEdge = null;
			for(GKAEdge edge: movableEdges){
				nextEdge = edge;
				if(!way.contains(nextEdge.getTarget())){
					break;
				}
			}
			movedEdges.add(nextEdge);
			String nextnode = nextEdge.getTarget().toString();
			way.add(nextnode);
			movableEdges = findMovableEdges(nextnode, movedEdges, dupGraph);
			startnode = nextnode;
		}
		Iterator<String> it = way.iterator();
		String currentNode = it.next();
		this.way.add(currentNode);
		while(it.hasNext()){
			String nextNode = it.next();
			if(!this.way.contains(nextNode)){
				this.way.add(nextNode);
				double currentWayLength = 0.0;
				for(GKAEdge edge: graph.dijkstra(currentNode, nextNode)){
					currentWayLength += edge.getWeight();
				}
				tour.addEdge(currentNode, nextNode, null, currentWayLength);
				this.length += currentWayLength;
				currentNode = nextNode;
			}
		}
		
		this.way.add(startnode);
		double currentWayLength = 0.0;
		for(GKAEdge edge: graph.dijkstra(currentNode, startnode)){
			currentWayLength += edge.getWeight();
		}
		tour.addEdge(currentNode, startnode, null, currentWayLength);
		this.length += currentWayLength;
		
		return tour;
	}
	private Set<GKAEdge> findMovableEdges(String node, Set<GKAEdge> movedEdges, GKAGraphInterface dupGraph){
		Set<GKAEdge> retVal = new HashSet<>(dupGraph.getjGraph().edgesOf(node));
		Set<GKAEdge> removeEdges = new HashSet<>(movedEdges);
		for(GKAEdge edge: retVal){
			if(!edge.getSource().equals(node)){
				removeEdges.add(edge);
			}
		}
		retVal.removeAll(removeEdges);
		return retVal;
	}
	
	private GKAGraphInterface generateDuplicatedGraph(MinimumSpanningTree graph){
		GKAGraphInterface dupGraph = GKAGraphInterface.newGraph(GraphType.DirectedWeighted);
		for(GKAEdge edge: graph.getEdgeSet()){
			dupGraph.addEdge(edge.getSource().toString(), edge.getTarget().toString(), null, edge.getWeight());
			dupGraph.addEdge(edge.getTarget().toString(), edge.getSource().toString(), null, edge.getWeight());
		}
		return dupGraph;
	}
	public GKAGraphInterface getTour(){
		return tour;
	}
}
