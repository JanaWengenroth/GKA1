package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MouseControler.MouseBroadSearchHandler;
import GKA.Controler.MouseControler.MouseDijkstraHandler;
import GKA.Controler.MouseControler.MouseEventControler;
import GKA.Controler.MouseControler.MouseFordFulkersonHandler;

public class GKAFordFulkersonButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8786171296007640450L;
	
	public GKAFordFulkersonButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "FordFulkerson");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MouseEventControler.newEvent(new MouseFordFulkersonHandler());
		}
	}
}
