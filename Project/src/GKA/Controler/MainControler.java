package GKA.Controler;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

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
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Directed Graph was build.");
	}
	public static void newUndirectedGraph(){
		ListenableGraph<String,DefaultEdge> graph = new ListenableUndirectedGraph<>(DefaultEdge.class);
		graph.addVertex("v3");
		graph.addVertex("v4");
		graph.addEdge("v3", "v4");
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Undirected Graph was build.");
	}
	public static void sendMessage(String msg){
		mainWindow.sendMessage(msg);
	}
}
