package GUI;

import api.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import static java.lang.Integer.parseInt;

public class GUI_runner {

    public static Directed_Weighted_Graph graph;
    static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public static int height = (int) (dimension.height), width = (int) (dimension.width * 0.75);
    DirectedWeightedGraphAlgorithms algGraph;
    String json_file;
    JFrame frame;
    JPanel graph_panel;
    GraphPanel graph_draw;
    PathPanel path_draw;
    List<NodeData> path;
    boolean flagTSP;

    public GUI_runner(DirectedWeightedGraphAlgorithms algGraph, String json_file) {
        this.json_file = json_file;
        this.algGraph = algGraph;
        graph = (Directed_Weighted_Graph) algGraph.getGraph();
        this.frame = new JFrame("Graph");
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
                if(graph_draw != null)
                    frame.remove(graph_draw);
                if(path_draw != null)
                    frame.remove(path_draw);
                boolean flag = algGraph.load(json_file);
                if (flag) {
                    JOptionPane.showMessageDialog(frame, "The graph was success loaded!");
//                    JLabel sucLable = new JLabel("The graph was success loaded!");
//                    sucLable.setBounds(200, 200, 200, 45);
//                    graph_panel.add(sucLable);
                } else {
                    JOptionPane.showMessageDialog(frame, "The graph wasn't success loaded!");
//                    JLabel unsucLable = new JLabel("The graph was success loaded!");
//                    unsucLable.setBounds(200, 200, 200, 45);
//                    graph_panel.add(unsucLable);
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
                if(!(graph_draw == null))
                    frame.remove(graph_draw);
                if(!(path_draw == null))
                    frame.remove(path_draw);
                boolean flag = algGraph.load(json_file);
                if (flag) {
                    JOptionPane.showMessageDialog(frame, "The graph was success saved!");

//                    JLabel sucLable = new JLabel("The graph was success saved!");
//                    sucLable.setBounds(200, 200, 100, 45);
//                    graph_panel.add(sucLable);
                } else {
                    JOptionPane.showMessageDialog(frame, "The graph wasn't success saved!");
//
//                    JLabel unsucLable = new JLabel("The graph was success saved!");
//                    unsucLable.setBounds(200, 200, 100, 45);
//                    graph_panel.add(unsucLable);
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
                if(!(graph_draw == null))
                    frame.remove(graph_draw);
                if(!(path_draw == null))
                    frame.remove(path_draw);
                graph_draw = new GraphPanel(graph);
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
                if(!(graph_draw == null))
                    frame.remove(graph_draw);
                if(!(path_draw == null))
                    frame.remove(path_draw);
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
                addButton.setBounds(30, 220, 80, 25);
                addButton.addActionListener(this);
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(graph.getNode(parseInt(keyText.getText())) != null) {
                            JOptionPane.showMessageDialog(frame, "The vertice is already exit, please add a new one!");
                        }
                        else
                        {
                            NodeData new_node = new Node_Data(parseInt(keyText.getText()),
                                    new Geo_Location(Double.parseDouble(XText.getText()), Double.parseDouble(YText.getText()), Double.parseDouble(ZText.getText())),
                                    Double.parseDouble(weightText.getText()));
                            graph.addNode(new_node);
                            GraphPanel graph_draw = new GraphPanel(graph);
                            frame.add(graph_draw);
                        }
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
                if(graph_draw != null)
                    frame.remove(graph_draw);
                if(path_draw != null)
                    frame.remove(path_draw);
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
                removeButton.setBounds(30, 80, 100, 25);
                graph_panel.add(removeButton);
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(graph.getNode(parseInt(keyText.getText())) == null)
                        {
                            JOptionPane.showMessageDialog(frame, "The vertice isn't exit, please try another one!");
                        }
                        else
                        {
                            graph.removeNode(parseInt(keyText.getText()));
                            graph_panel.removeAll();

                            GraphPanel graph_draw = new GraphPanel(graph);
                            frame.add(graph_draw);
                            frame.setVisible(true);
                            frame.repaint();
                        }
                    }
                });
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
                if(!(graph_draw == null))
                    frame.remove(graph_draw);
                if(!(path_draw == null))
                    frame.remove(path_draw);
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
                addButton.setBounds(30, 140, 80, 25);
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(graph.getEdge(parseInt(srcText.getText()),parseInt(destText.getText())) != null)
                        {
                            JOptionPane.showMessageDialog(frame, "The edge is already exit, please try a new one!");
                            return;
                        }
                        else
                        {
                            graph.connect(parseInt(srcText.getText()),
                                    parseInt(destText.getText()),
                                    Double.parseDouble(weightText.getText()));
                            graph_panel.removeAll();
                            GraphPanel graph_draw = new GraphPanel(graph);
                            frame.add(graph_draw);
                            frame.repaint();
                        }
                    }
                });
                graph_panel.add(addButton);
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });
//        Add_edge.addActionListener(this);

        JMenuItem Remove_edge = new JMenuItem("Remove edge");
        Remove_edge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JPanel graph_panel = new JPanel();
