package GUI;

import api.Directed_Weighted_Graph;
import api.EdgeData;
import api.NodeData;
import api.Node_Data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphPanel extends JPanel {
    //private Graphics mBuffer_graphics;

    private HashMap<Integer, NodeData> nodes_List;
    private HashMap<String, EdgeData> edge_List;
    private double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE, minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;

    public GraphPanel(Directed_Weighted_Graph graph)
    {
        super();
        this.edge_List = GUI_runner.graph.getEdgeMap();
        this.nodes_List = GUI_runner.graph.getNodeMap();
        this.getMaxX();
        this.getMinX();
        this.getMaxY();
        this.getMinY();
    }

    private void getMinX()
    {
        for (NodeData n: this.nodes_List.values())
        {
            if (n.getLocation().x() < this.minX)
                this.minX = n.getLocation().x();
        }
    }

    private void getMinY()
    {
        for (NodeData n: this.nodes_List.values())
        {
            if (n.getLocation().y() < this.minY)
                this.minY = n.getLocation().y();
        }
    }

    private void getMaxX()
    {
        for (NodeData n: this.nodes_List.values())
        {
            if (n.getLocation().x() > this.maxX)
                this.maxX = n.getLocation().x();
        }
    }

    private void getMaxY()
    {
        for (NodeData n: this.nodes_List.values())
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
    public void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
        for(NodeData n: nodes_List.values())
        {
            int x = this.scaleX(n.getLocation().x()), y = this.scaleY( n.getLocation().y());
            gr.setColor(Color.BLUE);
            gr.fillOval(x-5, y-5, 10, 10);
            gr.setColor(Color.BLACK);
            gr.drawString(String.valueOf(n.getKey()), x+4, y+5);
            this.repaint();
        }

        for(EdgeData e: edge_List.values())
        {
            int srcX = this.scaleX(nodes_List.get(e.getSrc()).getLocation().x()) ,
                    srcY = this.scaleY(nodes_List.get(e.getSrc()).getLocation().y()),
            destX = this.scaleX(nodes_List.get(e.getDest()).getLocation().x()),
                    destY = this.scaleY(nodes_List.get(e.getDest()).getLocation().y());
            gr.setColor(Color.BLACK);
            gr.drawLine(srcX, srcY, destX, destY);
            gr.setFont(gr.getFont().deriveFont(Font.ITALIC, 10));
            gr.drawString(String.format("%.2g%n", e.getWeight()) , (int) (srcX * 0.5 + destX * 0.5) + 4,
                    (int) (srcY * 0.5 + destY * 0.5) + 4);
            this.repaint();
        }
    }
}
