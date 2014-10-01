package GKA.GUI;

import java.awt.event.ActionEvent;
import GKA.Controler.MouseControler.MouseAddEdgeHandler;
import GKA.Controler.MouseControler.MouseEventControler;

public class GKACancelButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3851766794244923370L;

	
	public GKACancelButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Cancel");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MouseEventControler.cancel();
		}
	}
}
