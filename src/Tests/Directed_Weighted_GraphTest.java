package Tests;
import api.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class Directed_Weighted_GraphTest {

    double x1 = 35.18869800968523, x2 = 35.187594216303474, x3 = 35.19381366747377, x4=23.2457754356 ,x5=22.5688766787,
            y1 = 32.104927164705884, y2 = 32.10378225882353, y3 = 32.102419275630254, y4=30.0 , y5=32.55553,
            z = 0.0 , w1 = 0.6, w2 = 9.7, w3 = 0.3, w4=10.2, w5=12.3;
    Geo_Location loc1 = new Geo_Location(x1,y1,0);
    Geo_Location loc2 = new Geo_Location(x2,y2,0);
    Geo_Location loc3 = new Geo_Location(x3,y3,0);
    Geo_Location loc4 = new Geo_Location(x4,y4,0);
    Geo_Location loc5 = new Geo_Location(x5,y5,0);
    String inf1 = "35.18869800968523,32.104927164705884,0.0";
    String inf2 = "32.104927164705884,32.10378225882353,0.0";
    String inf3 = "35.19381366747377,32.102419275630254,0.0";
    String inf4 = "23.2457754356,30.0,0.0";
    String inf5 = "22.5688766787,32.55553,0.0";
    int ta1 = Node_Data.WHITE, ta2 = Node_Data.GRAY, ta3 = Node_Data.BLACK;
    int src1 = 0, src2 = 1, src3 = 2, dest1 = 3, dest2 = 4, dest3 = 15;
    Node_Data n1 = new Node_Data(src1, loc1, w1,inf1, ta1);
    Node_Data n2 = new Node_Data(src2,loc2, w2, inf2, ta2);
    Node_Data n3 = new Node_Data(src3,loc3, w3, inf3, ta3);
    Node_Data n4 = new Node_Data(dest1, loc4, w4,inf4, ta1);
    Node_Data n5 = new Node_Data(dest2, loc5, w5,inf5, ta2);
    double we1=4 , we2=8, we3=11, we4=12 ,we5=22 , we6=31;
    Edge_Data e1= new Edge_Data(n1.getKey(),n2.getKey(),we1);
    Edge_Data e2= new Edge_Data(n2.getKey(),n3.getKey(),we2);
    Edge_Data e3= new Edge_Data(n3.getKey(),n4.getKey(),we3);
    Edge_Data e4= new Edge_Data(n4.getKey(),n5.getKey(),we4);
    Edge_Data e5= new Edge_Data(n5.getKey(),n1.getKey(),we5);
    Edge_Data e6= new Edge_Data(n5.getKey(),n5.getKey(),we6);
    Directed_Weighted_Graph g1 = new Directed_Weighted_Graph();
    Directed_Weighted_Graph g2 = new Directed_Weighted_Graph();
    Directed_Weighted_Graph g3 = new Directed_Weighted_Graph();


    @Test
    void getNodeMap() {
    }

    @Test
    void getEdgeMap() {
    }

    @Test
    void getNode() {
    }

    @Test
    void getEdge() {
    }

    @Test
    void addNode() {
        HashMap<Integer,NodeData> nods1 = new HashMap<>();
//        HashMap<Integer,NodeData> nods2 = new HashMap<>();
//        HashMap<Integer,NodeData> nods3 = new HashMap<>();
        nods1.put(n1.getKey(),n1);
        nods1.put(n2.getKey(),n2);
        nods1.put(n3.getKey(),n3);
//        nods1.put(n4.getKey(),n4);
//        nods1.put(n5.getKey(),n5);
//        nods2.put(n5.getKey(),n5);
//        nods3.put(n1.getKey(),n1);
//        nods3.put(n2.getKey(),n2);
//        nods3.put(n3.getKey(),n3);
        g1.addNode(n1);
        g1.addNode(n2);
        g1.addNode(n3);
//        g1.addNode(n4);
//        g1.addNode(n5);
//        g2.addNode(n5);
//        g3.addNode(n1);
//        g3.addNode(n2);
//        g3.addNode(n3);
        assertEquals(nods1, g1.getNodeMap());
        //assertTrue(nods1.equals(g1.getNodeMap()));
//        assertTrue(nods2.equals(g2.getNodeMap()));
//        assertTrue(nods3.equals(g3.getNodeMap()));
    }

    @Test
    void getCenter() {
    }

    @Test
    void connect() {
    }

    @Test
    void nodeIter() {
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}