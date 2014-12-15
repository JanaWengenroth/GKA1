package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MainControler;
import GKA.Controler.MouseControler.MouseAddEdgeHandler;
import GKA.Controler.MouseControler.MouseEventControler;
import GKA.Controler.MouseControler.MouseNearestNeighbourHandler;
import GKA.Controler.MouseControler.MouseRemoveHandler;

public class GKANearestNeighbourButton extends GKAButton{

    /**
     * 
     */
    private static final long serialVersionUID = -8786171296007640450L;
    
    public GKANearestNeighbourButton(int width, int height, int posX, int posY) {
        super(width, height, posX, posY, "NearestNeighbour");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this){
            MouseEventControler.newEvent(new MouseNearestNeighbourHandler());
        }
    }
}
