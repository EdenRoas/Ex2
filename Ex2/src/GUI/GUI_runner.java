package GUI;

import api.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import static java.lang.Integer.parseInt;

public class GUI_runner{

    public static Directed_Weighted_Graph graph;
    JFrame frame;
    JPanel graph_panel;
//    private int kRADIUS = 5;
//    private int mWin_h = 500;
//    private int mWin_w = 500;
//    private Image mBuffer_image;
//    private Graphics mBuffer_graphics;
//    frame.setSize(500, 500);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    JPanel graph_panel = new JPanel();
//    graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//    frame.add(graph_panel);
//    graph_panel.setLayout(null);
    public GUI_runner(DirectedWeightedGraphAlgorithms algGraph, String json_file)
    {
        graph = (Directed_Weighted_Graph) algGraph.getGraph();
        this.frame = new JFrame("Graph");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) (dimension.height), width = (int) (dimension.width*0.75);
        this.frame.setSize(width, height);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.graph_panel = new JPanel();
        this.graph_panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.frame.add(this.graph_panel, BorderLayout.CENTER);
        this.graph_panel.setLayout(null);

        JMenuBar Graph = new JMenuBar();
        this.frame.setJMenuBar(Graph);

        JMenu Charge = new JMenu("Charge");
        JMenuItem charge = new JMenuItem("charge");
        charge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                alg.load(json_file);
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();

