package GKA.FileHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static GKA.FileHandling.Checks.PreChecks.*; 

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import GKA.FileHandling.Errors.FileNotExists;

public class FileHandler 
{
	public static final String DIRECTED_SIGN = "->";
	public static final String UNDIRECTED_SIGN = "--";
	public static Graph<String, DefaultEdge> load(File file) throws FileNotExists, IOException
	{
		
		System.out.println(readFile(file));
		return null;
	}
	
	private static ArrayList<String> readFile(File file) throws IOException, FileNotExists
	{
		Charset charset = Charset.forName("US-ASCII");
		BufferedReader reader = Files.newBufferedReader(checkExistingFile(file).toPath(), charset);
		String line = null;
		ArrayList<String> returnValue = new ArrayList<>();
		while ((line = reader.readLine()) != null) 
		{
			returnValue.addAll(Arrays.asList(line.replace(" ","").replace("\t","").split(";")));
		}	
		return returnValue;
	}
	
	private static Graph<String, DefaultEdge> getNewGraph(String s)
	{
		
		return null;
		
	}
	
	private static HashMap<String, String> parse(String parse)
	{
		HashMap<String, String> returnValue = new HashMap<>();
		String splitAt = null;
		if(parse.contains(DIRECTED_SIGN))
		{
			splitAt = DIRECTED_SIGN;
			returnValue.put("isDirected", "true");
		}
		else if(parse.contains(UNDIRECTED_SIGN)) 
		{
			splitAt = UNDIRECTED_SIGN;
			returnValue.put("isDirected", "false");
		}
		String[] splitedValue = {parse};
		if(splitAt != null)
		{
			splitedValue = parse.split(splitAt + "|\\(|\\)|\\:");
			System.out.println(Arrays.asList(splitedValue));
		}
		returnValue.put("node1", splitedValue[0]);
		if(splitedValue.length > 1)
		{
			splitedValue = splitedValue[1].split("(");
			returnValue.put("node2", splitedValue[0]);
		}
		return returnValue;
		
	}
	
	
	public static void save(File file, Graph<String, DefaultEdge> graph)
	{
		
	}
	
	public static void main(String[] args)
	{
		System.out.println(parse("a--b"));
	}
}
