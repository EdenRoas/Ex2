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
    private double minY, minX, maxX, maxY;

    public PathPanel(Directed_Weighted_Graph graph, ArrayList<NodeData> path)
    {
        super();
        this.repaint();
        this.graph = graph;
        this.path = path;
        this.getMaxX();
        this.getMaxY();
        this.getMinX();
        this.getMinY();
    }

    private void getMinX()
    {
        for (NodeData n: this.path)
        {
            if (n.getLocation().x() < this.minX)
                this.minX = n.getLocation().x();
        }
    }

    private void getMinY()
    {
        for (NodeData n: this.path)
        {
            if (n.getLocation().y() < this.minY)
                this.minY = n.getLocation().y();
        }
    }

    private void getMaxX()
    {
        for (NodeData n: this.path)
        {
            if (n.getLocation().x() > this.maxX)
                this.maxX = n.getLocation().x();
        }
    }

    private void getMaxY()
    {
        for (NodeData n: this.path)
        {
            if (n.getLocation().y() > this.maxY)
                this.maxY = n.getLocation().y();
        }
    }

    private int scaleX(double x)
    {
        return (int) ((((x - this.minX) * (GUI_runner.width - 100)) / (this.maxX - this.minX)) + 15);
    }

    private int scaleY(double y)
    {
        return (int) ((((y - this.minY) * (GUI_runner.height - 150)) / (this.maxY - this.minY)) + 25);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        for(NodeData n: path)
        {
            int x = this.scaleX(n.getLocation().x()), y = this.scaleY(n.getLocation().y());
            g.setColor(Color.BLUE);
            g.fillOval(x-5, y-5, 10, 10);
            g.drawString(String.valueOf(n.getKey()), x + 4, y + 5);
            this.repaint();
        }
        for(int i = 0; i < this.path.size() - 1; i++)
        {
            int srcX = this.scaleX(this.path.get(i).getLocation().x()),
                    srcY = this.scaleY(this.path.get(i).getLocation().y()),
                    destX = this.scaleX(this.path.get(i + 1).getLocation().x()),
                    destY = this.scaleY(this.path.get(i + 1).getLocation().y());
            g.setColor(Color.BLACK);
            g.drawLine(srcX, srcY, destX, destY);
            g.setFont(g.getFont().deriveFont(Font.ITALIC, 10));
            g.drawString(String.format("%.2g%n", this.graph.getEdge(this.path.get(i).getKey(), this.path.get(i + 1).getKey()).getWeight()),
                    (int) (srcX * 0.5 + destX * 0.5) + 4,
                    (int) (srcY * 0.5 + destY * 0.5) + 4);
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
