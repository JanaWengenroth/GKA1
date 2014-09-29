package GKA.Controler;

import GKA.GUI.GKAWindow;
import GKA.Graph.GKAGraphInterface;
import GKA.Graph.GraphType;

public class MainControler {
	private static GKAWindow mainWindow;
	private static GKAGraphInterface graph;
	public static void main(String[] args) {
		mainWindow = new GKAWindow();
		mainWindow.repaint();
	}
	public static void newDirectedGraph(){
		MainControler.graph = GKAGraphInterface.newGraph(GraphType.Directed);
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addEdge("v1", "v2",null,null);
		graph.setCircleLayout();
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Directed Graph was build.");
	}
	public static void newUndirectedGraph(){
		MainControler.graph = GKAGraphInterface.newGraph(GraphType.Undirected);
		graph.addVertex("v3");
		graph.addVertex("v4");
		graph.addEdge("v3", "v4",null,null);
		graph.setCircleLayout();
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Directed Graph was build.");
	}
	public static void sendMessage(String msg){
		mainWindow.sendMessage(msg);
	}
	public static void addVertex() {
		graph.addVertex("v_new");
		
	}
	public static void showGraph() {
		System.out.println(graph.toString());
		
	}
}
