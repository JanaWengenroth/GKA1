package GKA.Graph;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class EdmondKarp extends FlowBase{
	public EdmondKarp(GKAGraph graph){
		init(graph);
	}
//	protected double maxFlow_(ArrayList<String> alreadyReached, String source, String sink, double maxFlow){
//		HashMap<String, String> parentMap = new HashMap<>();
//		Queue<String> queue = new LinkedList<>();
//		queue.add(source);
//		while(queue.size() > 0){
//			String aktuellerVertex = queue.poll();
//			alreadyReached.add(aktuellerVertex);
//			for(String nextVertex: forwardVertexesofSources(aktuellerVertex)){
//				if((getPossibleFlowBetween(aktuellerVertex, nextVertex) > 0)
//						&& !alreadyReached.contains(nextVertex)){
//					parentMap.put(nextVertex, aktuellerVertex);
//					maxFlow = Double.min(maxFlow, 
//							getPossibleFlowBetween(aktuellerVertex, nextVertex));
//					if(nextVertex != sink){
//						queue.offer(nextVertex);
//					}
//					else{
//						while(parentMap.get(nextVertex) != null){
//							currentFlows.put(parentMap.get(nextVertex) , nextVertex, 
//									currentFlows.get(parentMap.get(nextVertex) , nextVertex) + maxFlow);
//							currentFlows.put(nextVertex, parentMap.get(nextVertex) ,  
//									currentFlows.get(nextVertex, parentMap.get(nextVertex) ) - maxFlow);
//							nextVertex = parentMap.get(nextVertex);
//						}
//						return maxFlow;
//					}
//				}
//			}
//		}
//		return 0.0;
//	}
	public double maxFlow(String source, String sink) 
    {
		if(source.equals(sink)){
			return 0.0;
		}
		System.out.println(" Edmon " + source + " " + sink);
		HashMap<String, String> parentMap = new HashMap<>();
        long start = System.nanoTime();
        hops = 0;
        Matrix<String, Double> capacityMatrix = maxFlows; 
        Matrix<String, Double> flowMatrix = currentFlows;
        clearFlows();
        while (true) 
        {
            
     	   ArrayList<String> parentTable = new ArrayList<>(); //Vorgaengerliste
     	   parentTable.add(source);
     	   ArrayList<Double> pathCapacity = new ArrayList<>();
     	   pathCapacity.add(Double.POSITIVE_INFINITY);//Pfad Kapazitaeten
            // BFS queue
            Queue<String> Q = new LinkedList<String>();//Queue mit Knoten
            Q.add(source);
            LOOP:
            while (!Q.isEmpty()) 
            {
                String currentNode = Q.remove();
                for (String nextNode: forwardVertexesofSources(currentNode)) 
                {
                	hops++;
                    // Es existiert eine Kapazitaet groeßer 0, Pfad moeglich
                    // nextNode noch nicht besucht
                    if ((capacityMatrix.get(currentNode, nextNode) - flowMatrix.get(currentNode, nextNode)) > 0 && !parentTable.contains(nextNode)) 
                    {
                 	   parentTable.add(nextNode);
                 	  parentMap.put(nextNode, currentNode);
                 	   pathCapacity.add(
                 			   Math.min(
                 					   pathCapacity.get(parentTable.indexOf(currentNode)),
                 					   capacityMatrix.get(currentNode, nextNode) - flowMatrix.get(currentNode, nextNode))
                 	   );
                 	  System.out.println(" Edmon " + nextNode + " " + sink);
                 	   if (!nextNode.equals(sink))
                            Q.add(nextNode);
                       else 
                       {
                           // Backtrack search, and write flow
                     	  while(parentMap.get(nextNode) != null)
                           {
                     		   hops++;
                               flowMatrix.put(parentMap.get(nextNode), nextNode, flowMatrix.get(parentMap.get(nextNode), nextNode) + pathCapacity.get(pathCapacity.size()-1));
                               
                               flowMatrix.put(
                            		   nextNode, parentMap.get(nextNode), 
                            		   flowMatrix.get(nextNode, parentMap.get(nextNode)) - 
                            		   pathCapacity.get(pathCapacity.size() - 1));
                               nextNode = parentMap.get(nextNode);
                               System.out.println(" Edmon " + source + " " + sink);
                           }
                           break LOOP;
                     }
                }
            }
            }
            if (!parentTable.contains(sink)) 
            { // kein Weg zur Senke gefunden
                Double sum = 0.0;
                for (String row : flowMatrix.getRows())
                {
                	hops++;
                	sum += flowMatrix.get(source,row);
	            } 
                runTime = System.nanoTime() - start;
                System.out.println(" Edmon " + source + " " + sink);
                return sum;
	        }
	    }
	}
	@Override
	protected double maxFlow_(ArrayList<String> alreadyReached, String source,
			String sink, double maxFlow) {
		// TODO Auto-generated method stub
		return 0;
	}
}
