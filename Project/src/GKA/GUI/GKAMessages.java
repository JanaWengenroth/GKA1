package GKA.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GKAMessages extends GKAPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3931067560175491718L;
	
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
