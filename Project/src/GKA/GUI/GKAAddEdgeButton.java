package GKA.GUI;

import java.awt.event.ActionEvent;
import GKA.Controler.MouseControler.MouseAddEdgeHandler;
import GKA.Controler.MouseControler.MouseEventControler;

public class GKAAddEdgeButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8786171296007640450L;
	
	public GKAAddEdgeButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Add Edge");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MouseEventControler.newEvent(new MouseAddEdgeHandler());
		}
	}
}
