package GKA.Controler.MouseControler;

import javax.swing.JOptionPane;
import javax.xml.ws.spi.Invoker;

import GKA.Controler.MainControler;

import com.mxgraph.model.mxCell;

public class MouseBroadSearchHandler implements MouseEventInterface{
	
	private boolean isFinished = false;
	private String source;
	public MouseBroadSearchHandler() {
		MainControler.sendMessage("Please select SourceVertex.");
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
			if (source == null || source.isEmpty()){
				source = eventObj.getValue().toString();
				MainControler.sendMessage("Please select TargetVertex.");
			}else{
				MainControler.shortesPathBroad(source, eventObj.getValue().toString());
				setFinished(true);
			}
		}
	}

	

}
