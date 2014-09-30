package GKA.GUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GKA.Controler.MouseControler.MouseEventControler;
import GKA.Graph.GKAGraphInterface;

import com.mxgraph.swing.mxGraphComponent;

public class GKAGraphPanel extends GKAPanel
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
		graphComponent.setConnectable(false);
		graphComponent.setDragEnabled(false);
		graphComponent.getGraphControl().addMouseListener(MouseEventControler.getMouseAdapter(graphComponent, graph));
		add(graphComponent);
	}

}
