package api;
import api.GeoLocation;

import java.util.ArrayList;
import java.util.HashMap;

public class Node_Data implements NodeData{
    private final int key;
    private int tag;
    private GeoLocation location;
    private double weight;
    private String info;
    public static final int WHITE = 0, GRAY = 1, BLACK = 2;
    private ArrayList<Node_Data> list_of_neighbors;

    /**
     * for the Node tag: white = 0,   gray = 1,   black = 2,
     * So that white symbolizes the node hasn't been visited,
     * gray symbolizes we already visit this node
     * and black symbolizes we've done with this node, as a default all nodes are Initialized to white.
     */
    public Node_Data(int key, GeoLocation location, double weight) {
        this.key = key;
        this.weight = weight;
        this.location = location;
        this.tag = WHITE;
        this.info = "pos : " + location.x() + "," + location.y() + "," +
                location.z() + ",\nid : " + this.getKey();
        this.list_of_neighbors = new ArrayList<>();
    }

    public Node_Data(int key, GeoLocation location, double weight, String info, int tag)
    {
        this.key = key;
        this.weight = weight;
        this.location = location;
        this.tag = tag;
        this.info = info;
        this.list_of_neighbors = new ArrayList<>();
    }

    public ArrayList<Node_Data> getNeighborsList()
    {
        return this.list_of_neighbors;
    }

    public void updateNeighbors(Node_Data neighbor)
    {
        this.list_of_neighbors.add(neighbor);
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
