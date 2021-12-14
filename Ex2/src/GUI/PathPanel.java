package GUI;

import api.DirectedWeightedGraph;
import api.Directed_Weighted_Graph;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PathPanel extends JPanel {

    DirectedWeightedGraph graph;
    List<NodeData> path;

    public PathPanel(Directed_Weighted_Graph graph, ArrayList<NodeData> path)
    {
        super();
        this.graph = graph;
        this.path = path;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        for(NodeData n: path)
        {
            int x = (int) n.getLocation().x(), y = (int) n.getLocation().y();
            g.setColor(Color.BLUE);
            g.fillOval(x-5, y-5, 10, 10);
            g.drawString(String.valueOf(n.getKey()), x, y);
            this.repaint();
        }
        for(int i = 0; i < this.path.size() - 1; i++)
        {
            int srcX = (int) this.path.get(i).getLocation().x(),
                    srcY = (int) this.path.get(i).getLocation().y(),
                    destX = (int) this.path.get(i + 1).getLocation().x(),
                    destY = (int) this.path.get(i + 1).getLocation().y();
            g.setColor(Color.BLACK);
            g.drawLine(srcX, srcY, destX, destY);
            g.drawString(String.valueOf(this.graph.getEdge(this.path.get(i).getKey(), this.path.get(i + 1).getKey()).getWeight()),
                    (int) (srcX * 0.5 + destX * 0.5),
                    (int) (srcY * 0.5 + destY * 0.5));
            this.repaint();
        }
//        for(EdgeData e: edge_List.values())
//        {
//            int srcX = (int) nodes_List.get(e.getSrc()).getLocation().x(),
//                    srcY = (int) nodes_List.get(e.getSrc()).getLocation().y(),
//                    destX = (int) nodes_List.get(e.getDest()).getLocation().x(),
//                    destY = (int) nodes_List.get(e.getDest()).getLocation().y();
//            gr.setColor(Color.BLACK);
//            gr.drawLine(srcX, srcY, destX, destY);
//            gr.drawString(String.valueOf(e.getWeight()), (int) (srcX * 0.5 + destX * 0.5),
//                    (int) (srcY * 0.5 + destY * 0.5));
//            this.repaint();
//        }
    }
}
