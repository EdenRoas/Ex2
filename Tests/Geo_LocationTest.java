package Tests.Tests;

import api.GeoLocation;
import api.Geo_Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Geo_LocationTest {
    double x1 = 35.18869800968523, x2 = 35.187594216303474, x3 = 35.19381366747377,
    y1 = 32.104927164705884, y2 = 32.10378225882353, y3 = 32.102419275630254,
    z = 0.0;
    GeoLocation geo1 = new Geo_Location();
    GeoLocation geo2 = new Geo_Location(x1, y1, z);
    GeoLocation geo3 = new Geo_Location(x2, y2, z);
    GeoLocation geo4 = new Geo_Location(x3, y3, z);
    GeoLocation geo5 = new Geo_Location();

    @Test
    void x() {
        assertEquals(x1, geo2.x());
        assertEquals(x2, geo3.x());
        assertEquals(x3, geo4.x());
        assertEquals(0.0, geo1.x());
    }

    @Test
    void y() {
        assertEquals(y1, geo2.y());
        assertEquals(y2, geo3.y());
        assertEquals(y3, geo4.y());
        assertEquals(0.0, geo1.y());
    }

    @Test
    void z() {
        assertEquals(z, geo2.z());
        assertEquals(z, geo3.z());
        assertEquals(z, geo4.z());
        assertEquals(0.0, geo1.z());
    }

    @Test
    void distance() {
        double dist1 = 0.00159, dist2 = 47.6, dist3 = 0.00637, dist4 = 0.0;
        assertEquals(dist1, geo2.distance(geo3), 0.0001);
        assertEquals(dist2, geo1.distance(geo4), 0.1);
        assertEquals(dist3, geo3.distance(geo4), 0.0001);
        assertEquals(dist4, geo1.distance(geo5), 0.1);
    }
}