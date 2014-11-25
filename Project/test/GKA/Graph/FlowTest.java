package GKA.Graph;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FlowTest {

	GKAGraph graph4 = (GKAGraph) GKAGraphInterface.newGraph(new File("..\\TestGraphen\\graph4Own.gka"));
	GKAGraph complex = (GKAGraph) GKAGraphInterface.newGraph(new File("..\\TestGraphen\\TestGraph.gka"));
	double delta = 1.0E-9;
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
	public void ComplexTest(){
		EdmondKarp edmondKarp = new EdmondKarp(complex);
		FordFulkerson fordFulkerson = new FordFulkerson(complex);
		assertEquals(0.0, edmondKarp.maxFlow("S", "S"),delta);
		assertEquals(0.0, fordFulkerson.maxFlow("S", "S"),delta);
		assertEquals(9.0, edmondKarp.maxFlow("S", "T"),delta);
		assertEquals(9.0, fordFulkerson.maxFlow("S", "T"),delta);
		for(String v1: complex.getjGraph().vertexSet()){
			for(String v2: complex.getjGraph().vertexSet()){
				System.out.println(v1 + " " + v2);
				assertEquals(fordFulkerson.maxFlow(v1, v2), edmondKarp.maxFlow(v1,v2), delta);
			}
		}
	}

}
