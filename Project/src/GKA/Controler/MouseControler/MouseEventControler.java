package GKA.Controler.MouseControler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GKA.Controler.MainControler;
import GKA.Graph.GKAGraphInterface;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;

public class MouseEventControler {
	private static MouseEventInterface runningEvent;
	
	public static MouseAdapter getMouseAdapter(mxGraphComponent graphComponent, GKAGraphInterface graph){
		return new MouseAdapter()
		{
		
			public void mouseReleased(MouseEvent e)
			{
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());
				
				if (cell != null && cell instanceof mxCell)
				{
					runEvent((mxCell) cell);
				}
			}
		};
		
	}
	public static void newEvent(MouseEventInterface mouseEvent){
		if (runningEvent == null || runningEvent.isFinished()){
			runningEvent = mouseEvent;
		}else{
			runningEvent.cancelOperation();
			runningEvent = mouseEvent;
		}
	}
	private static void runEvent(mxCell cell){
		if(runningEvent != null && !runningEvent.isFinished()){
			runningEvent.addEventObject(cell);
		}
	}
	public static void cancel() {
		runningEvent.cancelOperation();
		MainControler.sendMessage("Canceled Operation");
		
	}
}
