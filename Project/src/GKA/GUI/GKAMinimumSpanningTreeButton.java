package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MainControler;
import GKA.Controler.MouseControler.MouseAddEdgeHandler;
import GKA.Controler.MouseControler.MouseEventControler;

public class GKAMinimumSpanningTreeButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8786171296007640450L;
	
	public GKAMinimumSpanningTreeButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "MST");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			MainControler.minimumSpanningTree();
		}
	}
}
