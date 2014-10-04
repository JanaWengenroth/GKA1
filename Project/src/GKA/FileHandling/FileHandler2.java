package GKA.FileHandling;

import static GKA.FileHandling.Checks.PreChecks.checkExistingFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;

import GKA.FileHandling.Errors.FileNotExists;

public class FileHandler2
{
    public static final String DIRECTED_SIGN = "->";
    public static final String UNDIRECTED_SIGN = "--";
    private static ArrayList<String>  _kanten;
    
    //*file wird geladen
    
    public static Graph<String, DefaultEdge> load(File file) throws FileNotExists, IOException
    {
        
        System.out.println(directed());
        return null;
    }
    
    //* der file enthält nur Graphen mit einen node(node1).
    //* der file enthält nur Graphen mit node1 gerichtet/ungerichtet und node2.
    //* der file enthält nur Graphen mit node1 gerichtet/ungerichtet und node2 und einem Kantennamen.
    //* der file enthält nur Graphen mit node1 gerichtet/ungerichtet und node2 und einer Gewichtung.
    //* der file enthält nur Graphen mit node1 gerichtet/ungerichtet und node2 und einen Kantennamen und einer Gewichtung.
    
  //* Wenn der file existiert, werden die " " entfernt und an dem ";" aufgeteilt und in einem Array heraus gegeben
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
        _kanten = returnValue;
        return returnValue;  
    }
       
    //*ist der Graph gerichtet  ein einzelner Knoten?
    private static boolean singleNode() 
    {
        for(String kante : _kanten)
        {
            if(!kante.matches("\\w+"))
            {
                System.out.println("Graph enthaelt nur node1"); //direkt zur Ausgabe
                return false;
            }
        }
        return true;
    }
    
    private static boolean directed()
    {
        for(String kante : _kanten)
        {
            if(!kante.matches("\\w+" + DIRECTED_SIGN + "\\w*[\\(\\w*\\)]?[\\:\\w*]?"))
            {
                System.out.println("Graph ist gerichtet");//Methode für gerichtete Graphen
                return false;
            }
        }
        return true;
    }
    
    private static boolean undirected()
    {
        for(String kante : _kanten)
        {
            if(!kante.matches("\\w+" + UNDIRECTED_SIGN + "\\w*[\\(\\w*\\)]?[\\:\\w*]?"))
            {
                System.out.println("Graph ist ungerichtet");//Methode für ungerichtete Graphen
                return false;
            }
        }
        return true;
    }
    
//        else if(returnValue.get(0).matches("\\w*\\" + DIRECTED_SIGN + "\\w*\\(\\w*\\)"))
//        {
//            System.out.println("Graphen sind gerichtet mit Kantennamen");
//        }
//        else if(returnValue.get(0).matches("\\w*\\" + UNDIRECTED_SIGN + "\\w*\\(\\w*\\)"))
//        {
//            System.out.println("Graphen sind ungerichtet mit Kantennamen");
//        }
//        else if(returnValue.get(0).matches("\\w*\\" + DIRECTED_SIGN + "\\w*\\:\\w*"))
//        {
//            System.out.println("Graphen sind gerichtet mit Gewichtung");
//        }
//        else if(returnValue.get(0).matches("\\w*\\" + UNDIRECTED_SIGN + "\\w*\\:\\w*"))
//        {
//            System.out.println("Graphen sind ungerichtet mit Gewichtung");
//        }
//        else if(returnValue.get(0).matches("\\w*\\" + DIRECTED_SIGN + "\\w*\\:\\w*"))
//        {
//            System.out.println("Graphen sind gerichtet mit Kantennamen und Gewichtung");
//        }
//        else if(returnValue.get(0).matches("\\w*\\" + UNDIRECTED_SIGN + "\\w*\\(\\w*\\)\\:\\w*"))
//        {
//            System.out.println("Graphen sind ungerichtet mit Kantennamen und Gewichtung");
//        }
//        else
//        {
//            System.out.println("found nothing");
//        }
    
    
   
    //bei den verschiedenen Möglichkeiten für gerichteten Graphen splitten
    //Liste mit Kantennamen erstellen
    public static ArrayList<String> edgenames(ArrayList<String> inputValue )
    {
        ArrayList<String> edgenameList = new ArrayList<>();
        String edgename = new String();
       // ArrayList<String> edgenameList = new ArrayList<>();
        for(int i = 0; i <= inputValue.size()-1; i++)
        {
            if(inputValue.get(i).contains("\\(\\w*\\)"))
            {
                edgename = inputValue.get(i).split("(")[1];
                edgename =edgename.split(")")[0];
            }
            else
            {
                edgename = "";
            }
            edgenameList.add(edgename);
          
        }
        return edgenameList;
        
    }
    
    //Liste mit Gewichtungen erstellen
    public static ArrayList<String> weights(ArrayList<String> inputValue )
    {
        String weight = new String();
        ArrayList<String> weightsList = new ArrayList<>();
        for(int i = 0; i <= inputValue.size()-1; i++)
        {
            if(inputValue.get(i).contains(":"))
            {
               weight = inputValue.get(i).split(":")[1];  
            }
            else
            {
                weight = "";
            }
            weightsList.add(weight);
        }
        System.out.println(weightsList);
        return weightsList;
        
    }
    
    //Liste mit ersten Knoten erstellen
    public static ArrayList<String> node1(ArrayList<String> inputValue )
    {
        String node1 = new String();
        ArrayList<String> node1Liste = new ArrayList<>();
        if(inputValue.get(0).contains("->"))
        {
            for(int i = 0; i <= inputValue.size()-1; i++)
            {
                node1 = inputValue.get(i).split("->")[0];
                node1Liste.add(node1); 
            }
        }
        if(inputValue.get(0).contains("--"))
        {
            for(int i = 0; i <= inputValue.size() -1; i++)
            {
                node1 = inputValue.get(i).split("--")[0];
                node1Liste.add(node1);
            }
        }
        else
        {
            for(int i = 0; i <= inputValue.size() -1; i++)
            {
                node1 = inputValue.get(i);
                node1Liste.add(node1);
            }
        }
       
        return node1Liste;
    }

    // Liste mit zweiten Knoten erstellen
