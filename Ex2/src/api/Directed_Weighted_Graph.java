package api;

import java.util.*;

public class Directed_Weighted_Graph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> NodeMap;
    private int MC = 0;
    private HashMap<String, EdgeData> EdgeMap;

    public Directed_Weighted_Graph() {
        this.EdgeMap = new LinkedHashMap<>();
        this.NodeMap = new LinkedHashMap<>();
    }

    public HashMap<Integer, NodeData> getNodeMap() {
        return this.NodeMap;
    }

    public HashMap<String, EdgeData> getEdgeMap() {
        return this.EdgeMap;
    }

    @Override
    public NodeData getNode(int key) {
        if (NodeMap.containsKey(key)) {
            return NodeMap.get(key);
        }
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        String kodkod = src + ", " + dest;
        if (EdgeMap.containsKey(kodkod)) {
            return EdgeMap.get(kodkod);
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        if (n != null) {
            NodeMap.put(n.getKey(), n);
            this.MC++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (src == dest || !this.NodeMap.containsKey(src) || !this.NodeMap.containsKey(dest) || getEdge(src, dest) != null) {
            System.out.println("can't connect vertices!");
            return;
        }

        EdgeData edge = new Edge_Data(src, dest, w);
        String kodkod = src + ", " + dest;
        EdgeMap.put(kodkod, edge);
        this.MC++;
        Node_Data node = (Node_Data) this.NodeMap.get(src);
        node.updateNeighbors((Node_Data) this.NodeMap.get(dest));
    }


    @Override
    public Iterator<NodeData> nodeIter() {
        int m = this.MC;
        Iterator<NodeData> iter_node = NodeMap.values().iterator();
        if (m != MC) {
            System.out.println("the Graph has been changed!");
            throw new RuntimeException();
        }
        return iter_node;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        int m = this.MC;
        Iterator<EdgeData> iter_edges = this.EdgeMap.values().iterator();
        Iterator<EdgeData> edgeiter = new Iterator<EdgeData>() {
            EdgeData edge;

            @Override
            public boolean hasNext() {
                return iter_edges.hasNext();
            }

            @Override
            public EdgeData next() {
                if (m != MC) {
                    System.out.println("the Graph has been changed!");
                    throw new RuntimeException();
                } else {
                    edge = iter_edges.next();
                    return edge;
                }
            }
        };
        return edgeiter;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        HashMap<String, EdgeData> node_Edges = new HashMap<>();
        for (EdgeData edge : this.EdgeMap.values()) {
            if (edge.getSrc() == node_id || edge.getDest() == node_id) {
                String key = edge.getSrc() + ", " + edge.getDest();
                node_Edges.put(key, edge);
            }
        }
        int m = this.MC;
        Iterator<EdgeData> iter_edge = node_Edges.values().iterator();
        if (m != MC) {
            System.out.println("the Graph has been changed!");
            throw new RuntimeException();
        }
        return iter_edge;
    }

    @Override
    public NodeData removeNode(int key) {
        if (!this.NodeMap.containsKey(key))
            return null;
        //if(! this.NodeMap.values().isEmpty()){
        for (NodeData n : this.NodeMap.values()) {
            if (n.getKey() == key)
                continue;
            String kod1 = key + ", " + n.getKey();
            String kod2 = n.getKey() + ", " + key;
            if (this.EdgeMap.containsKey(kod1))
                EdgeMap.remove(kod1);
            if (this.EdgeMap.containsKey(kod2))
                EdgeMap.remove(kod2);
        }
        this.MC++;
        return NodeMap.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        String kodkod = src + ", " + dest;
        if (!EdgeMap.containsKey(kodkod))
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

    @Override
    public String toString() {
        String str = "Directed_Weighted_Graph-> " +
                "NodeMap=";
        for (NodeData n: this.NodeMap.values()){
            str = str + "id: " + n.getKey() + "\n";
        }
        str = str + ", MC=" + MC + ", EdgeMap=";
        for (EdgeData e: this.EdgeMap.values()){
            str = str + e.getInfo() + "\n";
        }
        return str;
    }
}

