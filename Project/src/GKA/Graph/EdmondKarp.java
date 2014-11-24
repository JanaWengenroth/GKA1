package GKA.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class EdmondKarp extends FlowBase{
	public EdmondKarp(GKAGraph graph){
		init(graph);
	}
	
	public Double edmondsKarp(String source, String sink) 
    {
       
        Matrix<String, Double> capacityMatrix = maxFlows; 
        Matrix<String, Double> flowMatrix = currentFlows;
        clearFlows();
        while (true) 
        {
            
     	   ArrayList<String> parentTable = new ArrayList<>();
     	   parentTable.add(source);
     	   ArrayList<Double> pathCapacity = new ArrayList<>();
     	   pathCapacity.add(Double.POSITIVE_INFINITY);
            // BFS queue
            Queue<String> Q = new LinkedList<String>();
            Q.add(source);
            LOOP:
            while (!Q.isEmpty()) 
            {
                String currentNode = Q.remove();
                for (String nextNode: forwardVertexesofSources(currentNode)) 
                {
                    // There is available capacity,
                    // and v is not seen before in search
                    if ((capacityMatrix.get(currentNode, nextNode) - flowMatrix.get(currentNode, nextNode)) > 0 && !parentTable.contains(nextNode)) 
                    {
                 	   parentTable.add(nextNode);
                 	   pathCapacity.add(
                 			   Math.min(
                 					   pathCapacity.get(parentTable.indexOf(currentNode)),
                 					   capacityMatrix.get(currentNode, nextNode) - flowMatrix.get(currentNode, nextNode))
                 	   );
                 	   if (nextNode != sink)
                            Q.add(nextNode);
                       else 
                       {
                           // Backtrack search, and write flow
                     	   ListIterator<String> backTrack = parentTable.listIterator(parentTable.size());
                     	   nextNode = backTrack.previous();
                     	   while (backTrack.hasPrevious()) 
                           {
                     		   currentNode = backTrack.previous();
                               flowMatrix.put(currentNode, nextNode, flowMatrix.get(currentNode, nextNode) + pathCapacity.get(pathCapacity.size()-1));
                               
                               flowMatrix.put(
                            		   nextNode, currentNode, 
                            		   flowMatrix.get(nextNode, currentNode) - 
                            		   pathCapacity.get(pathCapacity.size() - 1));
                               nextNode = currentNode;
                           }
                           break LOOP;
                     }
                }
            }
            }
            if (!parentTable.contains(sink)) 
            { // We did not find a path to t
                Double sum = 0.0;
                for (String row : flowMatrix.getRows())
                {
                    for (String column : flowMatrix.getColumns())
                    {
                        if(column == source)
                        {
                            sum += flowMatrix.get(column,row);
                        }
	                }
	               
	            } 
                return sum;
	        }
	    }
	}
}
