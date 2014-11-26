package GKA.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GKAFileChooser extends JFileChooser{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5976610437720184567L;
	public static final FileNameExtensionFilter egkaFilter = new FileNameExtensionFilter("Extended GKA Files","egka");
	public static final FileNameExtensionFilter gkaFilter = new FileNameExtensionFilter("Classic GKA Files", "gka"); 
	public GKAFileChooser(){
		super();
		addChoosableFileFilter(egkaFilter);
		addChoosableFileFilter(gkaFilter);
	}

}