                boolean flag = algGraph.load(json_file);
                if (flag)
                {
                    JLabel sucLable = new JLabel("The graph was success loaded!");
                    sucLable.setBounds(200, 200, 100, 45);
                    graph_panel.add(sucLable);
                }
                else
                {
                    JLabel unsucLable = new JLabel("The graph was success loaded!");
                    unsucLable.setBounds(200, 200, 100, 45);
                    graph_panel.add(unsucLable);
                }
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });
        Charge.add(charge);

        JMenu Save = new JMenu("Save");
        JMenuItem save = new JMenuItem("save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();

                boolean flag = algGraph.load(json_file);
                if (flag) {
                    JLabel sucLable = new JLabel("The graph was success saved!");
                    sucLable.setBounds(200, 200, 100, 45);
                    graph_panel.add(sucLable);
                } else {
                    JLabel unsucLable = new JLabel("The graph was success saved!");
                    unsucLable.setBounds(200, 200, 100, 45);
                    graph_panel.add(unsucLable);
                }
                frame.setVisible(true);
            }
        });
        Save.add(save);

        JMenu Draw = new JMenu("Draw");
        JMenuItem draw = new JMenuItem("draw");
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph_panel.removeAll();
                GraphPanel graph_draw = new GraphPanel(graph);
                frame.add(graph_draw);
                //graph_draw.setVisible(true);
                frame.setVisible(true);
              //  DirectedWeightedGraph graph = getGrapg(json_file);
                // GUI_Graph_Draw(graph);
            }
        });
        Draw.add(draw);

        JMenu Change = new JMenu("Change");
        JMenuItem Add_vertice = new JMenuItem("Add vertice");
        Add_vertice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();
                JLabel addLable = new JLabel("Add new vertice");
                addLable.setBounds(100, 10, 100, 45);
                graph_panel.add(addLable);

                JLabel keyLable = new JLabel("id:");
                keyLable.setBounds(30, 40, 100, 45);
                graph_panel.add(keyLable);

                JTextField keyText = new JTextField();
                keyText.setBounds(80, 50, 165, 25);
                graph_panel.add(keyText);

                JLabel weightLable = new JLabel("weight:");
                weightLable.setBounds(30, 70, 100, 45);
                graph_panel.add(weightLable);

                JTextField weightText = new JTextField();
                weightText.setBounds(80, 80, 165, 25);
                graph_panel.add(weightText);

                JLabel locationLable = new JLabel("location:");
                locationLable.setBounds(30, 100, 100, 45);
                graph_panel.add(locationLable);

                JLabel XLable = new JLabel("X:");
                XLable.setBounds(100, 100, 100, 45);
                graph_panel.add(XLable);

                JTextField XText = new JTextField();
                XText.setBounds(120, 110, 165, 25);
                graph_panel.add(XText);

                JLabel YLable = new JLabel("Y:");
                YLable.setBounds(100, 140, 100, 45);
                graph_panel.add(YLable);

                JTextField YText = new JTextField();
                YText.setBounds(120, 150, 165, 25);
                graph_panel.add(YText);

                JLabel ZLable = new JLabel("Z:");
                ZLable.setBounds(100, 180, 100, 45);
                graph_panel.add(ZLable);

                JTextField ZText = new JTextField();
                ZText.setBounds(120, 190, 165, 25);
                graph_panel.add(ZText);

                JButton addButton = new JButton("Add");
                addButton.setBounds(30, 220,80,25);
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NodeData new_node = new Node_Data(parseInt(keyText.getText()),
                                new Geo_Location(Double.parseDouble(XText.getText()), Double.parseDouble(YText.getText()), Double.parseDouble(ZText.getText())),
                                Double.parseDouble(weightText.getText()));
                        graph.addNode(new_node);
                        GraphPanel graph_draw = new GraphPanel(graph);
                        frame.add(graph_draw);
                    }
                });
                graph_panel.add(addButton);
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });

        JMenuItem Remove_vertice = new JMenuItem("Remove vertice");
        Remove_vertice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();

                JLabel addLable = new JLabel("Remove a vertice");
                addLable.setBounds(100, 10, 100, 45);
                graph_panel.add(addLable);

                JLabel keyLable = new JLabel("id:");
                keyLable.setBounds(30, 40, 100, 45);
                graph_panel.add(keyLable);

                JTextField keyText = new JTextField();
                keyText.setBounds(80, 50, 165, 25);
                graph_panel.add(keyText);

                JButton removeButton = new JButton("Remove");
                removeButton.setBounds(30, 80,100,25);
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graph.removeNode(parseInt(keyText.getText()));
                        GraphPanel graph_draw = new GraphPanel(graph);
                        frame.add(graph_draw);
                    }
                });
                graph_panel.add(removeButton);
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });

        JMenuItem Add_edge = new JMenuItem("Add edge");
        Add_edge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();

                JLabel addLable = new JLabel("Add an edge");
                addLable.setBounds(100, 10, 100, 45);
                graph_panel.add(addLable);

                JLabel srcLable = new JLabel("src:");
                srcLable.setBounds(30, 40, 100, 45);
                graph_panel.add(srcLable);

                JTextField srcText = new JTextField();
                srcText.setBounds(80, 50, 165, 25);
                graph_panel.add(srcText);

                JLabel destLable = new JLabel("dest:");
                destLable.setBounds(30, 70, 100, 45);
                graph_panel.add(destLable);

                JTextField destText = new JTextField();
                destText.setBounds(80, 80, 165, 25);
                graph_panel.add(destText);

                JLabel weightLable = new JLabel("weight:");
                weightLable.setBounds(30, 100, 100, 45);
                graph_panel.add(weightLable);

                JTextField weightText = new JTextField();
                weightText.setBounds(100, 110, 165, 25);
                graph_panel.add(weightText);

                JButton addButton = new JButton("Add");
                addButton.setBounds(30, 140,80,25);
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graph.connect(parseInt(srcText.getText()),
                                parseInt(destText.getText()),
                                Double.parseDouble(weightText.getText()));
                        GraphPanel graph_draw = new GraphPanel(graph);
                        frame.add(graph_draw);
                    }
                });
                graph_panel.add(addButton);
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });

        JMenuItem Remove_edge = new JMenuItem("Remove edge");
        Remove_edge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();
                JLabel addLable = new JLabel("Remove an edge");
                addLable.setBounds(100, 10, 100, 45);
                graph_panel.add(addLable);

                JLabel srcLable = new JLabel("src:");
                srcLable.setBounds(30, 40, 100, 45);
                graph_panel.add(srcLable);

                JTextField srcText = new JTextField();
                srcText.setBounds(80, 50, 165, 25);
                graph_panel.add(srcText);

                JLabel destLable = new JLabel("dest:");
                destLable.setBounds(30, 70, 100, 45);
                graph_panel.add(destLable);

                JTextField destText = new JTextField();
                destText.setBounds(80, 80, 165, 25);
                graph_panel.add(destText);

                JButton removeButton = new JButton("Remove");
                removeButton.setBounds(30, 140,100,25);
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graph.removeEdge(parseInt(srcText.getText()),
                                parseInt(destText.getText()));
                        GraphPanel graph_draw = new GraphPanel(graph);
                        frame.add(graph_draw);
                    }
                });
                graph_panel.add(removeButton);
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });

        JMenu Algorithm = new JMenu("Algorithm");
        JMenuItem center = new JMenuItem("center");
        center.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();

                JLabel centerLable = new JLabel(String.valueOf(algGraph.center().getKey()));
                centerLable.setBounds(200, 200, 100, 45);
                centerLable.setVisible(true);
                graph_panel.add(centerLable);
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });
        JMenuItem shortestPathDist = new JMenuItem("shortest Path Dist");
        shortestPathDist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();

                JLabel addLable = new JLabel("Check Path Between Two Vertices");
                addLable.setBounds(50, 10, 200, 45);
                graph_panel.add(addLable);

                JLabel srcLable = new JLabel("src:");
                srcLable.setBounds(30, 40, 100, 45);
                graph_panel.add(srcLable);

                JTextField srcText = new JTextField();
                srcText.setBounds(80, 50, 165, 25);
                graph_panel.add(srcText);

                JLabel destLable = new JLabel("dest:");
                destLable.setBounds(30, 70, 100, 45);
                graph_panel.add(destLable);

                JTextField destText = new JTextField();
                destText.setBounds(80, 80, 165, 25);
                graph_panel.add(destText);

                JButton checkButton = new JButton("Check");
                checkButton.setBounds(30, 140,80,25);
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JLabel distLable = new JLabel(String.valueOf(algGraph.shortestPathDist(parseInt(srcText.getText()), parseInt(destText.getText()))));
                        distLable.setBounds(200, 200, 100, 45);
                        graph_panel.add(distLable);
                    }
                });
                graph_panel.add(checkButton);
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });
        JMenuItem shortestPath = new JMenuItem("shortest path");
        shortestPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();

                JLabel addLable = new JLabel("Check Path Between Two Vertices");
                addLable.setBounds(50, 10, 200, 45);
                graph_panel.add(addLable);

                JLabel srcLable = new JLabel("src:");
                srcLable.setBounds(30, 40, 100, 45);
                graph_panel.add(srcLable);

                JTextField srcText = new JTextField();
                srcText.setBounds(80, 50, 165, 25);
                graph_panel.add(srcText);

                JLabel destLable = new JLabel("dest:");
                destLable.setBounds(30, 70, 100, 45);
                graph_panel.add(destLable);

                JTextField destText = new JTextField();
                destText.setBounds(80, 80, 165, 25);
                graph_panel.add(destText);

                JButton checkButton = new JButton("Check");
                checkButton.setBounds(30, 140,80,25);
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        JLabel pathLable = new JLabel(String.valueOf(algGraph.shortestPathDist(parseInt(srcText.getText()), parseInt(destText.getText()))));
//                        pathLable.setBounds(200, 200, 100, 45);
//                        graph_panel.add(pathLable);
                        List<NodeData> path =  algGraph.shortestPath(parseInt(srcText.getText()), parseInt(destText.getText()));
                        PathPanel graph_draw = new PathPanel(graph, (ArrayList<Node_Data>) path);
                        frame.add(graph_draw);
                    }
                });
                graph_panel.add(checkButton);
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });
        JMenuItem isConnected = new JMenuItem("is connected?");
        isConnected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();

                JLabel algLable = new JLabel(String.valueOf(algGraph.isConnected()));
                algLable.setBounds(200, 200, 100, 45);
                graph_panel.add(algLable);
                graph_panel.repaint();
                frame.setVisible(true);

            }
        });
        JMenuItem tsp = new JMenuItem("tsp");
        tsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();

                JLabel addLable = new JLabel("Check Path Between Two Vertices");
                addLable.setBounds(100, 10, 200, 45);
                graph_panel.add(addLable);

                JLabel srcLable = new JLabel("src:");
                srcLable.setBounds(30, 40, 100, 45);
                graph_panel.add(srcLable);

                JTextField srcText = new JTextField();
                srcText.setBounds(80, 50, 165, 25);
                graph_panel.add(srcText);

                JLabel destLable = new JLabel("dest:");
                destLable.setBounds(30, 70, 100, 45);
                graph_panel.add(destLable);

                JTextField destText = new JTextField();
                destText.setBounds(80, 80, 165, 25);
                graph_panel.add(destText);

                JButton checkButton = new JButton("Check");
                checkButton.setBounds(30, 140,80,25);
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JLabel pathLable = new JLabel(String.valueOf(algGraph.shortestPathDist(parseInt(srcText.getText()), parseInt(destText.getText()))));
                        pathLable.setBounds(200, 200, 100, 45);
                        graph_panel.add(pathLable);
                    }
                });
                graph_panel.add(checkButton);
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });

        Graph.add(Charge);
        Graph.add(Save);
        Graph.add(Draw);
        Graph.add(Change);
        Graph.add(Algorithm);
        Change.add(Add_vertice);
        Change.add(Remove_vertice);
        Change.add(Add_edge);
        Change.add(Remove_edge);
        Algorithm.add(center);
        Algorithm.add(shortestPathDist);
        Algorithm.add(shortestPath);
        Algorithm.add(isConnected);
        Algorithm.add(tsp);

        this.graph_panel.setVisible(true);
        this.frame.setVisible(true);
    }


    public static void runGUI(DirectedWeightedGraphAlgorithms algG, String json_file)
    {
       new GUI_runner(algG, json_file);
    }

