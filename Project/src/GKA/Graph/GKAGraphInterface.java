package GKA.Graph;

import java.io.File;
import java.util.Collection;

import org.jgrapht.ListenableGraph;

import com.mxgraph.view.mxGraph;

public interface GKAGraphInterface {

	public static GKAGraphInterface newGraph(GraphType type){
		return GKAGraph.newGraph(type);
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
	
	public void saveGraph(File file);
	
	public void colorEdge(GKAEdge edge);
	
	public void colorEdge(Collection<GKAEdge> edges);

}