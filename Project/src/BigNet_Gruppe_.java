import GKA.Graph.GKAGraphInterface;
import GKA.Graph.GraphType;
//import GKA.Graph.GKAGraphTest.RandomGraph;



public class BigNet_Gruppe_
{
    private GKAGraphInterface graph;
    private String waySource;
    private String wayTarget;
   

    
    BigNet_Gruppe_(GKAGraphInterface graph, String waySource, String wayTarget)
    {
       this.setGraph(graph);
       this.setWaySource(waySource);
       this.setWayTarget(wayTarget);
    }

    public GKAGraphInterface getGraph()
    {
        return graph;
    }
    
    private void setGraph(GKAGraphInterface graph)
    {
        this.graph = graph;
    }
    
    public String getWaySource()
    {
        return waySource;
    }

    private void setWaySource(String waySource)
    {
        this.waySource = waySource;
    }

    public String getWayTarget()
    {
        return wayTarget;
    }

    private void setWayTarget(String wayTarget)
    {
        this.wayTarget = wayTarget;
    }


	public BigNet_Gruppe_ generateGraph(int vertexAnzahl, int edgeAnzahl, String source, String sink)
	{
	    GKAGraphInterface retval = GKAGraphInterface.newGraph(GraphType.DirectedWeighted);
	    for(int i = 0; i < vertexAnzahl -2; i++)
	    {
	        retval.addVertex(String.valueOf(i));
	    }
	    retval.addVertex(source);
	    retval.addVertex(sink);
	    retval.addEdge(source, String.valueOf(0), null, (double)((int) (Math.random() * edgeAnzahl)));   
	    retval.addEdge((String.valueOf((vertexAnzahl/2) - 1)), sink, null, (double)((int) (Math.random() * edgeAnzahl)));
	    for(int i = 0; i < ((vertexAnzahl -2) / 2) - 1; i++)
	    {
	        retval.addEdge(String.valueOf(i), String.valueOf(i+1), null, (double)((int) (Math.random() * edgeAnzahl)));
	    }
	
	    for(int i = 0 ; i < edgeAnzahl; i++)
	    {
	        int edgeTarget = (int) (Math.random() * (vertexAnzahl - 1));
	        int edgeSource = (int) (Math.random() * (vertexAnzahl - 1));
	        retval.addEdge(String.valueOf(edgeSource), String.valueOf(edgeTarget), null, ((double)((int) (Math.random() * edgeAnzahl)) + (int)((vertexAnzahl / 2) + 1) * Math.random()));
	    }
	   
	    return new BigNet_Gruppe_(retval, source, sink);
	}
}
