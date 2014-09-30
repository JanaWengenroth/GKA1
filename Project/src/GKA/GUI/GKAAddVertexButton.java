package GKA.GUI;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import GKA.Controler.MainControler;

public class GKAAddVertexButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 983926603804344475L;
	
	public GKAAddVertexButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Add Vertex");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			String inputString = JOptionPane.showInputDialog("Please enter VertexName.");
			if (inputString == null){
				return;
			}else if (inputString.isEmpty()){
				MainControler.sendMessage("Please Enter a VertexName.");
				return;
			}else if(!inputString.matches("[a-zA-Z]+[a-zA-Z0-9]*")){
				MainControler.sendMessage("Please Enter a correct VertexName.");
				return;
			}
			MainControler.addVertex(inputString);
		}
	}
		
	
}
