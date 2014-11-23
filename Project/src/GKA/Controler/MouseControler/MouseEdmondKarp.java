package GKA.Controler.MouseControler;

import GKA.Controler.MainControler;

import com.mxgraph.model.mxCell;

public class MouseEdmondKarpHandler extends MouseSearchHandler implements MouseEventInterface{
    
    public MouseEdmondKarpHandler() {
        MainControler.sendMessage("Please select SourceVertex.");
    }
    
    @Override
    public void addEventObject(mxCell eventObj) {
        if (eventObj.isVertex()){
            if (source == null || source.isEmpty()){
                source = eventObj.getValue().toString();
                MainControler.sendMessage("Please select TargetVertex.");
            }else{
                MainControler.edmondKarp(source, eventObj.getValue().toString());
                setFinished(true);
            }
        }
    }

    

}
