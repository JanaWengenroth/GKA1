package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MainControler;

public class GKAAddEdgeButton extends GKAButton{

	public GKAAddEdgeButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Add Edge");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MainControler.showGraph();
		}
		
	}
}
