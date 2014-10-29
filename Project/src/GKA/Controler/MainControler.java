package GKA.Controler;

import java.io.File;

import GKA.GUI.GKAWindow;
import GKA.Graph.GKAGraphInterface;
import GKA.Graph.GraphType;

public class MainControler {
	private static GKAWindow mainWindow;
	private static GKAGraphInterface graph;
	public static void main(String[] args) {
		mainWindow = new GKAWindow();
		newDirectedGraph();
		mainWindow.repaint();
	}
	public static void newGraph(File file){
		GKAGraphInterface.newGraph(file);
		MainControler.graph = GKAGraphInterface.newGraph(file);
		graph.addMessageReceiver(mainWindow);
		graph.setLayout();
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Graph was build.");
	}
	public static void newDirectedGraph(){
		MainControler.graph = GKAGraphInterface.newGraph(GraphType.Directed);
		graph.addMessageReceiver(mainWindow);
		graph.addEdge("v1", "v2",null,null);
		graph.addEdge("v1", "v2",null,null);
		graph.setLayout();
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Directed Graph was build.");
	}
	public static void newDirectedWeightedGraph(){
		MainControler.graph = GKAGraphInterface.newGraph(GraphType.DirectedWeighted);
		graph.addMessageReceiver(mainWindow);
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addEdge("v1", "v2",null,1.0);
		graph.addEdge("v1", "v2",null,1.0);
		graph.setLayout();
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Directed Graph was build.");
	}
	public static void newUndirectedGraph(){
		MainControler.graph = GKAGraphInterface.newGraph(GraphType.Undirected);
		graph.addVertex("v3");
		graph.addVertex("v4");
		graph.addEdge("v3", "v4",null,null);
		graph.addEdge("v3", "v4",null,null);
		graph.setLayout();
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Directed Graph was build.");
	}
	public static void newUndirectedWeigthedGraph(){
		MainControler.graph = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
		graph.addMessageReceiver(mainWindow);
		graph.addVertex("v3");
		graph.addVertex("v4");
		graph.addEdge("v3", "v4",null,1.0);
		graph.addEdge("v3", "v4",null,1.0);
		graph.setLayout();
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Directed Graph was build.");
	}
	public static void sendMessage(String msg){
		if (mainWindow != null)
			mainWindow.sendMessage(msg);
	}
	public static void addVertex(String vertexName) {
		graph.addVertex(vertexName);
		
	}
	public static void showGraph() {
		System.out.println(graph.toString());
	}
	public static void setLayout(){
		graph.setLayout();
	}
	public static boolean removeVertex(String vertexName) {
		return graph.removeVertex(vertexName);
	}
	public static  boolean removeEdge(String source, String target) {
		return graph.removeEdge(source, target);
	}
	public static boolean addEdge(String source, String target, String edgeName, Double edgeWeight) {
		return graph.addEdge(source, target, edgeName, edgeWeight);
	}
	public static GKAGraphInterface getGraph() {
		return graph;
	}
	public static void saveGraph(File file){
		graph.saveGraph(file);
	}
	public static void shortesPathBroad(String source, String target){
		graph.resetColor();
		graph.colorEdge(graph.shortesPathBroad(source, target));
	}
	public static void dijkstra(String source, String target){
        graph.resetColor();
        graph.colorEdge(graph.dijkstra(source, target));
    }
	public static void floydWarschall(String source, String target) {
		graph.resetColor();
		graph.colorEdge(graph.floydWarschall(source, target));
		
	}
}
