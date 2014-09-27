package GKA.GUI;

import java.awt.Color;

import javax.swing.JPanel;

public class GKAPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6495772062755771565L;
	public GKAPanel(int width, int height, int posX, int posY) 
	{
		setLayout(null);
		setSize(width, height);
		setLocation(posX, posY);
	}
	public GKAPanel(int width, int height, int posX, int posY, Color bgColor) 
	{
		setSize(width, height);
		setLocation(posX, posY);
		setBackground(bgColor);
	}
}
