package Tests;
import api.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Directed_Weighted_Graph_AlgorithmsTest {
    double x1 = 35.18869800968523, x2 = 35.187594216303474, x3 = 35.19381366747377, x4=23.2457754356 ,x5=22.5688766787,
            y1 = 32.104927164705884, y2 = 32.10378225882353, y3 = 32.102419275630254, y4=30.0 , y5=32.55553,
            z = 0.0 , w1 = 0.6, w2 = 9.7, w3 = 0.3, w4=10.2, w5=12.3;
    GeoLocation loc1 = new Geo_Location(x1,y1,0);
    GeoLocation loc2 = new Geo_Location(x2,y2,0);
    GeoLocation loc3 = new Geo_Location(x3,y3,0);
    GeoLocation loc4 = new Geo_Location(x4,y4,0);
    GeoLocation loc5 = new Geo_Location(x5,y5,0);
    String inf1 = "35.18869800968523,32.104927164705884,0.0";
    String inf2 = "32.104927164705884,32.10378225882353,0.0";
    String inf3 = "35.19381366747377,32.102419275630254,0.0";
    String inf4 = "23.2457754356,30.0,0.0";
    String inf5 = "22.5688766787,32.55553,0.0";
    int ta1 = Node_Data.WHITE, ta2 = Node_Data.GRAY, ta3 = Node_Data.BLACK;
    int src1 = 0, src2 = 1, src3 = 2, src4 = 5, dest1 = 3, dest2 = 4, dest3 = 15;
    NodeData n1 = new Node_Data(src1, loc1, w1,inf1, ta1);
    NodeData n2 = new Node_Data(src2,loc2, w2, inf2, ta2);
    NodeData n3 = new Node_Data(src3,loc3, w3, inf3, ta3);
    NodeData n4 = new Node_Data(dest1, loc4, w4,inf4, ta1);
    NodeData n5 = new Node_Data(dest2, loc5, w5,inf5, ta2);
    NodeData n6 = new Node_Data(src4, loc5, w5,inf5, ta2);
    double we1=4 , we2=8, we3=11, we4=12 ,we5=22 , we6=31, we7=12, we8=3, we9=10 ,we10=0.8;
    Edge_Data e1= new Edge_Data(n1.getKey(),n2.getKey(),we1);
    Edge_Data e2= new Edge_Data(n2.getKey(),n3.getKey(),we2);
    Edge_Data e3= new Edge_Data(n3.getKey(),n4.getKey(),we3);
    Edge_Data e4= new Edge_Data(n4.getKey(),n5.getKey(),we4);
    Edge_Data e5= new Edge_Data(n5.getKey(),n1.getKey(),we5);
    Edge_Data e6= new Edge_Data(n5.getKey(),n5.getKey(),we6);
    DirectedWeightedGraph grpah = new Directed_Weighted_Graph();
    DirectedWeightedGraphAlgorithms gl = new Directed_Weighted_Graph_Algorithms(grpah);



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
        assertTrue(grpah.nodeSize()==g1.nodeSize());
        assertTrue(grpah.edgeSize()==g1.edgeSize());
        assertTrue(grpah.getMC() == g1.getMC());
        Iterator<EdgeData> graphIter = grpah.edgeIter();
        Iterator<EdgeData> g1Iter = g1.edgeIter();
        Iterator<NodeData> graph_Iter = grpah.nodeIter();
        Iterator<NodeData> g1_Iter = g1.nodeIter();
        while (g1Iter.hasNext() || g1_Iter.hasNext())
        {
            assertSame(g1Iter.next(), graphIter.next());
            assertSame(g1_Iter.next(), graph_Iter.next());

        }
    }

    @Test
    void isConnected() {
        DirectedWeightedGraphAlgorithms gl=new Directed_Weighted_Graph_Algorithms();
        DirectedWeightedGraph g=new Directed_Weighted_Graph();
        gl.init(g);
        assertTrue(gl.isConnected());
        GeoLocation loc1 = new Geo_Location();//x = 0, y = 0, z = 0
        Node_Data n1 = new Node_Data(0, loc1, 3.7, "0.0, 0.0, 0.0", Node_Data.WHITE);
        g.addNode(n1);
        assertTrue(gl.isConnected());
        for(int i=1;i<3;i++)
        {

            g.addNode(new Node_Data(i,new Geo_Location(1+i, 2+i, 0),i+1));
        }
        assertFalse(gl.isConnected());
        g.connect(0,1,4);
        g.connect(0,2,3);
        g.connect(1,2,7);
        g.connect(1,0,2);
        g.connect(2,0,6);
        g.connect(2,1,1);
        assertTrue(gl.isConnected());

    }

    @Test
    void shortestPathDist() {
        DirectedWeightedGraphAlgorithms ga1 = new Directed_Weighted_Graph_Algorithms();
        ga1.init(grpah);
        grpah.addNode(n1);
        grpah.addNode(n2);
        grpah.addNode(n3);
        grpah.addNode(n4);
        grpah.addNode(n5);
        grpah.addNode(n6);
        grpah.connect(n1.getKey(), n2.getKey(), we1);
        grpah.connect(n2.getKey(), n1.getKey(), we5);
        grpah.connect(n2.getKey(), n3.getKey(), we2);
        grpah.connect(n3.getKey(), n2.getKey(), we6);
        grpah.connect(n3.getKey(), n4.getKey(), we3);
        grpah.connect(n4.getKey(), n3.getKey(), we7);
        grpah.connect(n4.getKey(), n5.getKey(), we4);
        grpah.connect(n5.getKey(), n4.getKey(), we8);
        grpah.connect(n1.getKey(), n5.getKey(), we9);
        grpah.connect(n5.getKey(), n1.getKey(), we10);
        assertEquals(12, ga1.shortestPathDist(n1.getKey(), n3.getKey()));
        assertEquals(10, ga1.shortestPathDist(n1.getKey(), n5.getKey()));
        assertEquals(-1, ga1.shortestPathDist(n1.getKey(), n1.getKey()));
        assertEquals(19, ga1.shortestPathDist(n2.getKey(), n4.getKey()));
        assertEquals(12.8, ga1.shortestPathDist(n4.getKey(), n1.getKey()));
        assertTrue(23 == ga1.shortestPathDist(n3.getKey(), n5.getKey()));
        assertEquals(-1, ga1.shortestPathDist(n3.getKey(), n6.getKey()));
    }

    @Test
    void shortestPath() {
        grpah.addNode(n1);
        grpah.addNode(n2);
        grpah.addNode(n3);
        grpah.addNode(n4);
        grpah.addNode(n5);
        grpah.addNode(n6);
        grpah.connect(n1.getKey(), n2.getKey(), we1);
        grpah.connect(n2.getKey(), n1.getKey(), we5);
        grpah.connect(n2.getKey(), n3.getKey(), we2);
        grpah.connect(n3.getKey(), n2.getKey(), we6);
        grpah.connect(n3.getKey(), n4.getKey(), we3);
        grpah.connect(n4.getKey(), n3.getKey(), we7);
        grpah.connect(n4.getKey(), n5.getKey(), we4);
        grpah.connect(n5.getKey(), n4.getKey(), we8);
        grpah.connect(n1.getKey(), n5.getKey(), we9);
        grpah.connect(n5.getKey(), n1.getKey(), we10);
        gl.init(grpah);
        List<NodeData> path = gl.shortestPath(n1.getKey(),n1.getKey());
        assertEquals(null, path);
        path = gl.shortestPath(n1.getKey(),n3.getKey());
        List<NodeData> checkPath = new ArrayList<>();
        checkPath.add(n1);
        checkPath.add(n2);
        checkPath.add(n3);
        assertEquals(checkPath, path);
        path = gl.shortestPath(n4.getKey(),n1.getKey());
        checkPath = new ArrayList<>();
        checkPath.add(n4);
        checkPath.add(n5);
        checkPath.add(n1);
        assertEquals(checkPath, path);
        path = gl.shortestPath(n1.getKey(),n6.getKey());
        assertEquals(null, path);
    }

    @Test
    void center() {
        DirectedWeightedGraphAlgorithms ga1 = new Directed_Weighted_Graph_Algorithms();
        ga1.init(grpah);
        grpah.addNode(n1);
        grpah.addNode(n2);
        grpah.addNode(n3);
        grpah.addNode(n4);
        grpah.addNode(n5);
        grpah.connect(n1.getKey(), n2.getKey(), we1);
        grpah.connect(n2.getKey(), n1.getKey(), we5);
        grpah.connect(n2.getKey(), n3.getKey(), we2);
        grpah.connect(n3.getKey(), n2.getKey(), we6);
        grpah.connect(n3.getKey(), n4.getKey(), we3);
        grpah.connect(n4.getKey(), n3.getKey(), we7);
        grpah.connect(n4.getKey(), n5.getKey(), we4);
        grpah.connect(n5.getKey(), n4.getKey(), we8);
        grpah.connect(n1.getKey(), n5.getKey(), we9);
        grpah.connect(n5.getKey(), n1.getKey(), we10);
        assertEquals(n5, ga1.center());
    }

    @Test
    void tsp() {

        GeoLocation p0 = new Geo_Location(0,0,0);
        GeoLocation p1 = new Geo_Location(1,2,0);
        GeoLocation p2 = new Geo_Location(1,5,0);
        GeoLocation p3 = new Geo_Location(4,4,0);
        GeoLocation p4 = new Geo_Location(4,3,0);
        GeoLocation p5 = new Geo_Location(4,0,0);
        GeoLocation p6 = new Geo_Location(9,2,0);
        NodeData n0 = new Node_Data(0, p0, we1);
        NodeData n1 = new Node_Data(1, p1, we2);
        NodeData n2 = new Node_Data(2, p2, we3);
        NodeData n3 = new Node_Data(3, p3, we4);
        NodeData n4 = new Node_Data(4, p4, we5);
        NodeData n5 = new Node_Data(5, p5, we6);
        NodeData n6 = new Node_Data(6, p6, we7);
        grpah.addNode(n0);
        grpah.addNode(n1);
        grpah.addNode(n2);
        grpah.addNode(n3);
        grpah.addNode(n4);
        grpah.addNode(n5);
        grpah.addNode(n6);
        grpah.connect(n0.getKey(),n1.getKey(),1);
        grpah.connect(n1.getKey(),n2.getKey(),1);
        grpah.connect(n2.getKey(),n1.getKey(),2);
        grpah.connect(n2.getKey(),n3.getKey(),2);
        grpah.connect(n3.getKey(),n4.getKey(),1);
        grpah.connect(n4.getKey(),n3.getKey(),1);
        grpah.connect(n2.getKey(),n4.getKey(),4);
        grpah.connect(n4.getKey(),n2.getKey(),2);
        grpah.connect(n4.getKey(),n6.getKey(),5);
        grpah.connect(n0.getKey(),n6.getKey(),15);
        grpah.connect(n5.getKey(),n6.getKey(),12);
        gl.init(grpah);
        List<NodeData> subGraph1 = new ArrayList<NodeData>();
        subGraph1.add(n3);
        subGraph1.add(n4);
        subGraph1.add(n2);
        ArrayList<NodeData> subGraphTSP1 = (ArrayList<NodeData>) gl.tsp(subGraph1);
        double pathW = 0.0;
        for(int i=0; i<=subGraphTSP1.size()-2; i++){
            pathW += gl.shortestPathDist(subGraphTSP1.get(i).getKey(),subGraphTSP1.get(i+1).getKey());
        }
        assertTrue(pathW >= 3 && pathW <= 8);
        assertFalse(pathW <= 2);
    }

    @Test
    void save() {

    }

    @Test
    void load() {
        gl.init(grpah);
       // assertEquals(false,gl.load("C:\\Users\\edenr\\Downloads\\OOP_2021-main (2)\\OOP_2021-main\\Assignments\\Ex2\\data\\test.json"));
        assertEquals(true,gl.load("C:\\Users\\edenr\\Downloads\\OOP_2021-main (2)\\OOP_2021-main\\Assignments\\Ex2\\data\\G1.json"));
       // System.out.println(gl.getGraph().toString());

    }
}