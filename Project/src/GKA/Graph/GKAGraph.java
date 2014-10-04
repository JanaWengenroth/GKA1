package GKA.Graph;

import static GKA.FileHandling.Checks.PreChecks.checkExistingFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.Multigraph;

import GKA.Controler.MainControler;
import GKA.FileHandling.Errors.FileNotExists;
import GKA.FileHandling.Errors.IncorrectFileFormat;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

class GKAGraph implements GKAGraphInterface {
	/**
	 * Creates a new Graph
	 * 
	 * @param type - specifies the type of the Graph
	 * @return
	 */
	static final String DIRECTED_SIGN = "->";
	static final String UNDIRECTED_SIGN = "--";
	static GKAGraphInterface newGraph(GraphType type){
		return new GKAGraph(type);
	}
	
	 static GKAGraphInterface newGraph(File file){
		ArrayList<String> linedFile;
		GKAGraphInterface retval = null;
		try {
			linedFile = readFile(file);
			ArrayList<HashMap<String,String>> parsedGraph = parse(linedFile);
			double weight; 
			String edgeName;
			
			for(HashMap<String,String> line : parsedGraph)
			{
			    if(line.get("vertexOnly") == "true")
			    {
			            addVertex(line.get("node1"));
			    }
			    else 
			    {
			        if(line.containsKey("weight"))
			        {
			          weight = Double.parseDouble(line.get("weight"));		          
			        }
			        else
			        {
			            weight = 0.0;
			            System.out.println("weight not existing");
			        }
			        if(line.containsKey("edgeName"))
			        {
			            edgeName = line.get("edgeName");
			        }
			        else
			        {
			            edgeName = null;
			        }
			        addEdge(line.get("node1"), line.get("node2"), edgeName, weight);
			    }
			}
		} catch (IOException | FileNotExists e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectFileFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retval;
	}
	
	private static ArrayList<HashMap<String, String>> parse(ArrayList<String> linedFile) throws IncorrectFileFormat {
		ArrayList<HashMap<String, String>> retVal = new ArrayList<>();
		for (String line : linedFile){
			HashMap<String, String> lineHash = new HashMap<>();
			if(line.matches("\\w+"))
	        {
				lineHash.put("vertexOnly","true");
				lineHash.put("node1", line);
				retVal.add(lineHash); //direkt zur Ausgabe
	        }
	        else if(line.matches("\\w+" + DIRECTED_SIGN + "\\w+(\\(\\w+\\))?(:\\d+(.\\d+)?)?"))
	        {	
	        	//Methode für gerichtete Graphen
	        	lineHash = getHashedLine(line, DIRECTED_SIGN);
	        	lineHash.put("isDirected", "true");
	        	retVal.add(lineHash);
	        }
	        else if(line.matches("\\w+" + UNDIRECTED_SIGN + "\\w+(\\(\\w+\\))?(:\\d+(.\\d+)?)?"))
	        {	
	        	//Methode für ungerichtete Graphen
	        	lineHash = getHashedLine(line, UNDIRECTED_SIGN);
	        	lineHash.put("isDirected", "false");
	        	retVal.add(lineHash);
	        }
	        else {
	        	System.out.println(line);
	        	throw new IncorrectFileFormat();
	        }
			System.out.println(lineHash);
		}
		return retVal;
	}


	private static HashMap<String, String> getHashedLine(String line,
			String sign) {
		HashMap<String,String> retVal = new HashMap<>();
		retVal.put("vertexOnly","false");
		String[] splitedLine = line.split(sign);
		retVal.put("node1", splitedLine[0]);
		if(splitedLine[1].contains("("))
        {
			splitedLine = splitedLine[1].split("\\(");
        }
        else
        {
        	splitedLine = splitedLine[1].split(":");
        }
		retVal.put("node2", splitedLine[0]);
		if (splitedLine.length > 1){
			if(splitedLine[1].contains(")")){
				splitedLine = splitedLine[1].split("\\)|\\):");
				retVal.put("edgeName", splitedLine[0]);
				if(splitedLine.length > 1){
					retVal.put("weight", splitedLine[1].replace(":", ""));
				}
			}
			else{
				retVal.put("weight", splitedLine[1]);
			}
		}
		return retVal;
	}

	//* Wenn der file existiert, werden die " " entfernt und an dem ";" aufgeteilt und in einem Array heraus gegeben
    private static ArrayList<String> readFile(File file) throws IOException, FileNotExists
    {
        Charset charset = Charset.forName("UTF-8");
        BufferedReader reader = Files.newBufferedReader(checkExistingFile(file).toPath(), charset);
        String line = null;
        ArrayList<String> returnValue = new ArrayList<>();
        while ((line = reader.readLine()) != null) 
        {
            returnValue.addAll(Arrays.asList(line.replace(" ","").replace("\t","").split(";")));
        }
        System.out.println(returnValue);
        return returnValue;  
    }
	
	
	private final ListenableGraph<String, GKAEdge> jGraph;
	private final JGraphXAdapter<String,GKAEdge> mxgraph;
	private final GraphType type;
	private GKAGraph(GraphType type){
		this.type = type;
		
		// Choose if it will be directed or undirected
		if (isDirected()){
			jGraph = new ListenableDirectedGraph<>(new DirectedMultigraph<>(GKAEdge.class));
		}else{
			jGraph = new ListenableUndirectedGraph<>(new Multigraph<>(GKAEdge.class));
		}
		
		// JGXAdapter for showing the Graph in Swing
		mxgraph = new JGraphXAdapter<>(getjGraph());
		
		// Changing EdgeStyle when is undirected
		if (!isDirected()){
			getMxgraph().getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW, "none");
		}
		setGraphConfig();
	}
	
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#getjGraph()
	 */
	@Override
	public ListenableGraph<String, GKAEdge> getjGraph() {
		return jGraph;
	}
	
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#getMxgraph()
	 */
	@Override
	public mxGraph getMxgraph() {
		return mxgraph;
	}
	private GraphType getType() {
		return type;
	}
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#isDirected()
	 */
	@Override
	public boolean isDirected(){
		return getType().isDirected();
	}
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#isWeighted()
	 */
	@Override
	public boolean isWeighted(){
		return getType().isWeighted();
	}
	
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#addVertex(java.lang.String)
	 */
	@Override
	public boolean addVertex(String vertexName){
		if(vertexName == null){
			MainControler.sendMessage("Null is not exepted as VertexName!");
			return false;
		}
		if(!getjGraph().addVertex(vertexName)){
			MainControler.sendMessage("Vertex \"" + vertexName + "\" already exists!");
			return false;
		}
		MainControler.sendMessage("Vertex \"" + vertexName + "\" created.");
		return true;
	}
	
