package GKA.Controler;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import GKA.GUI.GKAWindow;

public class MainControler {
	private static GKAWindow mainWindow;
	public static void main(String[] args) {
		mainWindow = new GKAWindow();
		mainWindow.repaint();
	}
	public static void newDirectedGraph(){
		ListenableGraph<String,DefaultEdge> graph = new ListenableDirectedGraph<>(DefaultEdge.class);
		graph.addVertex("v1");
		graph.addVertex("v2");
		
		graph.addEdge("v1", "v2");
		graph.addEdge("v2", "v1");
		mainWindow.showMainPanel(graph);
	}
}
