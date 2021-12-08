package Tests;

import api.Edge_Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Edge_DataTest {

    int src1 = 0, src2 = 15, src3 = 8, dest1 = 4, dest2 = 15, dest3 = 11,
            ta1 = Edge_Data.WHITE, ta2 = Edge_Data.BLACK, ta3 = Edge_Data.GRAY;
    double w1 = 0.6, w2 = 9.7, w3 = 0.3;
    String  inf1 = "src: 0, w: 0.6, dest: 4",
            inf2 = "src: 0, w: 0.6, dest: 4",
            inf3 = "src: 15, w: 9.7, dest: 15",
            inf4 = "src: 8, w: 0.3, dest: 11";
    Edge_Data e1 = new Edge_Data(src1, dest1, w1);
    Edge_Data e2 = new Edge_Data(src1, dest1, w1, inf2, ta1);
    Edge_Data e3 = new Edge_Data(src2, dest2, w2, inf3, ta2);
    Edge_Data e4 = new Edge_Data(src3, dest3, w3, inf4, ta3);

    @Test
    void getSrc() {
        assertEquals(src1, e1.getSrc());
        assertEquals(src1, e2.getSrc());
        assertEquals(src2, e3.getSrc());
        assertEquals(src3, e4.getSrc());
    }

    @Test
    void getDest() {
        assertEquals(dest1, e1.getDest());
        assertEquals(dest1, e2.getDest());
        assertEquals(dest2, e3.getDest());
        assertEquals(dest3, e4.getDest());
    }

    @Test
    void getWeight() {
        assertEquals(w1, e1.getWeight());
        assertEquals(w1, e2.getWeight());
        assertEquals(w2, e3.getWeight());
        assertEquals(w3, e4.getWeight());
    }

    @Test
    void getInfo() {
        assertEquals(inf1, e1.getInfo());
        assertEquals(inf2, e2.getInfo());
        assertEquals(inf3, e3.getInfo());
        assertEquals(inf4, e4.getInfo());
    }

    @Test
    void setInfo() {
        e1.setInfo(inf2);
        e2.setInfo(inf3);
        e3.setInfo(inf4);
        e4.setInfo(inf1);
        assertTrue(inf2 == e1.getInfo());
        assertTrue(inf3 == e2.getInfo());
        assertTrue(inf4 == e3.getInfo());
        assertTrue(inf1 == e4.getInfo());
    }

    @Test
    void getTag() {
        assertEquals(ta1, e1.getTag());
        assertEquals(ta1, e2.getTag());
        assertEquals(ta2, e3.getTag());
        assertEquals(ta3, e4.getTag());
    }

    @Test
    void setTag() {
        e1.setTag(ta3);
        e2.setTag(ta2);
        e3.setTag(ta1);
        e4.setTag(ta2);
        assertEquals(ta3, e1.getTag());
        assertEquals(ta2, e2.getTag());
        assertEquals(ta1, e3.getTag());
        assertEquals(ta2, e4.getTag());
    }
}