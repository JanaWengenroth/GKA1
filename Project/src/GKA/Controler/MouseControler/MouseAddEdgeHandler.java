package GKA.Controler.MouseControler;

import javax.swing.JOptionPane;
import javax.xml.ws.spi.Invoker;

import GKA.Controler.MainControler;

import com.mxgraph.model.mxCell;

public class MouseAddEdgeHandler implements MouseEventInterface{
	
	private boolean isFinished = false;
	private String source;
	public MouseAddEdgeHandler() {
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
				String edgeName = JOptionPane.showInputDialog("Please enter EdgeName.");
				if (edgeName == null){
					return;
				}else if (edgeName.isEmpty()){
					edgeName = null;
				}else if(!edgeName.matches("[\\p{L}|[0-9]]+")){
					MainControler.sendMessage("Please Enter a correct VertexName.");
					return;
				}
				String weightString = null;
				if (MainControler.getGraph().isWeighted()){
					weightString = JOptionPane.showInputDialog("Please enter the EdgeWeight.");
					if (weightString == null){
						return;
					}else if (weightString.isEmpty()){
						MainControler.sendMessage("Please Enter a a weight.");
						return;
					}else if(!weightString.matches("[0-9]+([.]?[0-9]*)?")){
						MainControler.sendMessage("Please Enter a correct weight.");
						return;
					}
				}
				
				Double weight = weightString == null ? null : Double.valueOf(weightString);
				
				
				if(MainControler.addEdge(source, eventObj.getValue().toString(), edgeName, weight)){
					//MainControler.sendMessage("Edge \"(" + source + " - " + eventObj.getValue().toString() + ")\" was added.");
				}else{
					MainControler.sendMessage("Unable to add Edge \"(" + source + " - " + eventObj.getValue().toString() + ")\"!");
				}
				setFinished(true);
			}
		}
	}

	

}
