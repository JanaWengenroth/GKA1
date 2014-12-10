package GKA.GUI;

import java.awt.Color;

import GKA.Graph.GKAGraphInterface;

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
	private GKAFloydWarshallButton floydWarshallButton;
	private GKAFordFulkersonButton fordFulkersonButton;
	private GKAEdmondKarpButton edmondKarpButton;
	private GenerateRandomButton generateRandomButton;
	private GKAMinimumSpanningTreeButton minimumSpanningTreeButton;
	private GKAMSTHeuristicTreeButton mstHeuristicTreeButton;
	
	public GKAGraphMenuPanel(int width, int height, int posX, int posY, GKAGraphInterface graph) 
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
		if (graph.isWeighted()){
			dijkstraButton = new GKADijkstraButton(width - 10, 25, 5, 155);
			add(dijkstraButton);
			floydWarshallButton = new GKAFloydWarshallButton(width - 10, 25, 5, 185);
			add(floydWarshallButton);
			if(graph.isDirected()){
				fordFulkersonButton = new GKAFordFulkersonButton(width - 10, 25, 5, 215);
				add(fordFulkersonButton);
				edmondKarpButton = new GKAEdmondKarpButton(width -10, 25, 5, 245);
				add(edmondKarpButton);
			}else{
				minimumSpanningTreeButton = new GKAMinimumSpanningTreeButton(width - 10, 25, 5, 215);
				add(minimumSpanningTreeButton);
				mstHeuristicTreeButton = new GKAMSTHeuristicTreeButton(width - 10, 25, 5, 245);
				add(mstHeuristicTreeButton);
			}
			
		}
		generateRandomButton = new GenerateRandomButton(width - 10, 25, 5, height-70);
		add(generateRandomButton);
		cancelButton = new GKACancelButton(width - 10, 25, 5, height-40);
		add(cancelButton);
	}

}
