package GKA.GUI;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JOptionPane;

import org.jgrapht.generate.GraphGenerator;

import GKA.Controler.MainControler;
import GKA.Graph.GKAGraphInterface;

public class GenerateRandomButton extends GKAButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 983926603804344475L;
	
	public GenerateRandomButton(int width, int height, int posX, int posY) {
		super(width, height, posX, posY, "Generate Random Graph");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			String inputVertexNr = JOptionPane.showInputDialog("Please enter VertexNumber");
			if (inputVertexNr == null){
				return;
			}else if (inputVertexNr.isEmpty()){
				MainControler.sendMessage("Please Enter a VertexNumber.");
				return;
			}else if(!inputVertexNr.matches("[0-9]+")){
				MainControler.sendMessage("Please Enter a correct VertexNumber.");
				return;
			}
			String inputEdgeNr = JOptionPane.showInputDialog("Please enter VertexNumber");
			if (inputEdgeNr == null){
				return;
			}else if (inputEdgeNr.isEmpty()){
				MainControler.sendMessage("Please Enter a VertexNumber.");
				return;
			}else if(!inputEdgeNr.matches("[0-9]+")){
				MainControler.sendMessage("Please Enter a correct VertexNumber.");
				return;
			}
			GKAGraphInterface.newGraphBigNet(Integer.valueOf(inputVertexNr), Integer.valueOf(inputEdgeNr)).
			saveGraph(new File("..\\aufgabe1Bsp\\big_" + inputVertexNr + "_" + inputEdgeNr + ".gka"), true);;
		}
	}
		
	
}
