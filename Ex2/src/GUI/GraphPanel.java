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

    HashMap<Integer, NodeData> nodes_List;
    HashMap<String, EdgeData> edge_List;

    public GraphPanel(Directed_Weighted_Graph graph)
    {
        super();
        this.edge_List = GUI_runner.graph.getEdgeMap();
        this.nodes_List = GUI_runner.graph.getNodeMap();
        //mBuffer_graphics = new
    }

//    public void paint(Graphics graphics)
//    {
//        this.Nodes_List = (ArrayList<NodeData>) GUI_runner.graph.nodeIter();
//        this.Edge_List = (ArrayList<EdgeData>) GUI_runner.graph.edgeIter();
//        paintComponent(graphics);
//    }



    @Override
    public void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
//        HashMap<Integer, NodeData> nodes_List = GUI_runner.graph.getNodeMap();
//        HashMap<String, EdgeData> edge_List = GUI_runner.graph.getEdgeMap();
        //this.setBackground(new java.awt.Color(255, 255, 25));
//        gr.setColor(Color.BLUE);
//        gr.fillOval(200, 200, 10, 10);

        //while ()
        for(NodeData n: nodes_List.values())
        {
            int x = (int) n.getLocation().x(), y = (int) n.getLocation().y();
            gr.setColor(Color.BLUE);
            gr.fillOval(x-5, y-5, 10, 10);
            gr.drawString(String.valueOf(n.getKey()), x, y);
            this.repaint();
        }
        for(EdgeData e: edge_List.values())
        {
            int srcX = (int) nodes_List.get(e.getSrc()).getLocation().x(),
                    srcY = (int) nodes_List.get(e.getSrc()).getLocation().y(),
            destX = (int) nodes_List.get(e.getDest()).getLocation().x(),
                    destY = (int) nodes_List.get(e.getDest()).getLocation().y();
            gr.setColor(Color.BLACK);
            gr.drawLine(srcX, srcY, destX, destY);
            gr.drawString(String.valueOf(e.getWeight()), (int) (srcX * 0.5 + destX * 0.5),
                    (int) (srcY * 0.5 + destY * 0.5));
            this.repaint();
        }
    }
}
