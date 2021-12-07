import java.util.HashMap;
import java.util.List;

public class DirectedWeightedGraphAlgorithms implements api.DirectedWeightedGraphAlgorithms{
    private DirectedWeightedGraph graph;

    public DirectedWeightedGraphAlgorithms(DirectedWeightedGraph g)
    {
        init(g);
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copy_graph = new DirectedWeightedGraph();
        HashMap<Integer, NodeData> new_map = this.graph.getNodeMap();

        int index = 0;
        for (NodeData n : new_map.values())
        {
            NodeData temp = new NodeData(n.getKey(), n.getLocation(), n.getWeight(), n.getInfo(), n.getTag());
            copy_graph.addNode(temp);
        }
        for (NodeData n : new_map.values())
        {
            HashMap<String, EdgeData> new_edge_map = this.graph.getEdgeMap();
            for(EdgeData edge : new_edge_map.values())
            {
                copy_graph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
            }
        }
        return copy_graph;
    }

    @Override
    public boolean isConnected() {
        return this.graph.edgeSize() == (this.graph.nodeSize()*(this.graph.nodeSize() - 1));
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
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
