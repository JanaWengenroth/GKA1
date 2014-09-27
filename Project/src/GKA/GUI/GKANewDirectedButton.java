package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MainControler;

public class GKANewDirectedButton extends GKAButton 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -556117185605916954L;
	public GKANewDirectedButton() {
		super(170, 25, 155, 5, "New directed Graph");
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MainControler.newDirectedGraph();
		}
	}
}
