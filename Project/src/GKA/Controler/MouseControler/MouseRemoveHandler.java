package GKA.Controler.MouseControler;

import GKA.Controler.MainControler;

import com.mxgraph.model.mxCell;

public class MouseRemoveHandler implements MouseEventInterface{
	
	private boolean isFinished = false;
	
	public MouseRemoveHandler() {
		MainControler.sendMessage("Please select node to delete.");
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
		if (eventObj.isVertex() || eventObj.isEdge()){
			if(eventObj.isVertex()){
				if (MainControler.removeVertex(eventObj.getValue().toString())){
					MainControler.sendMessage("Vertex \"" + eventObj.getValue().toString() + "\" was removed.");
				}else{
					MainControler.sendMessage("Vertex \"" + eventObj.getValue().toString() + "\" can't removed.");
				}
			}else if(eventObj.isEdge()){
				if (MainControler.removeEdge(eventObj.getSource().getValue().toString(), eventObj.getTarget().getValue().toString())){
					MainControler.sendMessage("Edge \"" + eventObj.getValue().toString() + "\" was removed.");
				}else{
					MainControler.sendMessage("Edge \"" + eventObj.getValue().toString() + "\" can't removed.");
				}
			}
			setFinished(true);
		}
	}

	

}
