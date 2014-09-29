package GKA.GUI;

import java.awt.Color;
import java.util.Hashtable;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;

import GKA.Graph.GKAGraphInterface;

import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;

public class GKAMainPanel extends GKAPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5691021885027802967L;

	private GKAGraphMenuPanel graphMenuePanel;
	private GKAGraphPanel graphPanel;
	private int graphMenueSize = 200;
	public GKAMainPanel(int width, int height, int posX, int posY, GKAGraphInterface graph) 
	{
		super(width, height, posX, posY, Color.BLACK);
		setLayout(null);
		graphMenuePanel = new GKAGraphMenuPanel(graphMenueSize, getHeight(), getWidth() - graphMenueSize, 0);
		add(graphMenuePanel);
		graphPanel = new GKAGraphPanel(getWidth() - graphMenueSize, getHeight(), 0, 0, graph);
		add(graphPanel);
	}

}
