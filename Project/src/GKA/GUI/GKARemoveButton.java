package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MouseControler.MouseEventControler;
import GKA.Controler.MouseControler.MouseRemoveHandler;

public class GKARemoveButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8786171296007640450L;
	
	public GKARemoveButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Remove");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MouseEventControler.newEvent(new MouseRemoveHandler());
		}
		
	}
}
