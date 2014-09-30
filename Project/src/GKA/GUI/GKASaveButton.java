package GKA.GUI;

import java.awt.event.ActionEvent;

public class GKASaveButton extends GKAButton 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6289110092253949214L;
	
	private final GKAFileChooser fileChooser = new GKAFileChooser();
	public GKASaveButton() {
		super(70, 25, 80, 5, "Save");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			int retVal = fileChooser.showOpenDialog(this);
			if (retVal == GKAFileChooser.APPROVE_OPTION){
				System.out.println(fileChooser.getSelectedFile());
				// ToDo: Real saving
			}
		}
		
	}
}
