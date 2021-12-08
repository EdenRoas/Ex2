package Tests.Tests;

import api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Node_DataTest {
    GeoLocation loc1 = new Geo_Location();//x = 0, y = 0, z = 0
    Node_Data n1 = new Node_Data(0, loc1, 3.7, "0.0, 0.0, 0.0", Node_Data.WHITE);
    GeoLocation loc2 = new Geo_Location(35.20319591121872, 32.10318254621849, 0.0);//x = 35.20319591121872, y = 32.10318254621849, z = 0.0
    Node_Data n2 = new Node_Data(1, loc2, 4.9, "35.20319591121872,32.10318254621849,0.0", Node_Data.GRAY);
    GeoLocation loc3 = new Geo_Location(35.19381366747377, 32.102419275630254, 0.0);//x = 35.19381366747377, y = 32.102419275630254, z = 0.0
    Node_Data n3 = new Node_Data(16, loc3, 0.6, "35.19381366747377,32.102419275630254,0.0", Node_Data.BLACK);

    @Test
    void getKey() {
        int k1 = 0, k2 = 1, k3 = 16;
        assertEquals(k1, n1.getKey());
        assertEquals(k2, n2.getKey());
        assertEquals(k3, n3.getKey());
    }

    @Test
    void getLocation() {
        assertEquals(loc1, n1.getLocation());
        assertEquals(loc2, n2.getLocation());
        assertEquals(loc3, n3.getLocation());
    }

    @Test
    void setLocation() {
        GeoLocation loc4 = new Geo_Location(35.187594216303474, 32.10378225882353, 0.0);//x = 35.187594216303474, y = 32.10378225882353, z = 0
        GeoLocation loc5 = new Geo_Location(35.18869800968523, 32.104927164705884, 0.0);//x = 35.18869800968523, y = 32.104927164705884, z = 0
        GeoLocation loc6 = new Geo_Location(35.189568308313156, 32.106617263865544, 0.0);//x = 35.189568308313156, y = 32.106617263865544, z = 0
        n1.setLocation(loc4);
        n2.setLocation(loc5);
        n3.setLocation(loc6);
        assertEquals(loc4, n1.getLocation());
        assertEquals(loc5, n2.getLocation());
        assertEquals(loc6, n3.getLocation());

    }

    @Test
    void getWeight() {
        double w1 = 3.7, w2 = 4.9, w3 = 0.6;
        assertEquals(w1, n1.getWeight());
        assertEquals(w2, n2.getWeight());
        assertEquals(w3, n3.getWeight());
    }

    @Test
    void setWeight() {
        double w4 = 1.0, w5 = 8.1, w6 = 2.3;
        n1.setWeight(w4);
        n2.setWeight(w5);
        n3.setWeight(w6);
        assertEquals(w4, n1.getWeight());
        assertEquals(w5, n2.getWeight());
        assertEquals(w6, n3.getWeight());
    }

    @Test
    void getInfo() {
        String inf1 = "0.0, 0.0, 0.0", inf2 = "35.20319591121872,32.10318254621849,0.0", inf3 = "35.19381366747377,32.102419275630254,0.0";
        assertTrue(inf1 == n1.getInfo());
        assertTrue(inf2 == n2.getInfo());
        assertTrue(inf3 == n3.getInfo());
    }

    @Test
    void setInfo() {
        String inf1 = "35.187594216303474, 32.10378225882353, 0.0", inf2 = "35.18869800968523, 32.104927164705884, 0.0", inf3 = "35.189568308313156, 32.106617263865544, 0.0";
        n1.setInfo(inf1);
        n2.setInfo(inf2);
        n3.setInfo(inf3);
        assertTrue(inf1 == n1.getInfo());
        assertTrue(inf2 == n2.getInfo());
        assertTrue(inf3 == n3.getInfo());
    }

    @Test
    void getTag() {
        int ta1 = Node_Data.WHITE, ta2 = Node_Data.GRAY, ta3 = Node_Data.BLACK;
        assertEquals(ta1, n1.getTag());
        assertEquals(ta2, n2.getTag());
        assertEquals(ta3, n3.getTag());
    }

    @Test
    void setTag() {
        int ta3 = Node_Data.WHITE, ta1 = Node_Data.GRAY, ta2 = Node_Data.BLACK;
        n1.setTag(ta1);
        n3.setTag(ta3);
        n2.setTag(ta2);
        assertEquals(ta1, n1.getTag());
        assertEquals(ta2, n2.getTag());
        assertEquals(ta3, n3.getTag());
    }
}