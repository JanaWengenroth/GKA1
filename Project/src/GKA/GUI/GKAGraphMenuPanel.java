package GKA.GUI;

import java.awt.Color;

public class GKAGraphMenuPanel extends GKAPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5691021885027802967L;
	
	private GKAAddVertexButton addVertex;
	private GKAAddEdgeButton addEdge;
	public GKAGraphMenuPanel(int width, int height, int posX, int posY) 
	{
		super(width, height, posX, posY, Color.GREEN);
		setLayout(null);
		addVertex = new GKAAddVertexButton(width - 10, 25, 5, 5);
		add(addVertex);
		addEdge = new GKAAddEdgeButton(width - 10, 25, 5, 35);
		add(addEdge);
	}

}
