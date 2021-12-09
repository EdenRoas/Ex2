import api.*;
import org.json.simple.JSONObject;
//import com.google.gson.JsonParser;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;

import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.*;

import static java.lang.Integer.parseInt;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = null;
        DirectedWeightedGraphAlgorithms algorithms = getGrapgAlgo(json_file);
        ans = algorithms.getGraph();
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        ans = new Directed_Weighted_Graph_Algorithms();
        //ans.load(json_file);
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        JFrame frame = new JFrame("Graph");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel graph_panel = new JPanel();
        graph_panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        frame.add(graph_panel);
        graph_panel.setLayout(null);

        JMenuBar Graph = new JMenuBar();
        frame.setJMenuBar(Graph);

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

                boolean flag = alg.load(json_file);
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

                boolean flag = alg.load(json_file);
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

                DirectedWeightedGraph graph = getGrapg(json_file);
                GUI_Graph_Draw.runGUI_Graph(graph);
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
                        alg.getGraph().addNode(new_node);
                    }
                });
                graph_panel.add(addButton);

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
                        alg.getGraph().removeNode(parseInt(keyText.getText()));
                    }
                });
                graph_panel.add(removeButton);

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
                        alg.getGraph().connect(parseInt(srcText.getText()),
                                parseInt(destText.getText()),
                                Double.parseDouble(weightText.getText()));
                    }
                });
                graph_panel.add(addButton);

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
                destText.setBounds(30, 80, 165, 25);
                graph_panel.add(destText);

                JButton removeButton = new JButton("Remove");
                removeButton.setBounds(30, 140,80,25);
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        alg.getGraph().removeEdge(parseInt(srcText.getText()),
                                parseInt(destText.getText()));
                    }
                });
                graph_panel.add(removeButton);

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

                JLabel centerLable = new JLabel(String.valueOf(alg.center()));
                centerLable.setBounds(200, 200, 100, 45);
                graph_panel.add(centerLable);
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

                JButton checkButton = new JButton("Check");
                checkButton.setBounds(30, 140,80,25);
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JLabel distLable = new JLabel(String.valueOf(alg.shortestPathDist(parseInt(srcText.getText()), parseInt(destText.getText()))));
                        distLable.setBounds(200, 200, 100, 45);
                        graph_panel.add(distLable);
                    }
                });
                graph_panel.add(checkButton);
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

                JButton checkButton = new JButton("Check");
                checkButton.setBounds(30, 140,80,25);
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JLabel pathLable = new JLabel(String.valueOf(alg.shortestPathDist(parseInt(srcText.getText()), parseInt(destText.getText()))));
                        pathLable.setBounds(200, 200, 100, 45);
                        graph_panel.add(pathLable);
                    }
                });
                graph_panel.add(checkButton);
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

                JLabel algLable = new JLabel(String.valueOf(alg.isConnected()));
                algLable.setBounds(200, 200, 100, 45);
                graph_panel.add(algLable);
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

        frame.setVisible(true);
    }


    public static void main(String[] args) {
//        Object ob = jsonpars.parse(new FileReader(file));
//        JSONObject file_g = (JSONObject) ob;
//        String file =
        runGUI("C:\\Users\\hadar\\IdeaProjects\\Ex2\\data\\G1.json");
    }
}



