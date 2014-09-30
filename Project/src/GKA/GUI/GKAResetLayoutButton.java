package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MainControler;

public class GKAResetLayoutButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8786171296007640450L;
	
	public GKAResetLayoutButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Reset Layout");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MainControler.setLayout();
		}
		
	}
}
