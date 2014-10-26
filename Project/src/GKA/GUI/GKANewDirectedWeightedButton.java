package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MainControler;

public class GKANewDirectedWeightedButton extends GKAButton 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5970849245144274312L;
	
	public GKANewDirectedWeightedButton() {
		super(230, 25, 330, 5, "New directed weigthed Graph");
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MainControler.newDirectedWeightedGraph();;
		}
	}
}
