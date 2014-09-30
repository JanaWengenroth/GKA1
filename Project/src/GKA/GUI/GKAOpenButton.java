package GKA.GUI;

import java.awt.event.ActionEvent;

public class GKAOpenButton extends GKAButton 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7102577864760108095L;
	private final GKAFileChooser fileChooser = new GKAFileChooser();
	public GKAOpenButton() {
		super(70, 25, 5, 5, "Open");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			int retVal = fileChooser.showOpenDialog(this);
			if (retVal == GKAFileChooser.APPROVE_OPTION){
				System.out.println(fileChooser.getSelectedFile());
				// ToDo: Real opening
			}
		}
		
	}
}
