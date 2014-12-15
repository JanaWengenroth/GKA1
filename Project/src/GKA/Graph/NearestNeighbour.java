package GKA.Graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.RowSet;

public class NearestNeighbour
{

    private final Set<GKAEdge> edgeSet = new HashSet<>();
    
    private final GKAGraphInterface dupGraph;
    private final GKAGraphInterface tour;
    private List<String> way = new ArrayList<>();
    private double length = 0.0;
    private long runTime = 0;
    
    public List<String> getWay() {
        return way;
    }
    public double getLength() {
        return length;
    }
    public long getRunTime() {
        return runTime;
    }
    
    public NearestNeighbour(GKAGraphInterface graph, String source)
    {   
        long startTime = System.nanoTime();
        if(graph.getjGraph().vertexSet().size() < 2 )
        {
            way = new ArrayList<>(graph.getjGraph().vertexSet().size());            
            dupGraph = graph;
            tour = graph;
        }
        else
        {
            dupGraph = generateDuplicatedGraph(this);
            tour = generateNearestNeighbour(graph, dupGraph,source);
        }
        runTime = System.nanoTime() - startTime;
    }
    
    private GKAEdge getShortestEdge(Set<GKAEdge> neighbourEdges) 
    {    
        Iterator<GKAEdge> i = neighbourEdges.iterator();
        GKAEdge shortestEdge = null;
        if(i.hasNext())
        {
            shortestEdge = (GKAEdge) i.next();
        }
        while(i.hasNext())
        {
            GKAEdge currentEdge = (GKAEdge) i.next();
            if(currentEdge.getWeight() < shortestEdge.getWeight())
            {
                shortestEdge = currentEdge; 
            }
        }
        return shortestEdge;
    }
 
    private GKAGraphInterface generateNearestNeighbour(GKAGraphInterface graph, GKAGraphInterface dupGraph, String source)
    {
        GKAGraphInterface tour = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
        List<String> visitedVertexes = new ArrayList<>();
        String edgeSource = source;
        visitedVertexes.add(edgeSource);
        
        Set<GKAEdge> neighbourEdges = new HashSet<>();
        Set<GKAEdge> shortestEdges = new HashSet<>();
        GKAEdge shortestEdge = null;
        
        Set<GKAEdge> edgesToSource = new HashSet<>();
        GKAEdge edgeToSource = null;
        
        while(visitedVertexes.size() < graph.getjGraph().vertexSet().size())
        {
            Set<GKAEdge> moveableEdges = new HashSet<>(graph.getjGraph().edgesOf(edgeSource));

            for(GKAEdge edge: moveableEdges)
            {
                if(((edge.getSource().toString().equals(edgeSource) && !visitedVertexes.contains(edge.getTarget()))) ||
                        (edge.getTarget().toString().equals(edgeSource) && !visitedVertexes.contains(edge.getSource())))
                {
                   neighbourEdges.add(edge);
                }
                shortestEdge = getShortestEdge(neighbourEdges);
            }
            shortestEdges.add(shortestEdge);
            
            if((shortestEdge.getSource().toString().equals(edgeSource)))
            {
                edgeSource = shortestEdge.getTarget().toString();
            }
            else
            {
                edgeSource = shortestEdge.getSource().toString();
            }
            visitedVertexes.add(edgeSource);
            neighbourEdges.clear();
        }
       if(visitedVertexes.size() == graph.getjGraph().vertexSet().size())
       {
        
          Set<GKAEdge> edgesFromLast = new HashSet<>(graph.getjGraph().edgesOf(visitedVertexes.get(visitedVertexes.size() -1)));
          Set<GKAEdge> edgesFromSource = new HashSet<>(graph.getjGraph().edgesOf(source));
          
          for(GKAEdge lastEdge : edgesFromLast)
          {
              if(lastEdge.getTarget().equals(source) || lastEdge.getSource().equals(source))
              {
                  edgesToSource.add(lastEdge);
              }
          }  
          
          for(GKAEdge lastEdge : edgesFromSource)
          {
              if(lastEdge.getTarget().equals(visitedVertexes.get(visitedVertexes.size() -1)) || lastEdge.getSource().equals(visitedVertexes.get(visitedVertexes.size() -1)))
              {
                  edgesToSource.add(lastEdge);
              }
          }
          
          Iterator<GKAEdge> i = edgesToSource.iterator();
          
          if(i.hasNext())
          {
              edgeToSource = (GKAEdge) i.next();
          }
          while(i.hasNext())
          {
              GKAEdge currentLastEdge = (GKAEdge) i.next();
              if(currentLastEdge.getWeight() < edgeToSource.getWeight())
              {
                  edgeToSource = currentLastEdge;
              }
          }
       }
       shortestEdges.add(edgeToSource);

       for(GKAEdge edge : shortestEdges)
       {
           String start = edge.getSource().toString();
           String target = edge.getTarget().toString();
           String name = null; 
           Double weight = edge.getWeight();
           length = length + weight;
           tour.addEdge(start, target, name, weight);
       }
       way = visitedVertexes;
       return tour;
    }
    
    public Set<GKAEdge> getEdgeSet() 
    {
        return Collections.unmodifiableSet(edgeSet);
    }
    
    private GKAGraphInterface generateDuplicatedGraph(NearestNeighbour graph)
    {
        GKAGraphInterface dupGraph = GKAGraphInterface.newGraph(GraphType.DirectedWeighted);
        for(GKAEdge edge: graph.getEdgeSet())
        {
            dupGraph.addEdge(edge.getSource().toString(), edge.getTarget().toString(), null, edge.getWeight());
            dupGraph.addEdge(edge.getTarget().toString(), edge.getSource().toString(), null, edge.getWeight());
        }
        return dupGraph;
    }
    
    public GKAGraphInterface getNearestNeighbourTour(String source) 
    {
        return tour;
    }
    
}
