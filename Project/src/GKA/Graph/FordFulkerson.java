package GKA.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FordFulkerson {
	
	private GKAGraph graph;
	private Matrix<String, Double> maxFlows;
	private Matrix<String, Double> currentFlows;

	public FordFulkerson(GKAGraph graph){
		init(graph);
	}
	private void init(GKAGraph graph){
		this.graph = graph;
		Set<String> vertexes = graph.getjGraph().vertexSet();
		this.currentFlows = new Matrix<>(vertexes, vertexes);
		this.maxFlows = generateMaxFlows();
	}
	
	private Matrix<String, Double> generateMaxFlows(){
		Set<String> vertexes = graph.getjGraph().vertexSet();
		Matrix<String, Double> flows = new Matrix<>(vertexes, vertexes);
		for(String row: vertexes){
			for(String column: vertexes){
				double flow = 0.0;
				for(GKAEdge edge: getAccessibleEdgesBetween(row, column)){
					flow += edge.getWeight();
				}
				flows.put(row, column, flow);
			}
		}
		return flows;
	}
	
	private Set<GKAEdge> getAccessibleEdgesBetween(String source, String target){
		Set<GKAEdge> edges = new HashSet<>(graph.getjGraph().getAllEdges(source, target));
		Set<GKAEdge> notGoable = new HashSet<>();
		for (GKAEdge edge : edges) {
			if(!isEdgeAccessible(edge, source)){
				notGoable.add(edge);
			}
		}
		edges.removeAll(notGoable);
		return edges;
	}

	private boolean isEdgeAccessible(GKAEdge edge, String source){
		if(edge.getSource().equals(source)){
			return true;
		}else if(!graph.isDirected() && edge.getTarget().equals(source)){
			return true;
		}else{
			return false;
		}
	}
	public double maxFlow(String source, String sink){
		clearFlows();
		
		Set<String> forwardVertexes = forwardVertexesofSources(source);
		if(forwardVertexes.isEmpty()){
			return 0.0;
		}
		else{
			double maxFlow = 0.0;
			double returnedFlow = 0.0;
			do{
				returnedFlow = maxFlow_(new ArrayList<>(), source, sink, Double.POSITIVE_INFINITY);	
				maxFlow += returnedFlow;
				System.out.println(" == " + maxFlow);
			}while(returnedFlow != 0.0);
			return maxFlow;
		}
	}
	private void clearFlows() {
		for(String row: currentFlows.getRows()){
			for(String column: currentFlows.getColumns()){
				currentFlows.put(row, column, 0.0);
			}
		}
		
	}
	private double maxFlow_(ArrayList<String> alreadyReached, String source, String sink, double maxFlow){
		if (source.equals(sink)){ 
			return maxFlow;
		}else{
			Set<String> forwardVertexes = forwardVertexesofSources(source);
			if(forwardVertexes.isEmpty()){
				Set<String> reverseVertexes = reverseVertexesofSources(source);
				Iterator<String> reverseIt = reverseVertexes.iterator();
				double returnedFlow = 0.0;
				String nextVertex = null;
				while(reverseIt.hasNext() && returnedFlow == 0.0){
					nextVertex = reverseIt.next();
					if(alreadyReached.contains(nextVertex)){
						returnedFlow = 0.0;
					}else{
						alreadyReached.add(source);
						returnedFlow = maxFlow_(alreadyReached, nextVertex, sink, Double.min(currentFlows.get(nextVertex, source), maxFlow));
					}
					if(returnedFlow != 0.0){
						currentFlows.put(nextVertex, source, currentFlows.get(nextVertex, source) - returnedFlow);
						System.out.print(source + ":" + nextVertex + "=-" + returnedFlow + "; ");
						return returnedFlow;
					}
				}
			}
			else{
				Iterator<String> forwardIt = forwardVertexes.iterator();
				double returnedFlow = 0.0;
				String nextVertex = null;
				while(forwardIt.hasNext() && returnedFlow == 0.0){
					nextVertex = forwardIt.next();
					if(alreadyReached.contains(nextVertex)){
						returnedFlow = 0.0;
					}else{
						alreadyReached.add(source);
						returnedFlow = maxFlow_(alreadyReached, nextVertex, sink, Double.min(getPossibleFlowBetween(source, nextVertex), maxFlow));
					}
					if(returnedFlow != 0.0){
						currentFlows.put(source, nextVertex, currentFlows.get(source, nextVertex) + returnedFlow);
						System.out.print(source + ":" + nextVertex + "=" + returnedFlow + "; ");
						return returnedFlow;
					}
				}
			}
		}
		return 0.0;
	}
	private Set<String> forwardVertexesofSources(String source) {
		Set<String> vertexes = new HashSet<>();
		for (String vertex: maxFlows.getColumns()){
			if (currentFlows.get(source, vertex) < maxFlows.get(source, vertex)){
				vertexes.add(vertex);
			}
		}
		return vertexes;
	}
	private Set<String> reverseVertexesofSources(String source){
		Set<String> vertexes = new HashSet<>();
		for (String vertex: maxFlows.getColumns()){
			if (currentFlows.get(vertex, source) > 0.0){
				vertexes.add(vertex);
			}
		}
		return vertexes;
	}
	private double getPossibleFlowBetween(String source, String sink){
		return maxFlows.get(source, sink) - currentFlows.get(source, sink);
	}
}
