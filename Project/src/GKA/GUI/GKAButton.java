package GKA.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import GKA.Controler.MainControler;

public class GKAButton extends JButton implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4183121297303364447L;
	public GKAButton(int width, int height, int posX, int posY, String name)
	{
		super(name);
		setLayout(null);
		setSize(width, height);
		setLocation(posX, posY);
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			MainControler.sendMessage("Not Yet Implemented");
		
	}
	
}
