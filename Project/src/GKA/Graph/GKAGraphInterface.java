package GKA.Graph;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.jgrapht.ListenableGraph;

import GKA.Controler.MessageReceiver;
import GKA.Controler.MessageSender;

import com.mxgraph.view.mxGraph;

public interface GKAGraphInterface extends MessageSender{

	public static GKAGraphInterface newGraph(GraphType type){
		return GKAGraph.newGraph(type);
	}
	public static GKAGraphInterface newGraph(File file){
		return GKAGraph.newGraph(file);
	}
	public static GKAGraphInterface newGraphBigNet(int i, int j){
		return GKAGraph.newGraph(i,j);
	}
	
	public abstract ListenableGraph<String, GKAEdge> getjGraph();

	/**
	 * Returns the mxGraph for showing in Swing.
	 * Please notices: All changes in this graph don't have any effect to the Graph
	 * 				   it's only in the GUI.
	 * @return
	 */
	public abstract mxGraph getMxgraph();

	public abstract boolean isDirected();

	public abstract boolean isWeighted();

	/**
	 * Adds a new vertex, Returns false at all errors.
	 * @param vertexName
	 * @return
	 */
	public abstract boolean addVertex(String vertexName);

	/**
	 * 
	 * @param source
	 * @param target
	 * @param name - can be null, in this case the vertexName is "source - target"
	 * @param weight - has to be null at unweighted Graphs and must be set at weighted Graphs
	 * @return
	 */
	public abstract boolean addEdge(String source, String target, String name,
			Double weight);
	
	public abstract void setLayout();
	
	public boolean removeVertex(String vertexName);
	
	public boolean removeEdge(String source, String target);
	
	public void saveGraph(File file, boolean decreapted);
	
	public void colorEdge(GKAEdge edge);
	
	public void colorEdge(Collection<GKAEdge> edges);
	
	public GraphType getType();
	
	/**
	 * Find the shortest way from Source to Target
	 * by using the broad search algorithm
	 * @param source
	 * @param target
	 * @return
	 */
	public List<GKAEdge> shortesPathBroad(String source, String target);
	/**
	 * Find the way with the smallest Weight from Source to Target
	 * @param source
	 * @param target
	 * @return
	 */
	public List<GKAEdge> dijkstra(String source, String target);
	
	public void resetColor();
	
	public List<GKAEdge> floydWarschall(String source, String target);
	
	public void fordFulkerson(String source, String sink);
	
	public void edmondKarp(String source, String sink);
	boolean addEdgeUnsave(String source, String target, String name,
			Double weight);
	public MinimumSpanningTree getMinimumSpanningTree();
	public MSTHeuristic getMSTHeuristic(String startNode);
	
	
}