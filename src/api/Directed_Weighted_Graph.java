package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Directed_Weighted_Graph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> NodeMap;
    private int MC = 0;
    private HashMap<String, EdgeData> EdgeMap;
   // NodeData center;

    public Directed_Weighted_Graph() {
        this.EdgeMap = new HashMap<>();
        this.NodeMap = new HashMap<>();
//        this.center = null;
    }

    public HashMap<Integer, NodeData> getNodeMap()
    {
        return this.NodeMap;
    }

    public HashMap<String, EdgeData> getEdgeMap()
    {
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
//        if(this.center == null)
//            this.center = n;
//        else
//        {
//            if(this.nodeSize() > 1)
//            {
//                double maxDistCen = 0, maxDistN = 0;
//                NodeData tmpNode;
//                //for(NodeData node : this.NodeMap.values())
//                for(int i = 0; i < this.nodeSize(); i++)
//                {
//                    tmpNode = this.NodeMap.get(i);
//                    if(tmpNode == center || tmpNode == n)
//                        continue;
//                   double tmpPathCen = shortestPath(center, tmpNode);
//                    double tmpPathN = shortestPath(n, tmpNode);
//                    if(maxDistCen < tmpPathCen)
//                        maxDistCen = tmpPathCen;
//                    if(maxDistN < tmpPathN)
//                        maxDistCen = tmpPathN;
//                }
//                if(maxDistN < maxDistCen)
//                    this.center = n;
//            }
//        }
    }

//    /**we use Dijkstra algorithm to find the shortest path between
//     the center and every other node, and between a current node and every other node.
//     the source of the algorithm we use is taken from wikipedia: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
//     */
//    private double shortestPath(NodeData src, NodeData dest) {
//        HashMap<Integer, NodeData> setNodes = new HashMap<>();
//        NodeData tmpNode;
//        double[] dist = new double[this.nodeSize()];
//        //Node_Data[] prev = new Node_Data[this.nodeSize()];
//        for(NodeData n : this.NodeMap.values())
//        {
//            dist[n.getKey()] = Integer.MAX_VALUE;
//            //prev[n.getKey()] = null;
//            setNodes.put(n.getKey(), n);
//        }
//        dist[src.getKey()] = 0;
//        while(!setNodes.isEmpty())
//        {
//            int minDist = minimumDist(dist);
//            tmpNode = setNodes.get(minDist);
//            setNodes.remove(tmpNode);
//            //for(Node_Data neighbor: tmpNode.getNeighborsList())
//            int index = 0;
//            NodeData neighbor;
//            while (index < ((Node_Data)tmpNode).getNeighborsList().size())
//            {
//                neighbor = ((Node_Data)tmpNode).getNeighborsList().get(index);
//                String kodkod = tmpNode.getKey() + ", " + neighbor.getKey();
//                double alt = dist[tmpNode.getKey()] + this.EdgeMap.get(kodkod).getWeight();
//                if(alt < dist[neighbor.getKey()])
//                {
//                    dist[neighbor.getKey()] = alt;
//                    //prev[neighbor.getKey()] = tmpNode;
//                }
//                index++;
//            }
//        }
//        return dist[dest.getKey()];
//    }
//
//    private int minimumDist(double[] dist)
//    {
//        double minDist = Double.MAX_VALUE;
//        int minIndex = 0;
//        for(int i = 0; i < dist.length; i++)
//        {
//            if(dist[i] < minDist) {
//                minDist = dist[i];
//                minIndex = i;
//            }
//        }
//        return minIndex;
//    }
//
//    public NodeData getCenter()
//    {
//        return this.center;
//    }

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
        Iterator<NodeData> iter = new Iterator<NodeData>() {
            private Iterator<NodeData> iter_node = NodeMap.values().iterator();
            private final int m = MC;
            @Override
            public boolean hasNext() {
                if(m != MC) {
                    System.out.println("the Graph has been changed!");
                    throw new RuntimeException();
                }
                return iter_node.hasNext();
            }

            @Override
            public NodeData next() {
                return iter_node.next();
            }
        };
        return iter;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        int m = this.MC;
        List<EdgeData> edgesList = new ArrayList<>();
        Iterator<NodeData> iter_node = NodeMap.values().iterator();
        while(iter_node.hasNext())
        {
            Iterator<EdgeData> iter_edges = this.edgeIter(((NodeData)iter_node).getKey());
            while(iter_edges.hasNext())
            {
                edgesList.add((EdgeData) iter_edges);
            }
        }
        Iterator<EdgeData> final_edge_iter = edgesList.iterator();
        if(m != MC) {
            System.out.println("the Graph has been changed!");
            throw new RuntimeException();
        }
        return final_edge_iter;
    }
/*
int MC_start = this.MC;
        LinkedList<EdgeData> edge_list = new LinkedList<>();
        Iterator<NodeData> iter =  this.G.values().iterator();
        while (iter.hasNext()){
            Iterator<EdgeData> iter2 =  ((Node_data) iter.next()).edges.values().iterator();
            while(iter2.hasNext()){
                Edge_data e = (Edge_data) iter2.next();
                edge_list.add(e);
            }
        }
        Iterator<EdgeData> e_iter = edge_list.iterator();
        if(MC_start!=this.MC){
            throw new RuntimeException("changes have been done");
        }
        return e_iter;
    }
 */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        HashMap<String, EdgeData> node_Edges = new HashMap<>();
        for(EdgeData edge : this.EdgeMap.values())
        {
            if(edge.getSrc() == node_id || edge.getDest() == node_id) {
                String key = edge.getSrc() + ", " + edge.getDest();
                node_Edges.put(key, edge);
            }
        }
        Iterator<EdgeData> iter = new Iterator<EdgeData>() {
            private Iterator<EdgeData> iter_edge = node_Edges.values().iterator();
            private final int m = MC;
            @Override
            public boolean hasNext() {
                if(m != MC) {
                    System.out.println("the Graph has been changed!");
                    throw new RuntimeException();

                }
                return iter_edge.hasNext();
            }

            @Override
            public EdgeData next() {
                return iter_edge.next();
            }
        };
        return iter;
    }

    @Override
    public NodeData removeNode(int key){
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
    public EdgeData removeEdge(int src, int dest){
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
}

