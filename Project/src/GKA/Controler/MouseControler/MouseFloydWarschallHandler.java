package GKA.Controler.MouseControler;

import GKA.Controler.MainControler;

import com.mxgraph.model.mxCell;

public class MouseFloydWarschallHandler extends MouseSearchHandler implements MouseEventInterface{
	
	public MouseFloydWarschallHandler() {
		MainControler.sendMessage("Please select SourceVertex.");
	}
	
	@Override
	public void addEventObject(mxCell eventObj) {
		if (eventObj.isVertex()){
			if (source == null || source.isEmpty()){
				source = eventObj.getValue().toString();
				MainControler.sendMessage("Please select TargetVertex.");
			}else{
				MainControler.floydWarschall(source, eventObj.getValue().toString());
				setFinished(true);
			}
		}
	}

	

}
