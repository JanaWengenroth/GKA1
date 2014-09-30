package GKA.GUI;


import java.awt.Dimension;

import javax.swing.JFrame;

import GKA.Graph.GKAGraphInterface;

public class GKAWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -681994194520141257L;
	public static String version = "0.0.1";
	private GKAMenuPanel menuePanel;
	private GKAPanel mainPanel;
	private GKAMessages messages;
	private int menuePanelHeight = 35;
	private int messageBoxHeight = 100;
	public GKAWindow()
	{
		super("GKA Project 1 -- Version: " + version);
		setLayout(null);
		setVisible(true);
		setSize(1000, 700);
		setPreferredSize(new Dimension(1000, 700));
		setResizable(false);
		initPanels();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initPanels()
	{	
		menuePanel = new GKAMenuPanel(getWidth(),menuePanelHeight,0,0);
		add(menuePanel);		
		messages = new GKAMessages(getWidth(), messageBoxHeight, 0, getHeight() - menuePanelHeight - messageBoxHeight);
		add(messages);
	}
	public void showMainPanel(GKAGraphInterface graph){
		if (mainPanel != null)
			remove(mainPanel);
		mainPanel = new GKAMainPanel(getWidth(),getHeight() - menuePanelHeight - messageBoxHeight, 0, menuePanelHeight, graph);
		add(mainPanel);
		repaint();
	}
	public void sendMessage(String msg){
		messages.sendMessage(msg);
	}
}
