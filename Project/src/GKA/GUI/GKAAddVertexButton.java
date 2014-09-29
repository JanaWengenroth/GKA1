package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MainControler;

public class GKAAddVertexButton extends GKAButton{

	public GKAAddVertexButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Add Vertex");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MainControler.addVertex();
		}
	}
		
	
}
