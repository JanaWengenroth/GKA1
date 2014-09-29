package GKA.Graph;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

import GKA.Controler.MainControler;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

class GKAGraph implements GKAGraphInterface {
	/**
	 * Creates a new Graph
	 * 
	 * @param type - specifies the type of the Graph
	 * @return
	 */
	static GKAGraphInterface newGraph(GraphType type){
		return new GKAGraph(type);
	}
	
	private final ListenableGraph<String, GKAEdge> jGraph;
	private final JGraphXAdapter<String,GKAEdge> mxgraph;
	private final GraphType type;
	private GKAGraph(GraphType type){
		this.type = type;
		
		// Choose if it will be directed or undirected
		if (isDirected()){
			jGraph = new ListenableDirectedGraph<>(GKAEdge.class);
		}else{
			jGraph = new ListenableUndirectedGraph<>(GKAEdge.class);
		}
		
		// JGXAdapter for showing the Graph in Swing
		mxgraph = new JGraphXAdapter<>(getjGraph());
		
		// Changing EdgeStyle when is undirected
		if (!isDirected()){
			mxgraph.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW, "none");
		}
	}
	
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#getjGraph()
	 */
	@Override
	public ListenableGraph<String, GKAEdge> getjGraph() {
		return jGraph;
	}
	
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#getMxgraph()
	 */
	@Override
	public mxGraph getMxgraph() {
		return mxgraph;
	}
	private GraphType getType() {
		return type;
	}
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#isDirected()
	 */
	@Override
	public boolean isDirected(){
		return getType().isDirected();
	}
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#isWeighted()
	 */
	@Override
	public boolean isWeighted(){
		return getType().isWeighted();
	}
	
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#addVertex(java.lang.String)
	 */
	@Override
	public boolean addVertex(String vertexName){
		if(vertexName == null){
			MainControler.sendMessage("Null is not exepted as VertexName!");
			return false;
		}
		if(!getjGraph().addVertex(vertexName)){
			MainControler.sendMessage("Vertex \"" + vertexName + "\" already exists!");
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#addEdge(java.lang.String, java.lang.String, java.lang.String, java.lang.Double)
	 */
	@Override
	public boolean addEdge(String source, String target, String name, Double weight){
		if (source == null){
			MainControler.sendMessage("Please add a SourceVertex!");
			return false;
		}else if(target == null){
			MainControler.sendMessage("Please add a TargetVertex!");
			return false;
		}
		if (weight == null && isWeighted()){
			MainControler.sendMessage("Please add a Weight for weighted Graphs!");
			return false;
		} else if( isWeighted() && weight < 0.0){
			MainControler.sendMessage("A weight below 0 is not allowed!");
			return false;
		}
		if (weight != null && !isWeighted()){
			MainControler.sendMessage("Weights have no effects to unweighted Graphs!");
		}
		if(!getjGraph().containsVertex(source)){
			addVertex(source);
		}else if (!getjGraph().containsVertex(target)){
			addVertex(target);
		}
		try {
			GKAEdge edge = jGraph.addEdge(source, target);
			if(edge == null){
				MainControler.sendMessage("A edge from \"" + source + "\" to \"" + target + "\" aready exists!");
				return false;
			}
			edge.setName(name);
			edge.setWeight(weight);
			MainControler.sendMessage("Edge \"" + edge.toString() + "\" was set.");
		} catch (Exception e) {
			MainControler.sendMessage("Adding edge from \"" + source + "\" to \"" + target + "\" failed by \n" +
					e.getMessage());
			return false;
		}
		return true;
		
	}
	
	@Override
	public void setCircleLayout(){
		mxCircleLayout layout = new mxCircleLayout(getMxgraph());
        layout.execute(getMxgraph().getDefaultParent());
	}
}
