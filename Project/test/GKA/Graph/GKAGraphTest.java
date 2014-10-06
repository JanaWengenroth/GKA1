package GKA.Graph;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashSet;
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
        MainControler.main(null);
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

        g7 = GKAGraphInterface.newGraph(GraphType.Directed);
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
        assertEquals(g3, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph3.gka")));
        assertEquals(g4, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph4.gka")));
        assertEquals(g5, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph5.gka")));
        assertEquals(g6, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph6.gka")));
        assertEquals(g7, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph7.gka")));
        assertEquals(g8, GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph8.gka")));
    }

//    @Test
//    public void testShortesPathBroadStringList()
//    {
//        fail("Not yet implemented");
//    }

}
