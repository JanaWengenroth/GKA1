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
	private GKACancelButton cancelButton;
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
		cancelButton = new GKACancelButton(width - 10, 25, 5, height - 80);
		add(cancelButton);
	}

}
