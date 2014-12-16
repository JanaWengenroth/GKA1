package GKA.Graph;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

public class TourTest
{

    @Test
    public void testNearestNeighbourGraph5()
    {
        GKAGraphInterface graph = GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph5.gka"));
        assertEquals(Arrays.asList("v1", "v6", "v2", "v4", "v3", "v7", "v5", "v1"), graph.getNearestNeighbour("v1").getWay());
    }
    
    @Test
    public void testMSTGraph5()
    {
        GKAGraphInterface graph = GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph5.gka"));
        assertEquals(Arrays.asList("v1", "v5", "v6", "v2", "v4", "v3", "v7", "v1"), graph.getMSTHeuristic("v1").getWay());
    }
    
    @Test
    public void test()
    {
        int testDurchlaeufe = 100;
        long mittlereZeitMST = 0;
        long mittlereZeitNearest = 0; 
        Double mittlererWeg = 0.0;
        Double mittlereDifferenzWeg = 0.0;
        for(int j = 0; j < testDurchlaeufe; j++)
        {
            GKAGraphInterface graph = GKAGraphInterface.newTourGraph(25);
            MinimumSpanningTree minimumSpanningTree = graph.getMinimumSpanningTree();
            NearestNeighbour nearestNeighbour = graph.getNearestNeighbour("0");
            MSTHeuristic mst = graph.getMSTHeuristic("0");
            assertEquals(graph.getjGraph().vertexSet(), mst.getTour().getjGraph().vertexSet());
            assertEquals(graph.getjGraph().vertexSet(), nearestNeighbour.getNearestNeighbourTour().getjGraph().vertexSet());
            for(int i = 0; i < 25; i++)
            {
                assertEquals(2, mst.getTour().getjGraph().edgesOf(String.valueOf(i)).size());
                assertEquals(2, nearestNeighbour.getNearestNeighbourTour().getjGraph().edgesOf(String.valueOf(i)).size());
            }
           
            ArrayList<GKAEdge> nearestNeighbourEdgeList = new ArrayList<>(nearestNeighbour.getNearestNeighbourTour().getjGraph().edgeSet());
            Collections.shuffle(nearestNeighbourEdgeList);
            nearestNeighbour.getNearestNeighbourTour().removeEdge(nearestNeighbourEdgeList.get(0).getSource().toString(),nearestNeighbourEdgeList.get(0).getTarget().toString());
            assertEquals(graph.getjGraph().vertexSet(),new HashSet<>(((GKAGraph) nearestNeighbour.getNearestNeighbourTour()).dijkstraStringList(nearestNeighbourEdgeList.get(0).getSource().toString(),nearestNeighbourEdgeList.get(0).getTarget().toString())));
           
            ArrayList<GKAEdge> mstEdgeList = new ArrayList<>(mst.getTour().getjGraph().edgeSet());
            Collections.shuffle(mstEdgeList);
            mst.getTour().removeEdge(mstEdgeList.get(0).getSource().toString(),mstEdgeList.get(0).getTarget().toString());
            assertEquals(graph.getjGraph().vertexSet(),new HashSet<>(((GKAGraph) mst.getTour()).dijkstraStringList(mstEdgeList.get(0).getSource().toString(),mstEdgeList.get(0).getTarget().toString())));
            assertTrue(minimumSpanningTree.getLength()*2 >= mst.getLength());
            assertTrue(minimumSpanningTree.getLength()*2 >= nearestNeighbour.getLength());
            
            mittlereZeitMST += mst.getRunTime();
            mittlereZeitNearest += nearestNeighbour.getRunTime();
            mittlereDifferenzWeg += (mst.getLength() - nearestNeighbour.getLength());
            mittlererWeg += (mst.getLength() + nearestNeighbour.getLength()/2);
        }
        
        System.out.println("mittlere ZeitMST: " + (mittlereZeitMST/testDurchlaeufe));
        System.out.println("mittlere ZeitNN: " + (mittlereZeitNearest/testDurchlaeufe));
        System.out.println("der mittlere Weg von MST ist " + (((mittlereDifferenzWeg/testDurchlaeufe)/(mittlererWeg/testDurchlaeufe))*100) + "% größer als der des NN");
    }
}
