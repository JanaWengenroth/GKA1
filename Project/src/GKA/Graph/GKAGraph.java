package GKA.Graph;

import static GKA.FileHandling.Checks.PreChecks.checkExistingFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;






//import org.jgraph.graph.DefaultEdge;
//import org.jgrapht.Graph;
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
    	GKAGraphInterface returnValue = null;
    	try {
    		linedFile = readFile(file);
    		ArrayList<HashMap<String,String>> parsedGraph = parse(linedFile);
    		Double weight; 
    		String edgeName;
    		boolean isDirectedGraph = false;
    		boolean isWeightedGraph = false;
    		    
    		for(HashMap<String,String> line : parsedGraph)
    		{
    		    if(line.get("vertexOnly").equals("false"))
    		    {
    		       isDirectedGraph = line.get("isDirected").equals("true"); 
    		       isWeightedGraph = isWeightedGraph || line.get("weight") != null;
    		    }   		    
    		}
    		
    		for(HashMap<String,String> line : parsedGraph)
    		{
    		    if(line.get("vertexOnly").equals("false"))
    		    {
    		        if(isDirectedGraph != (line.get("isDirected").equals("true")))
    		        {
    		           MainControler.sendMessage("Graph beinhaltet sowohl gerichtete als auch ungerichtete Kanten");
    		           throw new IncorrectFileFormat(); 		           
    		        }
    		    }
    		}
    		
    		if(isDirectedGraph && isWeightedGraph)
    		{
    		  returnValue = newGraph(GraphType.DirectedWeighted); 
    		}
    		else if(isDirectedGraph && !isWeightedGraph)
    		{
    		    returnValue = newGraph(GraphType.Directed);
    		}
    		else if(!isDirectedGraph && isWeightedGraph)
    		{
    		    returnValue = newGraph(GraphType.UndirectedWeighted);
    		}
    		else if(!isDirectedGraph && !isWeightedGraph)
    		{
    		    returnValue = newGraph(GraphType.Undirected);
    		}
    		
    		
    		for(HashMap<String,String> line : parsedGraph)
    		{
    		    if(line.get("vertexOnly") == "true")
    		    {
    		        
    		            returnValue.addVertex(line.get("node1"));
    		    }
    		    else 
    		    {
    		        if(line.containsKey("weight"))
    		        {
    		          weight = Double.parseDouble((isWeightedGraph != (line.get("weight") != null)) ? "1.0" :line.get("weight"));		          
    		        }
    		        else
    		        {
    		            weight = null;
    		        }
    		        if(line.containsKey("edgeName"))
    		        {
    		            edgeName = line.get("edgeName");
    		        }
    		        else
    		        {
    		            edgeName = null;
    		        }
    		        returnValue.addEdge(line.get("node1"), line.get("node2"), edgeName, weight);
    		    }
    		}
    	} catch (IOException | FileNotExists e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IncorrectFileFormat e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	return returnValue;
    }

	
	private static ArrayList<HashMap<String, String>> parse(ArrayList<String> linedFile) throws IncorrectFileFormat {
		ArrayList<HashMap<String, String>> retVal = new ArrayList<>();
		for (String line : linedFile){
		    if(!line.isEmpty())
		    {
		        HashMap<String, String> lineHash = new HashMap<>();
    			if(line.matches("[\\p{L}|[0-9]]+"))
    	        {
    				lineHash.put("vertexOnly","true");
    				lineHash.put("node1", line);
    				retVal.add(lineHash); //direkt zur Ausgabe
    	        }
    	        else if(line.matches("[\\p{L}|[0-9]]+" + DIRECTED_SIGN + "[\\p{L}|[0-9]]+(\\([\\p{L}|[0-9]]+\\))?(:\\d+(.\\d+)?)?"))
    	        {	
    	        	//Methode für gerichtete Graphen
    	        	lineHash = getHashedLine(line, DIRECTED_SIGN);
    	        	lineHash.put("isDirected", "true");
    	        	retVal.add(lineHash);
    	        }
    	        else if(line.matches("[\\p{L}|[0-9]]+" + UNDIRECTED_SIGN + "[\\p{L}|[0-9]]+(\\([\\p{L}|[0-9]]+\\))?(:\\d+(.\\d+)?)?"))
    	        {	
    	        	//Methode für ungerichtete Graphen
    	        	lineHash = getHashedLine(line, UNDIRECTED_SIGN);
    	        	lineHash.put("isDirected", "false");
    	        	retVal.add(lineHash);
    	        }
    	        else {
    	        	throw new IncorrectFileFormat();
    	        }
    		}
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
        CharsetDecoder decoder = Charset.forName("ISO-8859-1").newDecoder();
        decoder.onMalformedInput(CodingErrorAction.IGNORE);
        InputStreamReader reader2 = new InputStreamReader(new FileInputStream(checkExistingFile(file)), decoder);
        BufferedReader reader = new BufferedReader(reader2);
        String line = null;
        ArrayList<String> returnValue = new ArrayList<>();
        while ((line = reader.readLine()) != null) 
        {
            returnValue.addAll(Arrays.asList(line.replace(" ","").replace("\t","").split(";")));
        }
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
		}
		if (!getjGraph().containsVertex(target)){
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
	
	/**
	 * Finds the Shortest way from source to target,
	 * returns null if not reachable,
	 * else it returns the way from source to target
	 */
	public List<String> shortesPathBroadStringList(String source, String target) {
		ArrayList<ArrayList<String>> wayList = new ArrayList<>();
		Set<String> visitedVertexes = new HashSet<>();
		long startime = System.nanoTime();
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
						if(!visitedVertexes.contains(nextNode)){
							visitedVertexes.add(nextNode);
							tmpWaylist.add(tmpActualWay);
						}
					}
				}
				
			}
			wayList = tmpWaylist;
		}
		long timeNeeded = (System.nanoTime() - startime);
		MainControler.sendMessage("Found no way!");
		MainControler.sendMessage("Hops: " + hops);
		MainControler.sendMessage("Time: " + timeNeeded + " NanoSec");
		return null;
	}
	/**
	 * Returns the TargetVertex by moving an edge Starting at a Source
	 * @param edge
	 * @param source
	 * @return
	 */
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
	public boolean equals(Object object)
	{
	    if(object == null){
	        return false;
	    }
	    else if(object == this){
	        return true;
	    }
	    else if(!(object instanceof GKAGraphInterface)){
	        return false;
	    }
	    else{
	        GKAGraphInterface graph = (GKAGraphInterface) object;
	        return (graph.getjGraph().edgeSet().equals(this.getjGraph().edgeSet()) && graph.getjGraph().vertexSet().equals(this.getjGraph().vertexSet()));
	    }
	}
	public int hashCode(){
	    return 31 + getjGraph().edgeSet().hashCode() + getjGraph().vertexSet().hashCode();

	}
	public String toString()
	{
	    return getjGraph().toString();
	}
}
