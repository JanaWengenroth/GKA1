package GKA.Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class FordFulkerson extends FlowBase {
	
	public FordFulkerson(GKAGraph graph){
		init(graph);
	}
	
	protected double maxFlow_(ArrayList<String> alreadyReached, String source, String sink, double maxFlow){
		if (source.equals(sink)){ 
			return maxFlow;
		}else{
			//Vorw�rtsSuche
			Set<String> forwardVertexes = forwardVertexesofSources(source);
			Iterator<String> forwardIt = forwardVertexes.iterator();
			double returnedFlow = 0.0;
			String nextVertex = null;
			while(forwardIt.hasNext() && returnedFlow == 0.0){
				hops++;
				nextVertex = forwardIt.next();
				if(alreadyReached.contains(nextVertex)){
					returnedFlow = 0.0;
				}else{
					alreadyReached.add(source);
					returnedFlow = maxFlow_(alreadyReached, nextVertex, sink, Double.min(getPossibleFlowBetween(source, nextVertex), maxFlow));
				}
				if(returnedFlow != 0.0){
					hops++;
					currentFlows.put(source, nextVertex, currentFlows.get(source, nextVertex) + returnedFlow);
					return returnedFlow;
				}
			}
			
			//R�ckw�rtssuche
			Set<String> reverseVertexes = reverseVertexesofSources(source);
			Iterator<String> reverseIt = reverseVertexes.iterator();
			while(reverseIt.hasNext() && returnedFlow == 0.0){
				hops++;
				nextVertex = reverseIt.next();
				if(alreadyReached.contains(nextVertex)){
					returnedFlow = 0.0;
				}else{
					alreadyReached.add(source);
					returnedFlow = maxFlow_(alreadyReached, nextVertex, sink, Double.min(currentFlows.get(nextVertex, source), maxFlow));
				}
				if(returnedFlow != 0.0){
					hops++;
					currentFlows.put(nextVertex, source, currentFlows.get(nextVertex, source) - returnedFlow);
					return returnedFlow;
				}
			}
		}
		return 0.0;
	}
}