//     private class GUI_Graph_Draw extends JPanel{
//
//        public void paint(Graphics graphics)
//        {
//
//        }
//
//        @Override
//         public void paintComponents(Graphics graphics)
//         {
//
//         }
//    }
}

//    public static void main(String[] args) {
//        DirectedWeightedGraph graph = new Directed_Weighted_Graph();
//        double x1 = 35.18869800968523, x2 = 35.187594216303474, x3 = 35.19381366747377, x4=23.2457754356 ,x5=22.5688766787,
//                y1 = 32.104927164705884, y2 = 32.10378225882353, y3 = 32.102419275630254, y4=30.0 , y5=32.55553,
//                z = 0.0 , w1 = 0.6, w2 = 9.7, w3 = 0.3, w4=10.2, w5=12.3;
//        GeoLocation loc1 = new Geo_Location(x1,y1,0);
//        GeoLocation loc2 = new Geo_Location(x2,y2,0);
//        GeoLocation loc3 = new Geo_Location(x3,y3,0);
//        GeoLocation loc4 = new Geo_Location(x4,y4,0);
//        GeoLocation loc5 = new Geo_Location(x5,y5,0);
//        String inf1 = "35.18869800968523,32.104927164705884,0.0";
//        String inf2 = "32.104927164705884,32.10378225882353,0.0";
//        String inf3 = "35.19381366747377,32.102419275630254,0.0";
//        String inf4 = "23.2457754356,30.0,0.0";
//        String inf5 = "22.5688766787,32.55553,0.0";
//        int ta1 = Node_Data.WHITE, ta2 = Node_Data.GRAY, ta3 = Node_Data.BLACK;
//        int src1 = 0, src2 = 1, src3 = 2, dest1 = 3, dest2 = 4, dest3 = 15;
//        NodeData n1 = new Node_Data(src1, loc1, w1,inf1, ta1);
//        NodeData n2 = new Node_Data(src2,loc2, w2, inf2, ta2);
//        NodeData n3 = new Node_Data(src3,loc3, w3, inf3, ta3);
//        NodeData n4 = new Node_Data(dest1, loc4, w4,inf4, ta1);
//        NodeData n5 = new Node_Data(dest2, loc5, w5,inf5, ta2);
//        double we1=4 , we2=8, we3=11, we4=12 ,we5=22 , we6=31;
//        Edge_Data e1= new Edge_Data(n1.getKey(),n2.getKey(),we1);
//        Edge_Data e2= new Edge_Data(n2.getKey(),n3.getKey(),we2);
//        Edge_Data e3= new Edge_Data(n3.getKey(),n4.getKey(),we3);
//        Edge_Data e4= new Edge_Data(n4.getKey(),n5.getKey(),we4);
//        Edge_Data e5= new Edge_Data(n5.getKey(),n1.getKey(),we5);
//        Edge_Data e6= new Edge_Data(n5.getKey(),n5.getKey(),we6);
//        graph.addNode(n1);
//        graph.addNode(n2);
//
//
//
//        GUI_Graph_Draw gui = new GUI_Graph_Draw();
//        gui.GUI_Graph(graph);
//        JFrame frame = new JFrame();
//        frame.setSize(500, 500);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel gPanel = new JPanel();
//        gPanel.setSize(150, 150);
//
////        frame.add(gPanel);
//        frame.add(gui);
//        frame.setVisible(true);
//    }
//public static void runGUI(String json_file) {
//    DirectedWeightedGraphAlgorithms.java alg = getGrapgAlgo(json_file);
//    JFrame frame = new JFrame("Graph");
//    frame.setSize(500, 500);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    JPanel graph_panel = new JPanel();
//    graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//    frame.add(graph_panel);
//    graph_panel.setLayout(null);
//
//    JMenuBar Graph = new JMenuBar();
//    frame.setJMenuBar(Graph);
//
//    JMenu Charge = new JMenu("Charge");
//    JMenuItem charge = new JMenuItem("charge");
//    charge.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
////                alg.load(json_file);
////                JPanel graph_panel = new JPanel();
////                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
////                frame.add(graph_panel);
////                graph_panel.setLayout(null);
//            graph_panel.removeAll();
//
//            boolean flag = alg.load(json_file);
//            if (flag)
//            {
//                JLabel sucLable = new JLabel("The graph was success loaded!");
//                sucLable.setBounds(200, 200, 100, 45);
//                graph_panel.add(sucLable);
//            }
//            else
//            {
//                JLabel unsucLable = new JLabel("The graph was success loaded!");
//                unsucLable.setBounds(200, 200, 100, 45);
//                graph_panel.add(unsucLable);
//            }
//            frame.setVisible(true);
//        }
//    });
//    Charge.add(charge);
//
//    JMenu Save = new JMenu("Save");
//    JMenuItem save = new JMenuItem("save");
//    save.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
////                JPanel graph_panel = new JPanel();
////                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
////                frame.add(graph_panel);
////                graph_panel.setLayout(null);
//            graph_panel.removeAll();
//
//            boolean flag = alg.load(json_file);
//            if (flag) {
//                JLabel sucLable = new JLabel("The graph was success saved!");
//                sucLable.setBounds(200, 200, 100, 45);
//                graph_panel.add(sucLable);
//            } else {
//                JLabel unsucLable = new JLabel("The graph was success saved!");
//                unsucLable.setBounds(200, 200, 100, 45);
//                graph_panel.add(unsucLable);
//            }
//            frame.setVisible(true);
//        }
//    });
//    Save.add(save);
//