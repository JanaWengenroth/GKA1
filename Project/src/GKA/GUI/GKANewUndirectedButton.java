package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MainControler;

public class GKANewUndirectedButton extends GKAButton 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1685392608382321985L;
	
	public GKANewUndirectedButton() {
		super(170, 25, 330, 5, "New undirected Graph");
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this){
			MainControler.newUndirectedGraph();
		}
	}
}
