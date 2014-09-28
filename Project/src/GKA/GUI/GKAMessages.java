package GKA.GUI;

import java.awt.Color;

import javax.swing.JTextArea;

public class GKAMessages extends JTextArea{
	public GKAMessages(int width, int height, int posX, int posY){
		super();
		setSize(width, height);
		setLocation(posX, posY);
		setEnabled(false);
	}
	public void sendMessage(String msg){
		append(msg + "\n");
	}
}
