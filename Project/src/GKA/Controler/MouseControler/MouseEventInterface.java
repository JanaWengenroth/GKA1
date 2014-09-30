package GKA.Controler.MouseControler;

import com.mxgraph.model.mxCell;

public interface MouseEventInterface {
	public void cancelOperation();
	
	public boolean isFinished();
	
	public void addEventObject(mxCell eventObj);
}
