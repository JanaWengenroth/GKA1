package GKA.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GKAMessages extends GKAPanel{
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane();
	public GKAMessages(int width, int height, int posX, int posY){
		super(width, height, posX, posY);	
		setLayout(null);
		setBackground(Color.MAGENTA);
		scrollPane.setViewportView(textArea);
		scrollPane.setSize(getWidth(), getHeight());
		add(scrollPane,BorderLayout.CENTER);
	}
	public void sendMessage(String msg){
		textArea.append(msg + "\n");
	}
}
