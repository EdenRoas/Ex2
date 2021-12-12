package api;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;

public class Directed_Weighted_Graph_Algorithms implements DirectedWeightedGraphAlgorithms {

    Directed_Weighted_Graph graph;
    double[][] distAllNodes;
    Node_Data[] prev;
    double[] dist;

    public Directed_Weighted_Graph_Algorithms() {
        this.graph = new Directed_Weighted_Graph();
    }

    public Directed_Weighted_Graph_Algorithms(DirectedWeightedGraph g) {
        init(g);
    }

    @Override
    public void init(DirectedWeightedGraph g) {
//        Directed_Weighted_Graph copy_graph = new Directed_Weighted_Graph();
//        //Directed_Weighted_Graph g = this.graph;
//        HashMap<Integer, NodeData> new_node_map = this.graph.getNodeMap();
//        HashMap<String, EdgeData> new_edge_map = this.graph.getEdgeMap();
//        for (NodeData n : new_node_map.values()) {
//            NodeData temp = new Node_Data(n.getKey(), n.getLocation(), n.getWeight(), n.getInfo(), n.getTag());
//            this.graph.addNode(temp);
//        }
//        for (EdgeData edge : new_edge_map.values()) {
//            this.graph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
//        }
        this.graph = (Directed_Weighted_Graph) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copy_graph = new Directed_Weighted_Graph();
        //Directed_Weighted_Graph g = this.graph;
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

    /**
     * we use Dijkstra algorithm to find the shortest path between
     * the center and every other node, and between a current node and every other node.
     * the source of the algorithm we use is taken from wikipedia: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
     */
    private void DijkstraAlgo(double[][] weights, int src) {
        this.prev = new Node_Data[this.graph.nodeSize()];
        HashMap<Integer, Node_Data> setNodes = new HashMap<>();
        Boolean sptSet[] = new Boolean[this.graph.nodeSize()];
        dist = new double[this.graph.nodeSize()];
        for(int i = 0; i < this.graph.nodeSize(); i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
            prev[i] = null;
        }
        dist[src] = 0;
        for (int count = 0; count < this.graph.nodeSize() - 1; count++) {
            int u = (int)(minimumDist(dist, sptSet));
            sptSet[u] = true;
            for (int v = 0; v < this.graph.nodeSize(); v++)
            {
                if (!sptSet[v] && weights[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE && dist[u] + weights[u][v] < dist[v]) {
                    dist[v] = dist[u] + weights[u][v];
                    prev[v] = (Node_Data) this.graph.getNode(u);
                }
            }
        }
//        for (NodeData n : this.graph.getNodeMap().values()) {
//            dist[n.getKey()] = Integer.MAX_VALUE;
//            prev[n.getKey()] = null;
//            setNodes.put(n.getKey(), (Node_Data) n);
//        }
//        dist[src.getKey()] = 0;
//        while (!setNodes.isEmpty()) {
//            int minDist = minimumDist(dist);
//            tmpNode = setNodes.get(minDist);
//            setNodes.remove(tmpNode);
//            for (Node_Data neighbor : tmpNode.getNeighborsList()) {
//                String kodkod = tmpNode.getKey() + ", " + neighbor.getKey();
//                double alt = dist[tmpNode.getKey()] + this.graph.getEdgeMap().get(kodkod).getWeight();
//                if (alt < dist[neighbor.getKey()]) {
//                    dist[neighbor.getKey()] = alt;
//                    prev[neighbor.getKey()] = tmpNode;
//                }
//            }
//        }
//        distAllNodes[src.getKey()][dest.getKey()] = dist[dest.getKey()];
    }

    private double minimumDist(double dist[], Boolean sptSet[])
    {
        // Initialize min value
        double min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < this.graph.nodeSize(); v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

//    private int minimumDist(double[] dist) {
//        int minDist = Integer.MAX_VALUE;
//        for (int i = 0; i < dist.length; i++) {
//            if (dist[i] < minDist)
//                minDist = i;
//        }
//        return minDist;
//    }

    @Override
    public double shortestPathDist(int src, int dest) {
        double[][] weights = new double[this.graph.nodeSize()][this.graph.nodeSize()];
        for (int i=0; i<weights.length; i++)
        {
            for (int j=0; j<weights.length; j++)
            {
                EdgeData tmpEdge = this.graph.getEdge(i, j);
                if (tmpEdge == null)
                {
                    weights[i][j] = 0;
                }
                else
                {
                    weights[i][j] = tmpEdge.getWeight();
                }
            }
        }
        DijkstraAlgo(weights, src);
        if(dist[dest] == Integer.MAX_VALUE || dist[dest] == 0)
            return -1;
        return this.dist[dest];
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if(src == dest || this.shortestPathDist(src, dest) == -1)
            return null;
        double[][] weights = new double[this.graph.nodeSize()][this.graph.nodeSize()];
        for (int i=0; i<weights.length; i++)
        {
            for (int j=0; j<weights.length; j++)
            {
                EdgeData tmpEdge = this.graph.getEdge(i, j);
                if (tmpEdge == null)
                {
                    weights[i][j] = 0;
                }
                else
                {
                    weights[i][j] = tmpEdge.getWeight();
                }
            }
        }
        DijkstraAlgo(weights, src);
        List<NodeData> path = new ArrayList<>();
        path.add(this.graph.getNode(dest));
        NodeData tmpNode = this.prev[dest];
        while (tmpNode.getKey() != src) {
            path.add(tmpNode);
            tmpNode = this.prev[tmpNode.getKey()];
        }
        path.add(this.graph.getNode(src));
        Collections.reverse(path);
        return path;
    }

    private void FWA() {
        this.distAllNodes = new double[this.graph.nodeSize()][this.graph.nodeSize()];
        for (int i = 0; i < this.distAllNodes.length; i++) {
            for (int j = 0; j < this.distAllNodes.length; j++) {
                if (i == j)
                    this.distAllNodes[i][j] = 0;
                else {
                    EdgeData tmp = this.graph.getEdge(i, j);
                    if (tmp != null)
                        distAllNodes[i][j] = tmp.getWeight();
                    else
                        distAllNodes[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int k = 0; k < this.graph.nodeSize(); k++) {
            for (int i = 0; i < this.graph.nodeSize(); i++) {
                for (int j = 0; j < this.graph.nodeSize(); j++) {
                    if (this.distAllNodes[i][j] > this.distAllNodes[i][k] + this.distAllNodes[k][j]) {
                        this.distAllNodes[i][j] = this.distAllNodes[i][k] + this.distAllNodes[k][j];
                    }
                }
            }
        }
    }

    /**
     * for this function we used Floyd–Warshall algorithm, the source of the algorithm is from
     * wikipedia : https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm
     *
     * @return
     */
    @Override
    public NodeData center() {
        FWA();
        double[] eccentricity = new double[this.graph.nodeSize()];
        double rad = Integer.MAX_VALUE;
        ArrayList<Integer> centers_list = new ArrayList<>();
        for (int i = 0; i < this.graph.nodeSize(); i++) {
            for (int j = 0; j < this.graph.nodeSize(); j++) {
                eccentricity[i] = Math.max(eccentricity[i], distAllNodes[i][j]);
            }
        }
        for (int i = 0; i < this.graph.nodeSize(); i++) {
            rad = Math.min(rad, eccentricity[i]);
        }
        for (int i = 0; i < this.graph.nodeSize(); i++) {
            if (eccentricity[i] == rad) {
                centers_list.add(i);
            }
        }
        Integer[] centers_array = new Integer[centers_list.size()];
        centers_array = centers_list.toArray(centers_array);
        int min = centers_array[0];
        for (int i = 1; i < centers_array.length; i++) {
            if (centers_array[i] < min)
                min = centers_array[i];
        }
        return this.graph.getNode(min);
//        return this.graph.getCenter();
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        List<NodeData> tmpCities = new ArrayList<>();
        List<NodeData> citiesPath = new ArrayList<>();
        for (int i = 0; i < cities.size(); i++) {
            tmpCities.add(cities.get(i));
        }
        int index = 0;
        NodeData currNode;
        while (!tmpCities.isEmpty()) {
            currNode = tmpCities.get(index);
            citiesPath.add(currNode);
            index = minWeightIndex(tmpCities, currNode);
            tmpCities.remove(currNode);
        }
        return citiesPath;
    }

    private int minWeightIndex(List<NodeData> tmpCities, NodeData currNode) {
        double minWeight = Double.MAX_VALUE;
        EdgeData tmpEdge;
        int minIndex = 0, index = 0;
        for (NodeData n : tmpCities) {
            if (n.equals(currNode)) {
                index++;
                continue;
            }
            tmpEdge = this.graph.getEdge(currNode.getKey(), n.getKey());
            if (tmpEdge == null) {
                index++;
                continue;
            }
            if (tmpEdge.getWeight() < minWeight) {
                minWeight = tmpEdge.getWeight();
                minIndex = index++;
            }
        }
        return minIndex;
    }

    @Override
    public boolean save(String file) {
        JSONArray edges = new JSONArray();
        JSONObject edgeO = new JSONObject();
        for(EdgeData edge : this.graph.getEdgeMap().values())
        {
            edgeO.put("src", edge.getSrc());
            edgeO.put("w", edge.getWeight());
            edgeO.put("dest", edge.getDest());
            edges.add(edgeO);
        }
        JSONArray nodes = new JSONArray();
        JSONObject nodeO = new JSONObject();
        for(NodeData node : this.graph.getNodeMap().values())
        {
            String str = node.getLocation().x() + "," + node.getLocation().y() + "," + node.getLocation().z();
            nodeO.put("pos", str);
            nodeO.put("id", node.getKey());
            nodes.add(nodeO);
        }
        JSONObject graphObj = new JSONObject();
        graphObj.put("Edges", edges);
        graphObj.put("Nodes", nodes);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(graphObj.toJSONString());
            writer.flush();
            return true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
//        try {
//            JSONObject file_g = new JSONObject();
//            file_g.put("Edges", this.graph.getEdgeMap());
//            file_g.put("Nodes", this.graph.getNodeMap());
//            FileWriter writer = new FileWriter(file);
//            writer.write(file_g.toJSONString());
//            writer.flush();
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
    }

    @Override
    public boolean load(String file) {
        try {
            Directed_Weighted_Graph dg = new Directed_Weighted_Graph();
            JSONParser jsonparser = new JSONParser();
            Object ob = jsonparser.parse(new FileReader(file));
            JSONObject file_g = (JSONObject) ob;
            JSONArray nod = (JSONArray) file_g.get("Nodes");
            for (Object oi : nod) {
                HashMap<String, Object> nodedata = (HashMap<String, Object>) oi;
                String pos = (String) nodedata.get("pos");
                String[] spliti = pos.split(",");
                GeoLocation g = new Geo_Location(Double.parseDouble(spliti[0]), Double.parseDouble(spliti[1]), Double.parseDouble(spliti[2]));
                int id = (int) (long) nodedata.get("id");
                NodeData nv = new Node_Data(id, g, 0, "", 0);
                dg.addNode(nv);
            }
            JSONArray ed = (JSONArray) file_g.get("Edges");
            for (Object o : ed) {
                HashMap<String, Object> edgedata = (HashMap<String, Object>) o;
                int src = (int) (long) edgedata.get("src");
                double w = (double) edgedata.get("w");
                int dest = (int) (long) edgedata.get("dest");
                dg.connect(src, dest, w);
            }
            this.init(dg);
            return true;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
