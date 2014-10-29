package GKA.GUI;

import java.awt.Color;

public class GKAGraphMenuPanel extends GKAPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5691021885027802967L;
	
	private GKAAddVertexButton addVertex;
	private GKAAddEdgeButton addEdge;
	private GKAResetLayoutButton resetLayout;
	private GKARemoveButton removeButton;
	private GKAShortestPathBroadButton broadButton;
	private GKACancelButton cancelButton;
	private GKADijkstraButton dijkstraButton;
	public GKAGraphMenuPanel(int width, int height, int posX, int posY) 
	{
		super(width, height, posX, posY, Color.GREEN);
		setLayout(null);
		resetLayout = new GKAResetLayoutButton(width - 10, 25, 5, 5);
		add(resetLayout);
		addVertex = new GKAAddVertexButton(width - 10, 25, 5, 35);
		add(addVertex);
		addEdge = new GKAAddEdgeButton(width - 10, 25, 5, 65);
		add(addEdge);
		removeButton = new GKARemoveButton(width - 10, 25, 5, 95);
		add(removeButton);
		broadButton = new GKAShortestPathBroadButton(width - 10, 25, 5, 125);
		add(broadButton);
		cancelButton = new GKACancelButton(width - 10, 25, 5, height-40);
		add(cancelButton);
		dijkstraButton = new GKADijkstraButton(width - 10, 25, 5, 155);
        add(dijkstraButton);
	}

}
