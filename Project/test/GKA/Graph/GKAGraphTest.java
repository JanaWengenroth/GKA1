package GKA.Graph;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import GKA.Controler.MainControler;

public class GKAGraphTest
{
    GKAGraphInterface g1;
    GKAGraphInterface g2;
    GKAGraphInterface g3;
    GKAGraphInterface g4;
    GKAGraphInterface g5;
    GKAGraphInterface g6; 
    GKAGraphInterface g7;
    GKAGraphInterface g8;
    
    @Before
    public void testBefore()
    {
        //MainControler.main(null);
        g1 = GKAGraphInterface.newGraph(GraphType.Directed);
        g1.addEdge("a", "b", null, null);
        g1.addEdge("a", "c", null, null);
        g1.addEdge("a", "h", null, null);
        g1.addEdge("a", "k", null, null);
        g1.addEdge("b", "b", null, null);
        g1.addEdge("b", "j", null, null);
        g1.addEdge("b", "k", null, null);
        g1.addEdge("b", "i", null, null);
        g1.addEdge("c", "a", null, null);
        g1.addEdge("c", "d", null, null); 
        g1.addEdge("d", "a", null, null);
        g1.addEdge("d", "e", null, null);
        g1.addEdge("d", "k", null, null);
        g1.addEdge("e", "b", null, null);
        g1.addEdge("e", "c", null, null);
        g1.addEdge("e", "e", null, null);
        g1.addEdge("e", "f", null, null);
        g1.addEdge("f", "c", null, null);
        g1.addEdge("f", "g", null, null);
        g1.addEdge("g", "g", null, null);
        g1.addEdge("g", "e", null, null);
        g1.addEdge("g", "b", null, null);
        g1.addEdge("g", "d", null, null);
        g1.addEdge("h", "b", null, null);
        g1.addEdge("h", "c", null, null);
        g1.addEdge("i", "a", null, null);
        g1.addEdge("i", "c", null, null);
        g1.addEdge("i", "i", null, null);
        g1.addEdge("j", "k", null, null);
        g1.addEdge("j", "c", null, null);
        g1.addEdge("j", "a", null, null);
        g1.addEdge("j", "b", null, null);
        g1.addEdge("k", "c", null, null);
        g1.addEdge("k", "g", null, null);
        g1.addEdge("k", "d", null, null);
        
        g2 = GKAGraphInterface.newGraph(GraphType.Undirected);
        g2.addEdge("a", "b", null, null);
        g2.addEdge("a", "d", null, null);
        g2.addEdge("a", "g", null, null);
        g2.addEdge("a", "e", null, null);
        g2.addEdge("b", "b", null, null);
        g2.addEdge("b", "j", null, null);
        g2.addEdge("b", "i", null, null);
        g2.addEdge("c", "a", null, null);
        g2.addEdge("c", "d", null, null);
        g2.addEdge("d", "a", null, null);
        g2.addEdge("d", "e", null, null);
        g2.addEdge("d", "g", null, null);
        g2.addEdge("e", "j", null, null);
        g2.addEdge("e", "c", null, null);
        g2.addEdge("e", "e", null, null);
        g2.addEdge("e", "f", null, null);
        g2.addEdge("f", "a", null, null);
        g2.addEdge("f", "g", null, null);
        g2.addEdge("f", "h", null, null);
        g2.addEdge("f", "i", null, null);
        g2.addEdge("g", "g", null, null);
        g2.addEdge("g", "e", null, null);
        g2.addEdge("g", "b", null, null);
        g2.addEdge("g", "d", null, null);
        g2.addEdge("h", "b", null, null);
        g2.addEdge("h", "c", null, null);
        g2.addEdge("h", "f", null, null);
        g2.addEdge("i", "a", null, null);
        g2.addEdge("i", "c", null, null);
        g2.addEdge("i", "i", null, null);
        g2.addEdge("i", "g", null, null);
        g2.addEdge("j", "k", null, null);
        g2.addEdge("j", "c", null, null);
        g2.addEdge("j", "a", null, null);
        g2.addEdge("j", "b", null, null);
        g2.addEdge("k", "c", null, null);
        g2.addEdge("k", "g", null, null);
        g2.addEdge("k", "d", null, null);
        
        g3 = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
        g3.addEdge("Paderborn","Hamburg", null, 228.0);
        g3.addEdge("Bremen","Hamburg", null, 127.0);
        g3.addEdge("Bremen","Bremerhaven", null, 66.0);
        g3.addEdge("Norderstedt","Bremerhaven", null, 191.0);
        g3.addEdge("Norderstedt","Husum", null, 145.0);
        g3.addEdge("Kiel","Husum", null, 86.0);
        g3.addEdge("Lüneburg","Lübeck", null, 115.0);
        g3.addEdge("Lüneburg","Soltau", null, 52.0);
        g3.addEdge("Hameln","Soltau", null, 134.0);
        g3.addEdge("Hameln","Paderborn", null, 134.0);
        g3.addEdge("Hameln","Detmold", null, 45.0);
        g3.addEdge("Münster","Bremen", null, 173.0);
        g3.addEdge("Münster","Minden", null, 131.0);
        g3.addEdge("Minden","Hannover", null, 76.0);
        g3.addEdge("Hannover","Oldenburg", null, 169.0);
        g3.addEdge("Oldenburg","Cuxhaven", null, 105.0);
        g3.addEdge("Cuxhaven","Hannover", null, 217.0);
        g3.addEdge("Cuxhaven","Bremen", null, 100.0);
        g3.addEdge("Bremerhaven","Rotenburg", null, 108.0);
        g3.addEdge("Rotenburg","Soltau", null, 41.0);
        g3.addEdge("Minden","Rotenburg", null, 171.0);
        g3.addEdge("Rotenburg","Uelzen", null, 104.0);
        g3.addEdge("Lüneburg","Buxtehude", null, 66.0);
        g3.addEdge("Uelzen","Hameln", null, 160.0);
        g3.addEdge("Hameln","Walsrode", null, 116.0);
        g3.addEdge("Walsrode","Hamburg", null, 101.0);
        g3.addEdge("Walsrode","Minden", null, 126.0);
        g3.addEdge("Münster","Walsrode", null, 226.0);
        g3.addEdge("Lüneburg","Münster", null, 295.0);
        g3.addEdge("Münster","Paderborn", null, 149.0);
        g3.addEdge("Lüneburg","Hamburg", null, 55.0);
        g3.addEdge("Kiel","Uelzen", null, 190.0);
        g3.addEdge("Oldenburg","Celle", null, 167.0);
        g3.addEdge("Celle","Hannover", null, 43.0);
        g3.addEdge("Celle","Soltau", null, 48.0);
        g3.addEdge("Cuxhaven","Soltau", null, 194.0);
        g3.addEdge("Soltau","Buxtehude", null, 60.0);
        g3.addEdge("Buxtehude","Hamburg", null, 37.0);
        g3.addEdge("Buxtehude","Minden", null, 223.0);
        g3.addEdge("Buxtehude","Walsrode", null, 125.0);
        
        g4 = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
        g4.addEdge("v4", "s", null, 1.0);
        g4.addEdge("v3", "v1", null, 2.0);
        g4.addEdge("v5", "v6", null, 2.0);
        g4.addEdge("q", "s", null, 3.0);
        g4.addEdge("v4", "v1", null, 3.0);
        g4.addEdge("v2", "v4", null, 3.0);
        g4.addEdge("v6", "v3", null, 3.0);
        g4.addEdge("v7", "s", null, 3.0);
        g4.addEdge("v1", "v3", null, 4.0);
        g4.addEdge("v8", "v2", null, 4.0);
        g4.addEdge("q", "v5", null, 6.0);
        g4.addEdge("v6", "v4", null, 7.0);
        g4.addEdge("v5", "v2", null, 7.0);
        g4.addEdge("q", "v1", null, 8.0);
        g4.addEdge("v6", "s", null, 8.0);
        g4.addEdge("v7", "v8", null, 8.0);
        g4.addEdge("q", "v2", null, 9.0);
        g4.addEdge("v8", "s", null, 9.0);
        g4.addEdge("v1", "v7", null, 9.0);
        g4.addEdge("v2", "v3", null, 11.0);
        g4.addEdge("v7", "v2", null, 11.0);
        g4.addEdge("v3", "s", null, 12.0);
        g4.addEdge("v4", "v2", null, 12.0);
        
        g5 = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
        g5.addEdge("v1", "v2", null, 5.0);
        g5.addEdge("v1", "v3", null, 7.0);
        g5.addEdge("v1", "v4", null, 5.0);
        g5.addEdge("v1", "v5", null, 3.0);
        g5.addEdge("v1", "v6", null, 2.0);
        g5.addEdge("v1", "v7", null, 6.0);
        g5.addEdge("v2", "v3", null, 4.0);
        g5.addEdge("v2", "v4", null, 1.0);
        g5.addEdge("v2", "v5", null, 8.0);
        g5.addEdge("v2", "v6", null, 3.0);
        g5.addEdge("v2", "v7", null, 5.0);
        g5.addEdge("v3", "v4", null, 3.0);
        g5.addEdge("v3", "v5", null, 4.0);
        g5.addEdge("v3", "v6", null, 7.0);
        g5.addEdge("v3", "v7", null, 1.0);
        g5.addEdge("v4", "v5", null, 7.0);
        g5.addEdge("v4", "v6", null, 4.0);
        g5.addEdge("v4", "v7", null, 4.0);
        g5.addEdge("v5", "v6", null, 5.0);
        g5.addEdge("v5", "v7", null, 3.0);
        g5.addEdge("v6", "v7", null, 8.0);
        
        g6 = GKAGraphInterface.newGraph(GraphType.Directed);
        g6.addEdge("1", "2", null, null);
        g6.addEdge("1", "3", null, null);
        g6.addEdge("1", "8", null, null);
        g6.addEdge("2", "4", null, null);
        g6.addEdge("3", "5", null, null);
        g6.addEdge("3", "8", null, null);
        g6.addEdge("4", "5", null, null);
        g6.addEdge("5", "2", null, null);
        g6.addEdge("3", "4", null, null);
        g6.addEdge("4", "1", null, null);
        g6.addEdge("8", "5", null, null);
        g6.addEdge("6", "7", null, null);
        g6.addEdge("7", "9", null, null);
        g6.addEdge("9", "6", null, null);
        g6.addVertex("10");
        g6.addVertex("12");
        g6.addEdge("11", "12", null, null);

        g7 = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
        g7.addEdge("v4", "s", null, 1.0);
        g7.addEdge("v3", "v1", null, 2.0);
        g7.addEdge("v5", "v6", null, 2.0);
        g7.addEdge("q", "s", null, 3.0);
        g7.addEdge("v4", "v1", null, 3.0);
        g7.addEdge("v2", "v4", null, 3.0);
        g7.addEdge("v6", "v3", null, 3.0);
        g7.addEdge("v7", "s", null, 3.0);
        g7.addEdge("v1", "v3", null, 4.0);
        g7.addEdge("v8", "v2", null, 4.0);
        g7.addEdge("q", "v5", null, 6.0);
        g7.addEdge("v6", "v4", null, 7.0);
        g7.addEdge("v5", "v2", null, 7.0);
        g7.addEdge("q", "v1", null, 8.0);
        g7.addEdge("v6", "s", null, 8.0);
        g7.addEdge("v7", "v8", null, 8.0);
        g7.addEdge("q", "v2", null, 9.0);
        g7.addEdge("v8", "s", null, 9.0);
        g7.addEdge("v1", "v7", null, 9.0);
        g7.addEdge("v2", "v3", null, 11.0);
        g7.addEdge("v7", "v2", null, 11.0);
        g7.addEdge("v3", "s", null, 12.0);
        g7.addEdge("v4", "v2", null, 12.0);
        
        
        g8 = GKAGraphInterface.newGraph(GraphType.UndirectedWeighted);
        g8.addEdge("v1", "v2", null, 5.0);
        g8.addEdge("v1", "v3", null, 7.0);
        g8.addEdge("v1", "v4", null, 5.0);
        g8.addEdge("v1", "v5", null, 3.0);
        g8.addEdge("v1", "v6", null, 2.0);
        g8.addEdge("v1", "v7", null, 6.0);
        g8.addEdge("v2", "v3", null, 4.0);
        g8.addEdge("v2", "v4", null, 1.0);
        g8.addEdge("v2", "v5", null, 8.0);
        g8.addEdge("v2", "v6", null, 3.0);
        g8.addEdge("v2", "v7", null, 5.0);
        g8.addEdge("v3", "v4", null, 3.0);
        g8.addEdge("v3", "v5", null, 4.0);
        g8.addEdge("v3", "v6", null, 7.0);
        g8.addEdge("v3", "v7", null, 1.0);
        g8.addEdge("v4", "v5", null, 7.0);
        g8.addEdge("v4", "v6", null, 4.0);
        g8.addEdge("v4", "v7", null, 4.0);
        g8.addEdge("v5", "v6", null, 5.0);
        g8.addEdge("v5", "v7", null, 3.0);
        g8.addEdge("v6", "v7", null, 8.0);
    }
    
    
    @Test
    public void testNewGraphFile()
    {
        assertEquals(g1, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph1.gka")));
        assertEquals(g2, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph2.gka")));
        //assertEquals(g3, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph3.gka")));
        assertEquals(g4, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph4.gka")));
        assertEquals(g5, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph5.gka")));
        assertEquals(g6, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph6.gka")));
        assertEquals(g7, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph7.gka")));
        assertEquals(g8, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph8.gka")));
    }
    
  

    @Test
    public void testShortesPathBroadStringList()
    {
        assertEquals(new ArrayList<String>(Arrays.asList("Paderborn", "Hamburg", "Bremen")), ((GKAGraph) GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph3.gka"))).shortesPathBroadStringList("Paderborn","Bremen"));
        //graph1 von a nach j
        assertEquals(new ArrayList(Arrays.asList("a", "b", "j")), ((GKAGraph) GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph1.gka"))).shortesPathBroadStringList("a","j"));
        //assertEquals("[(a : b), (b : j)]", GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph1.gka")).shortesPathBroad("a","j").toString());
        //graph2 von a nach k mehrere Möglichkeiten
        assertEquals(new ArrayList(Arrays.asList("a", "d", "k")), ((GKAGraph) GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph2.gka"))).shortesPathBroadStringList("a","k"));
        assertEquals(2, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph2.gka")).shortesPathBroad("a","k").size());
        // graph3 von Paderborn nach Bremen
        //assertEquals("[(Paderborn : Hamburg) : 228.0, (Bremen : Hamburg) : 127.0]", GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph3.gka")).shortesPathBroad("Paderborn","Bremen").toString());
        //graph4 gewichtet von v4 nach s : 1.0
        assertEquals(new ArrayList(Arrays.asList("v4", "s")), ((GKAGraph) GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph4.gka"))).shortesPathBroadStringList("v4","s"));
        assertEquals("[(v4 : s) : 1.0]", GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph4.gka")).shortesPathBroad("v4","s").toString());
        //graph5 gewichtet von v1 nach v7 : 6.0
        assertEquals(new ArrayList(Arrays.asList("v1", "v7")), ((GKAGraph) GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph5.gka"))).shortesPathBroadStringList("v1","v7"));
       // assertEquals("[(v1 : v7) : 6.0]", GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph5.gka")).shortesPathBroad("v1","v7").toString());
       //graph6 von 10 nach 12 (keine Verbindung vorhanden)
        assertEquals(null, ((GKAGraph) GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph6.gka"))).shortesPathBroadStringList("10","12"));
        //assertEquals(null, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph6.gka")).shortesPathBroad("10","12"));
       //graph6 von 12 nach 11 gegen die Richtung
        assertEquals(null, ((GKAGraph) GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph6.gka"))).shortesPathBroadStringList("12","11"));
        assertEquals(null, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph6.gka")).shortesPathBroad("12","11"));
    }
    
    @Test
    public void testSaveGraph()
    {
        g1.saveGraph(new File("..\\aufgabe1Bsp\\test.gka"));
        assertEquals(g1, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\test.gka")));
        g2.saveGraph(new File("..\\aufgabe1Bsp\\test.gka"));
        assertEquals(g2, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\test.gka")));
       // g3.saveGraph(new File("..\\aufgabe1Bsp\\test.gka"));
       // assertEquals(g3, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\test.gka")));
        g4.saveGraph(new File("..\\aufgabe1Bsp\\test.gka"));
        assertEquals(g4, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\test.gka")));
        g5.saveGraph(new File("..\\aufgabe1Bsp\\test.gka"));
        assertEquals(g5, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\test.gka")));
        g6.saveGraph(new File("..\\aufgabe1Bsp\\test.gka"));
        assertEquals(g6, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\test.gka")));
        g7.saveGraph(new File("..\\aufgabe1Bsp\\test.gka"));
        assertEquals(g7, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\test.gka")));
        g8.saveGraph(new File("..\\aufgabe1Bsp\\test.gka"));
        assertEquals(g8, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\test.gka")));
    }
    
    private double getWayLength(Collection<GKAEdge> list){
        double retval = 0.0;
        if(list != null)
        {
            for(GKAEdge edge : list){
                retval += edge.getWeight();
            }
        }
        else
        {
            retval = -1.0;
        }
        return retval;
    }
    
    @Test 
    public void testGraph3()
    {
        GKAGraphInterface testGraph = GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph3.gka"));
        for(String vertex1 : testGraph.getjGraph().vertexSet())
        {
            for(String vertex2 : testGraph.getjGraph().vertexSet())
            {
                
               assertEquals(getWayLength(testGraph.dijkstra(vertex1, vertex2)), getWayLength(testGraph.floydWarschall(vertex1, vertex2)), 0.01); 
            }
        }
    }
    class RandomGraph
    {
        private GKAGraphInterface graph;
        private String way1Source;
        private String way1Target;
        private double way1Weight;
        private String way2Source;
        private String way2Target;
        private double way2Weight;
        
        RandomGraph(GKAGraphInterface graph, String way1Source, String way1Target, double way1Weight, String way2Source, String way2Target, double way2Weight)
        {
           this.setGraph(graph);
           this.setWay1Source(way1Source);
           this.setWay1Target(way1Target);
           this.setWay1Weight(way1Weight);
           this.setWay2Source(way2Source);
           this.setWay2Target(way2Target);
           this.setWay2Weight(way2Weight);
        }

        public GKAGraphInterface getGraph()
        {
            return graph;
        }
        
        private void setGraph(GKAGraphInterface graph)
        {
            this.graph = graph;
        }
        
        public String getWay1Source()
        {
            return way1Source;
        }

        private void setWay1Source(String way1Source)
        {
            this.way1Source = way1Source;
        }

        public String getWay1Target()
        {
            return way1Target;
        }

        private void setWay1Target(String way1Target)
        {
            this.way1Target = way1Target;
        }

        public double getWay1Weight()
        {
            return way1Weight;
        }

        private void setWay1Weight(double way1Weight)
        {
            this.way1Weight = way1Weight;
        }

        public String getWay2Source()
        {
            return way2Source;
        }

        private void setWay2Source(String way2Source)
        {
            this.way2Source = way2Source;
        }

        public String getWay2Target()
        {
            return way2Target;
        }

        private void setWay2Target(String way2Target)
        {
            this.way2Target = way2Target;
        }

        public double getWay2Weight()
        {
            return way2Weight;
        }

        private void setWay2Weight(double way2Weight)
        {
            this.way2Weight = way2Weight;
        }
        
        
    }
    
    public RandomGraph generateGraph(int vertexAnzahl, int edgeAnzahl)
    {
        GKAGraphInterface retval = GKAGraphInterface.newGraph(GraphType.DirectedWeighted);
        for(int i = 0; i < vertexAnzahl; i++)
        {
            retval.addVertex(String.valueOf(i));
        }
        for(int i = 0; i < (vertexAnzahl / 2) - 1; i++)
        {
            retval.addEdge(String.valueOf(i), String.valueOf(i+1), null, 0.1);
        }
        for(int i = vertexAnzahl / 2 ; i < vertexAnzahl; i++)
        {
            retval.addEdge(String.valueOf(i), String.valueOf(i+1), null, 0.1);
        }
        for(int i = 0 ; i < edgeAnzahl; i++)
        {
            int target = (int) (Math.random() * (vertexAnzahl - 1));
            int source = (int) (Math.random() * (vertexAnzahl - 1));
            retval.addEdge(String.valueOf(source), String.valueOf(target), null, ((Math.random() * edgeAnzahl) + ((vertexAnzahl / 2) + 1) * 0.1));
        }
        return new RandomGraph(retval, String.valueOf(0), String.valueOf((vertexAnzahl / 2) - 1), ((vertexAnzahl /2) - 1) * 0.1, String.valueOf(vertexAnzahl / 2), String.valueOf(vertexAnzahl - 1), ((vertexAnzahl /2) - 1) * 0.1);
    }
    
    @Test
    public void testRandomGraph()
    {
       long startTime = System.currentTimeMillis();
       RandomGraph testGraph = generateGraph(100, 6000);
       System.out.println(System.currentTimeMillis() - startTime);
       startTime = System.currentTimeMillis();
       assertEquals(getWayLength(testGraph.getGraph().dijkstra(testGraph.getWay1Source(), testGraph.getWay1Target())), testGraph.getWay1Weight(), 0.01);
       System.out.println(System.currentTimeMillis() - startTime);
       startTime = System.currentTimeMillis();
       assertEquals(getWayLength(testGraph.getGraph().dijkstra(testGraph.getWay2Source(), testGraph.getWay2Target())), testGraph.getWay2Weight(), 0.01);
       System.out.println(System.currentTimeMillis() - startTime);
       startTime = System.currentTimeMillis();
       assertEquals(getWayLength(testGraph.getGraph().floydWarschall(testGraph.getWay1Source(), testGraph.getWay1Target())), testGraph.getWay1Weight(), 0.01);
       System.out.println(System.currentTimeMillis() - startTime);
       startTime = System.currentTimeMillis();
       assertEquals(getWayLength(testGraph.getGraph().floydWarschall(testGraph.getWay2Source(), testGraph.getWay2Target())), testGraph.getWay2Weight(), 0.01);
       System.out.println(System.currentTimeMillis() - startTime);
       startTime = System.currentTimeMillis();
    }
}
    


