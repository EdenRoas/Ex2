import java.util.HashMap;
import java.util.Iterator;

public class DirectedWeightedGraph implements api.DirectedWeightedGraph {
    private HashMap<Integer,NodeData> NodeMap;
    private int MC = 0;
    private HashMap<String, EdgeData> EdgeMap;


    @Override
    public NodeData getNode(int key) {
        if(NodeMap.containsKey(key))
        {
            return NodeMap.get(key);
        }
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        String kodkod = src +", "+dest;
        if(EdgeMap.containsKey(kodkod))
        {
            return EdgeMap.get(kodkod);
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        if(n!=null)
        {
            NodeMap.put(n.getKey(),n);
            this.MC++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        if( src == dest || !this.NodeMap.containsKey(src) || !this.NodeMap.containsKey(dest) || getEdge(src, dest) != null )
            return;
        EdgeData edge = new EdgeData(src, dest, w);
        String kodkod = src +", "+dest;
        EdgeMap.put(kodkod,edge);
        this.MC++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
//        try {
//            return this.NodeMap.values().iterator();
//        }
//        catch (RuntimeException)
//        {
//            throw RuntimeException;
//        }

//    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return this.EdgeMap.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.EdgeMap.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        if(!this.NodeMap.containsKey(key))
            return null;
        //if(! this.NodeMap.values().isEmpty()){
        for (NodeData n : this.NodeMap.values()) {
            if(n.getKey() == key)
                continue;
            String kod1 = key +", "+n.getKey();;
            String kod2 = n.getKey() +", "+key;
            if(this.EdgeMap.containsKey(kod1))
                EdgeMap.remove(kod1);
            if(this.EdgeMap.containsKey(kod2))
                EdgeMap.remove(kod2);
        }
        this.MC++;
        return NodeMap.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        String kodkod = src +", "+dest;

        if(!EdgeMap.containsKey(kodkod))
            return null;

        this.MC++;

        return this.EdgeMap.remove(kodkod);
    }

    @Override
    public int nodeSize() {
        return NodeMap.size();
    }

    @Override
    public int edgeSize() {
        return EdgeMap.size();
    }

    @Override
    public int getMC() {
        return this.MC;
    }
}
