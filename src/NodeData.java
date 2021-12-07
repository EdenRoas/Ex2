import api.GeoLocation;

public class NodeData implements api.NodeData{
    private int key, tag;
    private GeoLocation location;
    private double weight;
    private String info;
    public static final int WHITE = 0, GRAY = 1, BLACK = 2;

    /**
     * for the Node tag: white = 0,   gray = 1,   black = 2,
     * So that white symbolizes the node hasn't been visited,
     * gray symbolizes we already visit this node
     * and black symbolizes we've done with this node, as a default all nodes are Initialized to white.
     */
    public NodeData(int key, GeoLocation location, double weight, String info, int tag)
    {
        this.key = key;
        this.weight = weight;
        this.location = location;
        this.tag = tag;
        this.info = info;
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
        this.tag = tag;
    }
}
