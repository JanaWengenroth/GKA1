package GKA.Controler.MouseControler;

import GKA.Controler.MainControler;

import com.mxgraph.model.mxCell;

public class MouseNearestNeighbourHandler implements MouseEventInterface{
    
    private boolean isFinished = false;
    
    public MouseNearestNeighbourHandler() {
        MainControler.sendMessage("Please select node to start.");
    }
    
    @Override
    public void cancelOperation() {
        setFinished(true);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
    private void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    @Override
    public void addEventObject(mxCell eventObj) {
        if (eventObj.isVertex()){
            MainControler.nearestNeighbour(eventObj.getValue().toString());
            setFinished(true);
        }
    }

    

}
