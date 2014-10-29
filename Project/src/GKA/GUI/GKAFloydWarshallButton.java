package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MouseControler.MouseBroadSearchHandler;
import GKA.Controler.MouseControler.MouseDijkstraHandler;
import GKA.Controler.MouseControler.MouseEventControler;

public class GKAFloydWarshallButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8786171296007640450L;
	
	public GKAFloydWarshallButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Dijkstra");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MouseEventControler.newEvent(new MouseDijkstraHandler());
		}
	}
}