//                graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//                frame.add(graph_panel);
//                graph_panel.setLayout(null);
                graph_panel.removeAll();
                if(!(graph_draw == null))
                    frame.remove(graph_draw);
                if(!(path_draw == null))
                    frame.remove(path_draw);
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
                removeButton.setBounds(30, 140, 100, 25);
                graph_panel.add(removeButton);
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(graph.getEdge(parseInt(srcText.getText()),parseInt(destText.getText())) == null)
                        {
                            JOptionPane.showMessageDialog(frame, "The edge isn't exit, please try another one!");
                        }
                        else
                        {
                            graph.removeEdge(parseInt(srcText.getText()),
                                    parseInt(destText.getText()));
                            graph_panel.removeAll();
                            GraphPanel graph_draw = new GraphPanel(graph);
                            frame.add(graph_draw);
                            frame.repaint();
                        }
                    }
                });
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });

        JMenu Algorithm = new JMenu("Algorithm");
        JMenuItem center = new JMenuItem("center");
        center.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph_panel.removeAll();
                if(!(graph_draw == null))
                    frame.remove(graph_draw);
                if(!(path_draw == null))
                    frame.remove(path_draw);
                JOptionPane.showMessageDialog(frame, String.valueOf(algGraph.center().getKey()));
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
                if(!(graph_draw == null))
                    frame.remove(graph_draw);
                if(!(path_draw == null))
                    frame.remove(path_draw);
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
                checkButton.setBounds(30, 140, 80, 25);
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, String.valueOf(algGraph.shortestPathDist(parseInt(srcText.getText()), parseInt(destText.getText()))));
//                        JLabel distLable = new JLabel(String.valueOf(algGraph.shortestPathDist(parseInt(srcText.getText()), parseInt(destText.getText()))));
//                        distLable.setBounds(200, 200, 100, 45);
//                        graph_panel.add(distLable);
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
                graph_panel.removeAll();
                if(!(graph_draw == null))
                    frame.remove(graph_draw);
                if(!(path_draw == null))
                    frame.remove(path_draw);
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
                checkButton.setBounds(30, 140, 80, 25);
                graph_panel.add(checkButton);
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.remove(graph_panel);
                        path = algGraph.shortestPath(parseInt(srcText.getText()), parseInt(destText.getText()));
                        path_draw = new PathPanel(graph, (ArrayList<NodeData>) path);
                        frame.add(path_draw);
                        graph_panel.repaint();
                    }
                });
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });
        JMenuItem isConnected = new JMenuItem("is connected?");
        isConnected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph_panel.removeAll();
                if(!(graph_draw == null))
                    frame.remove(graph_draw);
                if(!(path_draw == null))
                    frame.remove(path_draw);
                JOptionPane.showMessageDialog(frame, String.valueOf(algGraph.isConnected()));
                graph_panel.repaint();
                frame.setVisible(true);
            }
        });
        JMenuItem tsp = new JMenuItem("tsp");
        tsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph_panel.removeAll();
                if(graph_draw != null)
                    frame.remove(graph_draw);
                if(path_draw != null)
                    frame.remove(path_draw);

                flagTSP = true;
                while (flagTSP) {
                    JLabel tspLable = new JLabel("Add vertices to the list");
                    tspLable.setBounds(100, 10, 200, 45);
                    graph_panel.add(tspLable);
                    List<NodeData> tsp_list = new ArrayList<>();
                    JLabel keyLable = new JLabel("id:");
                    keyLable.setBounds(30, 40, 100, 45);
                    graph_panel.add(keyLable);

                    JTextField keyText = new JTextField();
                    keyText.setBounds(80, 50, 165, 25);
                    graph_panel.add(keyText);

                    Clicklistener clicklistener = new Clicklistener();
                    JButton addNodeToTSPButton = new JButton("Add");
                    addNodeToTSPButton.setBounds(30, 80, 80, 25);
                    addNodeToTSPButton.addActionListener(clicklistener);

                    JButton doneButton = new JButton("Done");
                    doneButton.setBounds(200, 80, 80, 25);
                    doneButton.addActionListener(clicklistener);

                    graph_panel.add(addNodeToTSPButton);
                    graph_panel.add(doneButton);
                    if (flagTSP)
                    {
                        NodeData new_node = graph.getNode(parseInt(keyText.getText()));
                        path.add(new_node);
                    }
                }
                List<NodeData> path_list = algGraph.tsp(path);
                graph_panel.removeAll();
                path_draw = new PathPanel(graph, (ArrayList<NodeData>) path_list);
                frame.add(path_draw);
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


    public static void runGUI(DirectedWeightedGraphAlgorithms algG, String json_file) {
        new GUI_runner(algG, json_file);
    }

    private class Clicklistener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Done")) {
                flagTSP = false;
            }

            if (e.getActionCommand().equals("Add"))
            {
               flagTSP = true;
            }
        }
    }
}
