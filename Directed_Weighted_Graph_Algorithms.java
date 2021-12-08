package api;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


import java.io.*;

public class Directed_Weighted_Graph_Algorithms implements DirectedWeightedGraphAlgorithms {

      Directed_Weighted_Graph graph;
      Node_Data[] prev = new Node_Data[this.graph.nodeSize()];
//    double[][] dist = new double[this.graph.nodeSize()][this.graph.nodeSize()];
//    NodeData[][] next = new Node_Data[this.graph.nodeSize()][this.graph.nodeSize()];

    public Directed_Weighted_Graph_Algorithms(DirectedWeightedGraph g) {
        init(g);
    }
    public Directed_Weighted_Graph_Algorithms()
    {
        this.graph = new Directed_Weighted_Graph();
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (Directed_Weighted_Graph) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copy_graph = new Directed_Weighted_Graph();
       // Directed_Weighted_Graph g = (Directed_Weighted_Graph) this.graph;
        HashMap<Integer, NodeData> new_node_map = this.graph.getNodeMap();
        HashMap<String, EdgeData> new_edge_map = this.graph.getEdgeMap();
        for (NodeData n : new_node_map.values()) {
            NodeData temp = new Node_Data(n.getKey(), n.getLocation(), n.getWeight(), n.getInfo(), n.getTag());
            copy_graph.addNode(temp);
        }
        for (EdgeData edge : new_edge_map.values()) {
            copy_graph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
        }
        return copy_graph;
    }

    @Override
    public boolean isConnected() {
        return this.graph.edgeSize() == (this.graph.nodeSize() * (this.graph.nodeSize() - 1));
    }

//    private void FWA_with_PR() {
//        for (int i = 0; i < this.dist.length; i++) {
//            for (int j = 0; j < this.dist.length; j++) {
//                if (i == j)
//                    this.dist[i][j] = 0;
//                else {
//                    EdgeData tmp = this.graph.getEdge(i, j);
//                    if (tmp != null)
//                        dist[i][j] = tmp.getWeight();
//                    else
//                        dist[i][j] = Integer.MAX_VALUE;
//                }
//                next[i][j] = this.graph.getNode(i);
//            }
//        }
//        for (int k = 0; k < this.graph.nodeSize(); k++) {
//            for (int i = 0; i < this.graph.nodeSize(); i++) {
//                for (int j = 0; j < this.graph.nodeSize(); j++) {
//                    if (this.dist[i][j] > this.dist[i][k] + this.dist[k][j]) {
//                        this.dist[i][j] = this.dist[i][k] + this.dist[k][j];
//                        next[i][j] = next[i][k];
//                    }
//                }
//            }
//        }
//
//    }

