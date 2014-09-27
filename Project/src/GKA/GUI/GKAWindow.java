package GKA.GUI;


import javax.swing.JFrame;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;

public class GKAWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -681994194520141257L;
	public static String version = "0.0.1";
	private GKAMenuPanel menuePanel;
	private GKAPanel mainPanel;
	private int menuePanelHeight = 35;
	public GKAWindow()
	{
		super("GKA Project 1 -- Version: " + version);
		setLayout(null);
		setVisible(true);
		setSize(1000, 700);
		initPanels();
	}
	
	private void initPanels()
	{	
		menuePanel = new GKAMenuPanel(getWidth(),menuePanelHeight,0,0);
		add(menuePanel);		
	}
	public void showMainPanel(ListenableGraph<String,DefaultEdge> graph){
		if (mainPanel != null)
			remove(mainPanel);
		mainPanel = new GKAMainPanel(getWidth(),getHeight() - menuePanelHeight, 0, menuePanelHeight, graph);
		add(mainPanel);
		repaint();
	}
}
