package GKA.Controler;

import java.io.File;

import GKA.GUI.GKAWindow;
import GKA.Graph.GKAGraphInterface;
import GKA.Graph.GraphType;

public class MainControler {
	private static GKAWindow mainWindow;
	private static GKAGraphInterface graph;
	private static boolean minimumSpanningTree = false;
	public static void main(String[] args) {
		mainWindow = new GKAWindow();
		newDirectedGraph();
		mainWindow.repaint();
	}
	public static void newGraph(File file){
		minimumSpanningTree = false;
		GKAGraphInterface.newGraph(file);
		MainControler.graph = GKAGraphInterface.newGraph(file);
		graph.addMessageReceiver(mainWindow);
		graph.setLayout();
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Graph was build.");
	}
	public static void newGraph(GKAGraphInterface graph){
		minimumSpanningTree = false;
		MainControler.graph = graph;
		graph.addMessageReceiver(mainWindow);
		graph.setLayout();
		mainWindow.showMainPanel(graph);
		mainWindow.pack();
		sendMessage("New Graph was build.");
	}
	public static void newDirectedGraph(){
		minimumSpanningTree = false;
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
		minimumSpanningTree = false;
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
		minimumSpanningTree = false;
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
		minimumSpanningTree = false;
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
	public static void saveGraph(File file, boolean decreapted){
		graph.saveGraph(file, decreapted);
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
	public static void fordFulkerson(String source, String target) {
		graph.resetColor();
		graph.fordFulkerson(source, target);	
	}
	public static void edmondKarp(String source, String target) {
        graph.resetColor();
        graph.edmondKarp(source, target);    
    }
	public static void minimumSpanningTree() {
		GKAGraphInterface tmpGraph = null;
		if(minimumSpanningTree){
			minimumSpanningTree = false;
			tmpGraph = graph;
		}else{
			minimumSpanningTree = true;
			tmpGraph = graph.getMinimumSpanningTree();
		}
		tmpGraph.addMessageReceiver(mainWindow);
		tmpGraph.addEdge("v1", "v2",null,null);
		tmpGraph.addEdge("v1", "v2",null,null);
		tmpGraph.setLayout();
		mainWindow.showMainPanel(tmpGraph);
		mainWindow.pack();
		
	}
}
