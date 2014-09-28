package GKA.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.jgraph.JGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;

import GKA.Controler.MainControler;

import com.mxgraph.analysis.mxGraphProperties;
import com.mxgraph.io.mxStylesheetCodec;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

public class GKAGraphPanel extends GKAPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 678622248231157261L;
	
	private ListenableGraph<String, DefaultEdge> graph;
	
	public GKAGraphPanel(int width, int height, int posX, int posY, ListenableGraph<String, DefaultEdge> graph) 
	{
		super(width, height, posX, posY, Color.RED);
		setLayout(null);
		this.graph = graph;
		JGraphXAdapter<String, DefaultEdge> jgxAdapter = new JGraphXAdapter<>(graph);
		jgxAdapter.setAllowDanglingEdges(false);
		jgxAdapter.setEdgeLabelsMovable(false);
		if (graph instanceof UndirectedGraph){
			jgxAdapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW, "none");
		}
		mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
		graphComponent.setSize(getWidth(),getHeight());
		add(graphComponent);
		mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
	}   
}
