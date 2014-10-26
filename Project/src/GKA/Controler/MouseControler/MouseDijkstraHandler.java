package GKA.Controler.MouseControler;

import GKA.Controler.MainControler;

import com.mxgraph.model.mxCell;

public class MouseDijkstraHandler extends MouseSearchHandler implements MouseEventInterface{
	
	public MouseDijkstraHandler() {
		MainControler.sendMessage("Please select SourceVertex.");
	}
	
	@Override
	public void addEventObject(mxCell eventObj) {
		if (eventObj.isVertex()){
			if (source == null || source.isEmpty()){
				source = eventObj.getValue().toString();
				MainControler.sendMessage("Please select TargetVertex.");
			}else{
				MainControler.dijkstra(source, eventObj.getValue().toString());
				setFinished(true);
			}
		}
	}

	

}
