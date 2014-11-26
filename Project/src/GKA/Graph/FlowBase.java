package GKA.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class FlowBase {

	protected GKAGraph graph;
	protected Matrix<String, Double> maxFlows;
	protected Matrix<String, Double> currentFlows;
	protected long runTime = 0;
	protected int hops = 0;
	public FlowBase() {
		super();
	}

	public int getHops(){
		return hops;
	}
	public long getRunTime(){
		return runTime;
	}
	
	protected void init(GKAGraph graph) {
		this.graph = graph;
		Set<String> vertexes = graph.getjGraph().vertexSet();
		this.currentFlows = new Matrix<>(vertexes, vertexes);
		this.maxFlows = generateMaxFlows();
	}

	private Matrix<String, Double> generateMaxFlows() {
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

	private Set<GKAEdge> getAccessibleEdgesBetween(String source, String target) {
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

	private boolean isEdgeAccessible(GKAEdge edge, String source) {
		if(edge.getSource().equals(source)){
			return true;
		}else if(!graph.isDirected() && edge.getTarget().equals(source)){
			return true;
		}else{
			return false;
		}
	}

	protected void clearFlows() {
		for(String row: currentFlows.getRows()){
			for(String column: currentFlows.getColumns()){
				currentFlows.put(row, column, 0.0);
			}
		}
		
	}

	protected Set<String> forwardVertexesofSources(String source) {
		Set<String> vertexes = new HashSet<>();
		for (String vertex: maxFlows.getColumns()){
			hops++;
			if (currentFlows.get(source, vertex) < maxFlows.get(source, vertex)){
				vertexes.add(vertex);
			}
		}
		return vertexes;
	}

	protected Set<String> reverseVertexesofSources(String source) {
		Set<String> vertexes = new HashSet<>();
		for (String vertex: maxFlows.getColumns()){
			hops++;
			if (currentFlows.get(vertex, source) > 0.0){
				vertexes.add(vertex);
			}
		}
		return vertexes;
	}

	protected double getPossibleFlowBetween(String source, String sink) {
		return maxFlows.get(source, sink) - currentFlows.get(source, sink);
	}
	
	public double maxFlow(String source, String sink){
		clearFlows();
		hops = 0;
		long start = System.nanoTime();
		if(source.equals(sink)){
			return Double.POSITIVE_INFINITY;
		}
		Set<String> forwardVertexes = forwardVertexesofSources(source);
		if(forwardVertexes.isEmpty()){
			runTime = System.nanoTime() - start;
			return 0.0;
		}
		else{
			double maxFlow = 0.0;
			double returnedFlow = 0.0;
			do{
				returnedFlow = maxFlow_(new ArrayList<>(), source, sink, Double.POSITIVE_INFINITY);	
				maxFlow += returnedFlow;
			}while(returnedFlow != 0.0);
			runTime = System.nanoTime() - start;
			return maxFlow;
		}
	}
	
	abstract protected double maxFlow_(ArrayList<String> alreadyReached, String source, String sink, double maxFlow);

}