    /**we use Dijkstra algorithm to find the shortest path between
     the center and every other node, and between a current node and every other node.
     the source of the algorithm we use is taken from wikipedia: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
     */
    private double DijkstraAlgo(NodeData src, NodeData dest) {
        HashMap<Integer, Node_Data> setNodes = new HashMap<>();
        Node_Data tmpNode;
        double[] dist = new double[this.graph.nodeSize()];
        for(NodeData n : this.graph.getNodeMap().values())
        {
            dist[n.getKey()] = Integer.MAX_VALUE;
            prev[n.getKey()] = null;
            setNodes.put(n.getKey(), (Node_Data) n);
        }
        dist[src.getKey()] = 0;
        while(!setNodes.isEmpty())
        {
            int minDist = minimumDist(dist);
            tmpNode = setNodes.get(minDist);
            setNodes.remove(tmpNode);
            for(Node_Data neighbor: tmpNode.getNeighborsList())
            {
                String kodkod = tmpNode.getKey() + ", " + neighbor.getKey();
                double alt = dist[tmpNode.getKey()] + this.graph.getEdgeMap().get(kodkod).getWeight();
                if(alt < dist[neighbor.getKey()])
                {
                    dist[neighbor.getKey()] = alt;
                    prev[neighbor.getKey()] = tmpNode;
                }
            }
        }
        return dist[dest.getKey()];
    }
    private int minimumDist(double[] dist)
    {
        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < dist.length; i++)
        {
            if(dist[i] < minDist)
                minDist = i;
        }
        return minDist;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
//        this.FWA_with_PR();
//        return dist[src][dest];
          return this.DijkstraAlgo(this.graph.getNode(src), this.graph.getNode(dest));
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
//        this.FWA_with_PR();
//        int tmp_dest = dest;
//        NodeData tmp_node = this.graph.getNode(src);
//        List<NodeData> path = new ArrayList<>();
//        if (next[src][dest] == null)
//            return null;
//        path.add(tmp_node);
//        while (tmp_node.getKey() != tmp_dest) {
//            tmp_node = next[tmp_node.getKey()][tmp_dest];
//            path.add(tmp_node);
//        }
//        return path;
        List<NodeData> path = new ArrayList<>();
        path.add(this.graph.getNode(src));
        NodeData tmpNode = this.prev[src];
        while(tmpNode.getKey() != dest)
        {
            path.add(tmpNode);
            tmpNode = this.prev[tmpNode.getKey()];
        }
        return path;
    }

    @Override
    public NodeData center() {
//        double[] eccentricity = new double[this.graph.nodeSize()];
//        double rad = Integer.MAX_VALUE;
//        ArrayList<Integer> centers_list = new ArrayList<>();
//        for (int i = 0; i < this.graph.nodeSize(); i++) {
//            for (int j = 0; j < this.graph.nodeSize(); j++) {
//                eccentricity[i] = Math.max(eccentricity[i], dist[i][j]);
//            }
//        }
//        for (int i = 0; i < this.graph.nodeSize(); i++) {
//            rad = Math.min(rad, eccentricity[i]);
//        }
//        for (int i = 0; i < this.graph.nodeSize(); i++) {
//            if (eccentricity[i] == rad) {
//                centers_list.add(i);
//            }
//        }
//        Integer[] centers_array = new Integer[centers_list.size()];
//        centers_array = centers_list.toArray(centers_array);
//        int min = centers_array[0];
//        for (int i = 1; i < centers_array.length; i++) {
//            if (centers_array[i] < min)
//                min = centers_array[i];

//        }
        return this.graph.getCenter();
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List <NodeData> path= new ArrayList<>();
        int counter = 0 ;
        while(counter < cities.size()){
            List <Node_Data> neighbors =((Node_Data)cities.get(i)).getNeighborsList();


        }
        return null;
    }
    public Node_Data min(List<Node_Data> nodes,Node_Data nod){
        for (int i = 0; i<nodes.size(); i++){
            if (nod.getKey() == i)
                continue;
            EdgeData ed = this.graph.getEdge(nod.getKey(),i);
            this.graph.getEdge()
        }
    }

    @Override
    public boolean save(String file) {
        try {
            JSONObject file_g = new JSONObject();
            file_g.put("Edges" ,this.graph.getEdgeMap());
            file_g.put("Nodes" ,this.graph.getNodeMap());
            FileWriter writer = new FileWriter(file);
            writer.write(file_g.toJSONString());
            writer.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

    @Override
    public boolean load(String file) {

        try {
            Directed_Weighted_Graph dg = new Directed_Weighted_Graph();
            JsonParser jsonpars = new JsonParser();
            Object ob = jsonpars.parse(new FileReader(file));
            JSONObject file_g = (JSONObject) ob;
            JSONArray ed = (JSONArray) file_g.get("Edges");
            for (Object o : ed) {
                HashMap<String, Object> edgedata = (HashMap<String, Object>) o;
                int src = (int) (long) edgedata.get("src");
                int dest = (int) (long) edgedata.get("dest");
                double w = (double) edgedata.get("w");
                dg.connect(src, dest, w);
            }
            JSONArray nod = (JSONArray) file_g.get("Nodes");
            for (Object oi : nod) {
                HashMap<String, Object> nodedata = (HashMap<String, Object>) oi;
                String pos = (String) nodedata.get("pos");
                String[] spliti = pos.split(",");
                Geo_Location g = new Geo_Location(Double.parseDouble(spliti[0]), Double.parseDouble(spliti[1]), Double.parseDouble(spliti[2]));
                int id = (int) (long) nodedata.get("id");
                Node_Data nv = new Node_Data(id, g, 0, "", 0);
                dg.addNode(nv);
            }
            this.init(dg);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}

