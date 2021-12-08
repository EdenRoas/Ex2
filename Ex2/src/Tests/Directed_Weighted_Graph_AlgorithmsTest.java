package Tests;
import api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Directed_Weighted_Graph_AlgorithmsTest {
    DirectedWeightedGraph grpah = new Directed_Weighted_Graph();

    @Test
    void init() {
        DirectedWeightedGraphAlgorithms gl = new Directed_Weighted_Graph_Algorithms();
        gl.init(grpah);
        assertTrue(grpah.equals(gl.getGraph()));
    }

    @Test
    void getGraph() {
        DirectedWeightedGraphAlgorithms gl= new Directed_Weighted_Graph_Algorithms();
        gl.init(grpah);
        assertTrue(grpah.equals(gl.getGraph()));
    }

    @Test
    void copy() {
        DirectedWeightedGraphAlgorithms gl=new Directed_Weighted_Graph_Algorithms();
        gl.init(grpah);
        DirectedWeightedGraph g1= gl.copy();
        //assertTrue(grpah.equals(g1));
        assertTrue(grpah.nodeIter().equals(g1.nodeIter()));
//        assertTrue(grpah.getMC() == g1.getMC());
//        assertTrue(grpah.edgeIter() == g1.edgeIter());
    }

    @Test
    void isConnected() {
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }

    @Test
    void tsp() {

    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}