public static ArrayList<String> node2(ArrayList<String> inputValue )
{
    String node2 = new String();
    ArrayList<String> node2Liste = new ArrayList<>();
    if(inputValue.get(0).contains("->"))
    {
        for(int i = 0; i <= inputValue.size()-1; i++)
        {
            node2 = inputValue.get(i).split("->")[1];
            if(node2.contains("("))
            {
                node2 = node2.split("(")[0];
            }
            else if(node2.contains(":"))
            {
                node2 = node2.split(":")[0];
            }
            node2Liste.add(node2);
        }
         
    }
    if(inputValue.get(0).contains("--"))
    {
        for(int i = 0; i <= inputValue.size()-1; i++)
        {
            node2 = inputValue.get(i).split("--")[1];
            if(node2.contains("("))
            {
                node2 = node2.split("(")[0];
            }
            else if(node2.contains(":"))
            {
                node2 = node2.split(":")[0];
            }
            node2Liste.add(node2);
        }
        
    }

    else
    {
        for(int i = 0; i <= inputValue.size()-1; i++)
        {
            node2 = "";
        }
        node2Liste.add(node2);
    }
    return node2Liste;
}


//    private static Graph<String, DefaultEdge> getNewGraph(String s)
//    {
//        
//        return null;
//        
//    }
    
//    private static HashMap<String, String> parse(String parse)
//    {
//        HashMap<String, String> returnValue = new HashMap<>();
//        String splitAt = null;
//        if(parse.contains(DIRECTED_SIGN))
//        {
//            splitAt = DIRECTED_SIGN;
//            returnValue.put("isDirected", "true");
//        }
//        else if(parse.contains(UNDIRECTED_SIGN)) 
//        {
//            splitAt = UNDIRECTED_SIGN;
//            returnValue.put("isDirected", "false");
//        }
//        String[] splitedValue = {parse};
//        if(splitAt != null)
//        {
//            splitedValue = parse.split(splitAt + "|\\(|\\)|\\:");
//            System.out.println(Arrays.asList(splitedValue));
//        }
//        returnValue.put("node1", splitedValue[0]);
//        if(splitedValue.length > 1)
//        {
//            splitedValue = splitedValue[1].split("(");
//            returnValue.put("node2", splitedValue[0]);
//        }
//        return returnValue;
//        
//    }
    
    
//    public static void save(File file, Graph<String, DefaultEdge> graph)
//    {
//        
//    }
//    
    public static void main(String[] args)
    {
           try
        {
            load(new File("D:\\GKA\\GKA1\\aufgabe1Bsp\\graph1.gka"));
        } catch (FileNotExists | IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
