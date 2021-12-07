package api;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import com.google.gson.Gson;

public class Directed_Weighted_Graph_Algorithms implements DirectedWeightedGraphAlgorithms{

    DirectedWeightedGraph graph;
    double[][] dist = new double[this.graph.nodeSize()][this.graph.nodeSize()];
    NodeData[][] next = new Node_Data[this.graph.nodeSize()][this.graph.nodeSize()];

    public Directed_Weighted_Graph_Algorithms(DirectedWeightedGraph g)
    {
        init(g);
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copy_graph = new Directed_Weighted_Graph();
        Directed_Weighted_Graph g = (Directed_Weighted_Graph)this.graph;
        HashMap<Integer, NodeData> new_node_map = g.getNodeMap();
        HashMap<String, EdgeData> new_edge_map = g.getEdgeMap();
        for (NodeData n : new_node_map.values())
        {
            NodeData temp = new Node_Data(n.getKey(), n.getLocation(), n.getWeight(), n.getInfo(), n.getTag());
            copy_graph.addNode(temp);
        }
        for(EdgeData edge : new_edge_map.values())
        {
            copy_graph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
        }
        return copy_graph;
    }

    @Override
    public boolean isConnected() {
        return this.graph.edgeSize() == (this.graph.nodeSize()*(this.graph.nodeSize() - 1));    }

    private void FWA_with_PR()
    {
        for(int i=0; i<this.dist.length; i++)
        {
            for(int j=0; j<this.dist.length; j++)
            {
                if(i == j)
                    this.dist[i][j] = 0;
                else {
                    EdgeData tmp = this.graph.getEdge(i, j);
                    if(tmp != null)
                        dist[i][j] = tmp.getWeight();
                    else
                        dist[i][j] = Integer.MAX_VALUE;
                }
                next[i][j] = this.graph.getNode(i);
            }
        }
        for(int k=0; k<this.graph.nodeSize(); k++)
        {
            for(int i=0; i<this.graph.nodeSize(); i++)
            {
                for(int j=0; j<this.graph.nodeSize(); j++)
                {
                    if(this.dist[i][j] > this.dist[i][k] + this.dist[k][j]) {
                        this.dist[i][j] = this.dist[i][k] + this.dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

    }

    @Override
    public double shortestPathDist(int src, int dest) {
        this.FWA_with_PR();
        return dist[src][dest];
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        this.FWA_with_PR();
        int tmp_dest = dest;
        NodeData tmp_node = this.graph.getNode(src);
        List<NodeData> path = new ArrayList<>();
        if(next[src][dest] == null)
            return null;
        path.add(tmp_node);
        while(tmp_node.getKey() != tmp_dest)
        {
            tmp_node = next[tmp_node.getKey()][tmp_dest];
            path.add(tmp_node);
        }
        return path;
    }

    @Override
    public NodeData center() {
        double[] eccentricity = new double[this.graph.nodeSize()];
        double rad = Integer.MAX_VALUE;
        ArrayList<Integer> centers_list = new ArrayList<>();
        for (int i = 0; i < this.graph.nodeSize(); i++) {
            for (int j = 0; j < this.graph.nodeSize(); j++) {
                eccentricity[i] = Math.max(eccentricity[i], dist[i][j]);
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
        for(int i = 1; i < centers_array.length; i++){
            if(centers_array[i] < min)
                min = centers_array[i];
        }
        return this.graph.getNode(min);
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