	/* (non-Javadoc)
	 * @see GKA.Graph.GKAGraphInterface#addEdge(java.lang.String, java.lang.String, java.lang.String, java.lang.Double)
	 */
	@Override
	public boolean addEdge(String source, String target, String name, Double weight){
		if (source == null){
			MainControler.sendMessage("Please add a SourceVertex!");
			return false;
		}else if(target == null){
			MainControler.sendMessage("Please add a TargetVertex!");
			return false;
		}
		if (weight == null && isWeighted()){
			MainControler.sendMessage("Please add a Weight for weighted Graphs!");
			return false;
		} else if( isWeighted() && weight < 0.0){
			MainControler.sendMessage("A weight below 0 is not allowed!");
			return false;
		}
		if (weight != null && !isWeighted()){
			MainControler.sendMessage("Weights have no effects to unweighted Graphs!");
		}
		if(!getjGraph().containsVertex(source)){
			addVertex(source);
		}else if (!getjGraph().containsVertex(target)){
			addVertex(target);
		}
		GKAEdge edge;
		try {
			edge = jGraph.addEdge(source, target);
			if(edge == null){
				MainControler.sendMessage("A edge from \"" + source + "\" to \"" + target + "\" aready exists!");
				return false;
			}
			edge.setName(name);
			edge.setWeight(weight);
			MainControler.sendMessage("Edge \"" + edge.toString() + "\" was set.");
		} catch (Exception e) {
			MainControler.sendMessage("Adding edge from \"" + source + "\" to \"" + target + "\" failed by \n" +
					e.getMessage());
			return false;
		}
		return true;
		
	}
	
	@Override
	public void setLayout(){
		mxCircleLayout layout1 = new mxCircleLayout(getMxgraph());
        layout1.execute(getMxgraph().getDefaultParent());
		mxParallelEdgeLayout layout = new mxParallelEdgeLayout(getMxgraph(), 50);
        layout.execute(getMxgraph().getDefaultParent());
	}
	private void setGraphConfig(){
		getMxgraph().setAllowDanglingEdges(false);
		getMxgraph().setCellsDisconnectable(false);
		getMxgraph().setDisconnectOnMove(false);
		getMxgraph().setCellsEditable(false);
		getMxgraph().setVertexLabelsMovable(false);
		getMxgraph().setEdgeLabelsMovable(false);
		getMxgraph().setConnectableEdges(false);
	}

	@Override
	public boolean removeVertex(String vertexName) {
		return getjGraph().removeVertex(vertexName);
	}

	@Override
	public boolean removeEdge(String source, String target) {
		return getjGraph().removeEdge(getjGraph().getEdge(source, target));
	}
	
