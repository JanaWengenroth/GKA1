package GKA.Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class FordFulkerson extends FlowBase {
	
	public FordFulkerson(GKAGraph graph){
		init(graph);
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
}
