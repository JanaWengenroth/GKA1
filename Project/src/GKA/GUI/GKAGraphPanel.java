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
import GKA.Graph.GKAGraphInterface;

import com.mxgraph.analysis.mxGraphProperties;
import com.mxgraph.io.mxStylesheetCodec;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

public class GKAGraphPanel extends GKAPanel implements mxIEventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 678622248231157261L;
	
	private GKAGraphInterface graph;
	
	public GKAGraphPanel(int width, int height, int posX, int posY, GKAGraphInterface graph) 
	{
		super(width, height, posX, posY, Color.RED);
		setLayout(null);
		this.graph = graph;
		mxGraphComponent graphComponent = new mxGraphComponent(graph.getMxgraph());
		graphComponent.setSize(getWidth(),getHeight());
		add(graphComponent);
	}

	@Override
	public void invoke(Object arg0, mxEventObject arg1) {
		System.out.println(arg1.getName());
		System.out.println(arg1.getProperties());
		System.out.println(arg1.getProperty("edge") instanceof mxCell ? (((mxCell) arg1.getProperty("edge")).isEdge() ? ((mxCell) arg1.getProperty("edge")).getSource():null): "noCell");
		
	}   
}
