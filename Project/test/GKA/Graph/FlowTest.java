package GKA.Graph;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FlowTest {

	GKAGraph graph4 = (GKAGraph) GKAGraphInterface.newGraph(new File("..\\TestGraphen\\graph4Own.gka"));
	//GKAGraph graph4 = (GKAGraph) GKAGraphInterface.newGraph(new File("..\\aufgabe1Bsp\\graph4.gka"));
	GKAGraph complex = (GKAGraph) GKAGraphInterface.newGraph(new File("..\\TestGraphen\\TestGraph.gka"));
	double delta = 1.0E-9;
	@Test
	public void ComplexTest(){
		EdmondKarp edmondKarp = new EdmondKarp(complex);
		FordFulkerson fordFulkerson = new FordFulkerson(complex);
		assertEquals(Double.POSITIVE_INFINITY, edmondKarp.maxFlow("S", "S"),delta);
		assertEquals(Double.POSITIVE_INFINITY, fordFulkerson.maxFlow("S", "S"),delta);
		assertEquals(9.0, edmondKarp.maxFlow("S", "T"),delta);
		assertEquals(9.0, fordFulkerson.maxFlow("S", "T"),delta);
		for(String v1: complex.getjGraph().vertexSet()){
			for(String v2: complex.getjGraph().vertexSet()){
				assertEquals(fordFulkerson.maxFlow(v1, v2), edmondKarp.maxFlow(v1,v2), delta);
			}
		}
	}
	
	@Test
	public void Graph4Test() {
		EdmondKarp edmondKarp = new EdmondKarp(graph4);
		FordFulkerson fordFulkerson = new FordFulkerson(graph4);
		for(String v1: graph4.getjGraph().vertexSet()){
			for(String v2: graph4.getjGraph().vertexSet()){
				assertEquals(fordFulkerson.maxFlow(v1, v2), edmondKarp.maxFlow(v1,v2), delta);
			}
		}
	}
	
	@Test
    public void bigNet(){
    	GKAGraph big = (GKAGraph) GKAGraphInterface.newGraphBigNet(50, 800);
    	EdmondKarp edmondKarp = new EdmondKarp(big);
    	FordFulkerson fordFulkerson = new FordFulkerson(big);
    	long timeFord =0;
    	long timeEdmond = 0;
    	for(int i = 0; i< 100;i++){
    	    int sink = (int)(Math.random() * 50);
    	    int source = (int)(Math.random() * 50);
    		assertEquals(edmondKarp.maxFlow(String.valueOf(source), String.valueOf(sink)), 
    		        fordFulkerson.maxFlow(String.valueOf(source), String.valueOf(sink)), delta);
    		timeFord += fordFulkerson.getRunTime();
    		timeEdmond += edmondKarp.getRunTime();
    	}
    	System.out.println("Durchschnittliche Laufzeit Edmond Karp: " + (timeEdmond/100) + " Ford Fulkerson: " + (timeFord/100));
    	
    	big = (GKAGraph) GKAGraphInterface.newGraphBigNet(100,1600);
    	edmondKarp = new EdmondKarp(big);
    	fordFulkerson = new FordFulkerson(big);
    	timeFord =0;
    	timeEdmond = 0;
    	for(int i = 0; i< 100;i++){
    	    System.out.println("running round: " + i);
    	    int sink = (int)(Math.random() * 100);
            int source = (int)(Math.random() * 100);
            assertEquals(edmondKarp.maxFlow(String.valueOf(source), String.valueOf(sink)), 
                    fordFulkerson.maxFlow(String.valueOf(source), String.valueOf(sink)), delta);
    		timeFord += fordFulkerson.getRunTime();
    		timeEdmond += edmondKarp.getRunTime();
    	}
    	System.out.println("Durchschnittliche Laufzeit Edmond Karp: " + (timeEdmond/100) + " Ford Fulkerson" + (timeFord/100));
    	
    }

}
