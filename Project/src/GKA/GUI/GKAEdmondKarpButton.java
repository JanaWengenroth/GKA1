package GKA.GUI;

import java.awt.event.ActionEvent;

import GKA.Controler.MouseControler.MouseBroadSearchHandler;
import GKA.Controler.MouseControler.MouseDijkstraHandler;
import GKA.Controler.MouseControler.MouseEventControler;
import GKA.Controler.MouseControler.MouseEdmondKarpHandler;

public class GKAEdmondKarpButton extends GKAButton{

    /**
     * 
     */
    private static final long serialVersionUID = -8786171296007640450L;
    
    public GKAEdmondKarpButton(int width, int height, int posX, int posY) {
        super(width, height, posX, posY, "EdmondKarp");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this){
            MouseEventControler.newEvent(new MouseEdmondKarpHandler());
        }
    }
}