	@Override
	public void saveGraph(File file){
		Writer fw = null;
		BufferedWriter bw = null;
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try
		{
		  fw = new FileWriter( file );
		  bw = new BufferedWriter(fw);
		  bw.write( "" );
		  for(String vertex :getjGraph().vertexSet()){
			  bw.append(vertex + ";" + System.getProperty("line.separator"));
		  }
		  String connector = isDirected() ? DIRECTED_SIGN : UNDIRECTED_SIGN;
		  for(GKAEdge edge:getjGraph().edgeSet()){
			  String saveVal = edge.getSource() + " " + connector + " " + edge.getTarget();
			  if(edge.getName() != null){
				  saveVal += " (" + edge.getName() + ")";
			  }
			  if(isWeighted()){
				  saveVal += " :" + edge.getWeight();
			  }
			  saveVal += ";" + System.getProperty("line.separator");
			  bw.append(saveVal);
		  }
		  MainControler.sendMessage("Graph is saved to: \"" + file.getAbsolutePath() + "\".");
		}
		catch ( IOException e ) {
		  MainControler.sendMessage( "Konnte Datei nicht erstellen" );
		}
		finally {
		  if ( bw != null )
		    try { bw.close(); } catch ( IOException e ) { e.printStackTrace();}
		}
		
	}

	@Override
	public void colorEdge(GKAEdge edge) {
		if(edge != null){
			getMxgraph().getModel().setStyle(
					mxgraph.getEdgeToCellMap().get(edge),
					"strokeColor=FF0000");
		}
	}
	
	@Override
	public void resetColor(){
		for(GKAEdge edge : getjGraph().edgeSet()){
			getMxgraph().getModel().setStyle(mxgraph.getEdgeToCellMap().get(edge), "");
		}
	}
	@Override
	public void colorEdge(Collection<GKAEdge> edges) {
		if(edges != null){
			for(GKAEdge edge: edges){
				colorEdge(edge);
			}
		}
	}
	/**
	 * Finds the Shortest way from source to target,
	 * returns null if not reachable,
	 * else it returns the from source to target
	 */
	@Override
	public List<GKAEdge> shortesPathBroad(String source, String target) {
		List<String> shortestPath = shortesPathBroadStringList(source, target);
		if(shortestPath == null){
			return null;
		}
		else{
			ArrayList<GKAEdge> retVal = new ArrayList<>();
			ListIterator<String> it = shortestPath.listIterator();
			if(it.hasNext()){
				String sourceErg = it.next();;
				while (it.hasNext()){
					String targetErg = it.next();
					retVal.add(getjGraph().getEdge(sourceErg, targetErg));
					sourceErg = targetErg;
				}
			}else{
				return null;
			}
			return retVal;
		}
	}
	
	
	public List<String> shortesPathBroadStringList(String source, String target) {
		ArrayList<ArrayList<String>> wayList = new ArrayList<>();
		Set<String> visitedVertexes = new HashSet<>();
		int hops = 0;
		if (source.equals(target)){
			MainControler.sendMessage("Source == Target");
			return null;
		}
		
		{
			ArrayList<String> actualWay = new ArrayList<>();
			actualWay.add(source);
			wayList.add(actualWay);
			visitedVertexes.add(source);
		}
		
		while (!wayList.isEmpty()){
			long startime = System.nanoTime();
			
			ArrayList<ArrayList<String>> tmpWaylist = new ArrayList<>();
			for(ArrayList<String> actualWay : wayList){
				String lastNode = actualWay.get(actualWay.size() - 1);
				for(GKAEdge edge : getAccessibleEdges(lastNode)){
					ArrayList<String> tmpActualWay = new ArrayList<>(actualWay);
					String nextNode = moveEdge(edge, lastNode);
					tmpActualWay.add(nextNode);
					hops = hops + 1;
					if(nextNode.equals(target)){
						long timeNeeded = (System.nanoTime() - startime);
						MainControler.sendMessage("Found Way: " + tmpActualWay);
						MainControler.sendMessage("Hops: " + hops);
						MainControler.sendMessage("Time: " + timeNeeded + " NanoSec");
						return tmpActualWay;
					}
					else{
						tmpWaylist.add(tmpActualWay);
					}
				}
				
			}
			wayList = tmpWaylist;
		}
		return null;
	}
	private String moveEdge(GKAEdge edge, String source){
		if(edge.getSource().equals(source)){
			return edge.getTarget().toString();
		}else{
			return edge.getSource().toString();
		}
	}
	/**
	 * Returns a set of edges which are accessible from a specified 
	 * source
	 * @param source
	 * @return
	 */
	private Set<GKAEdge> getAccessibleEdges(String source){
		Set<GKAEdge> edges = new HashSet<>(getjGraph().edgesOf(source));
		Set<GKAEdge> notGoable = new HashSet<>();
		for (GKAEdge edge : edges) {
			if(!isEdgeAccessible(edge, source)){
				notGoable.add(edge);
			}
		}
		edges.removeAll(notGoable);
		return edges;
	}
	/**
	 * Returns true or false if an Edge is accessible by Starting 
	 * from a specified source
	 * @param edge
	 * @param source
	 * @return
	 */
	private boolean isEdgeAccessible(GKAEdge edge, String source){
		if(edge.getSource().equals(source)){
			return true;
		}else if(!isDirected() && edge.getTarget().equals(source)){
			return true;
		}else{
			return false;
		}
	}
}
