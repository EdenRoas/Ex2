package GUI;

import api.Directed_Weighted_Graph;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {
    private List<NodeData> Nodes_List;
    private List<EdgeData> Edge_List;

    public GraphPanel(Directed_Weighted_Graph graph)
    {
        super();
        this.Edge_List = (ArrayList<EdgeData>) graph.edgeIter();
        this.Nodes_List = (ArrayList<NodeData>) graph.nodeIter();
    }

    @Override
    public void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);

        for(NodeData n: Nodes_List)
        {
            int x = (int) n.getLocation().x(), y = (int) n.getLocation().y();
            gr.setColor(Color.BLUE);
            gr.fillOval(x, y, 10, 10);
        }
    }
}
