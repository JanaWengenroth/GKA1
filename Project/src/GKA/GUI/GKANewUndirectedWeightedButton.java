package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MainControler;

public class GKANewUndirectedWeightedButton extends GKAButton 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1685392608382321985L;
	
	public GKANewUndirectedWeightedButton() {
		super(230, 25, 740, 5, "New undirected weighted Graph");
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this){
			MainControler.newUndirectedWeigthedGraph();
		}
	}
}
