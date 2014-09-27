package GKA.GUI;

import java.awt.Color;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

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
		JGraphXAdapter<String, DefaultEdge> jgxAdapter = new JGraphXAdapter<>(graph);
		mxGraphComponent mxG = new mxGraphComponent(jgxAdapter);
		mxG.setSize(getWidth(), getHeight());
		add(mxG);
		//mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        //layout.execute(jgxAdapter.getDefaultParent());
	}

}
