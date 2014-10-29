
package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MouseControler.MouseBroadSearchHandler;
import GKA.Controler.MouseControler.MouseEventControler;

public class GKAShortestPathBroadButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8786171296007640450L;
	
	public GKAShortestPathBroadButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Broad Search");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MouseEventControler.newEvent(new MouseBroadSearchHandler());
		}
	}
}
