package GKA.GUI;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JOptionPane;

import org.jgrapht.generate.GraphGenerator;

import GKA.Controler.MainControler;
import GKA.Graph.GKAGraphInterface;

public class GenerateRandomTourGraphButton extends GKAButton{

    /**
     * 
     */
    private static final long serialVersionUID = 983926603804344475L;
    
    public GenerateRandomTourGraphButton(int width, int height, int posX, int posY) {
        super(width, height, posX, posY, "Generate Random Tour Graph");
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
           
            GKAGraphInterface.newTourGraph(Integer.valueOf(inputVertexNr)).
            saveGraph(new File("..\\aufgabe1Bsp\\tour_" + inputVertexNr + "_" + ".gka"), true);
        }
    }
        
    
}

