package GKA.GUI;

import java.awt.Color;

public class GKAMenuPanel extends GKAPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 678622248231157261L;
	
	private GKAOpenButton openButton = new GKAOpenButton();
	private GKASaveButton saveButton = new GKASaveButton();
	private GKANewDirectedButton newDirectedButton = new GKANewDirectedButton();
	private GKANewDirectedWeightedButton newDirectedWeightedButton = new GKANewDirectedWeightedButton();
	private GKANewUndirectedButton newUndirectedButton = new GKANewUndirectedButton();
	private GKANewUndirectedWeightedButton newUndirectedWeightedButton = new GKANewUndirectedWeightedButton();
	
	public GKAMenuPanel(int width, int height, int posX, int posY) 
	{
		super(width, height, posX, posY, Color.blue);
		setLayout(null);
		add(openButton);
		add(saveButton);
		add(newDirectedButton);
		add(newDirectedWeightedButton);
		add(newUndirectedButton);
		add(newUndirectedWeightedButton);
	}

}
