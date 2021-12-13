import GUI.GUI_runner;
import api.*;
//import com.google.gson.JsonParser;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;

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
        ans.load(json_file);
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
//        int src1 = 0, src2 = 1, src3 = 2;
//        double x1 = 35.18869800968523, x2 = 40.187594216303474, x3 = 29.19381366747377,
//                y1 = 32.104927164705884, y2 = 48.10378225882353, y3 = 45.102419275630254,
//                w1 = 0.6, w2 = 9.7, w3 = 0.3;
//        GeoLocation loc1 = new Geo_Location(x1,y1,0);
//        GeoLocation loc2 = new Geo_Location(x2,y2,0);
//        GeoLocation loc3 = new Geo_Location(x3,y3,0);
//        String inf1 = "35.18869800968523,32.104927164705884,0.0";
//        String inf2 = "32.104927164705884,32.10378225882353,0.0";
//        String inf3 = "35.19381366747377,32.102419275630254,0.0";
//        int ta1 = Node_Data.WHITE, ta2 = Node_Data.GRAY, ta3 = Node_Data.BLACK;
//        NodeData n1 = new Node_Data(src1, loc1, w1,inf1, ta1);
//        NodeData n2 = new Node_Data(src2,loc2, w2, inf2, ta2);
//        NodeData n3 = new Node_Data(src3,loc3, w3, inf3, ta3);
//        DirectedWeightedGraph graph = new Directed_Weighted_Graph();
//        graph.addNode(n1);
//        graph.addNode(n2);
//        graph.addNode(n3);
//        alg.init(graph);
        GUI_runner.runGUI(alg, json_file);
    }


    public static void main(String[] args) {
//        Object ob = jsonpars.parse(new FileReader(file));
//        JSONObject file_g = (JSONObject) ob;
//        String file =
        //runGUI(args[0]);
        runGUI("C:\\Users\\hadar\\IdeaProjects\\Ex2\\data\\G1.json");
//        HashMap<String, EdgeData> map = new LinkedHashMap<>();
//        String str1 = "0, 4", str2 = "1, 0", str3 = "2, 1";
//        EdgeData e1 = new Edge_Data(0, 4, 22), e2 = new Edge_Data(1, 0, 31),
//                e3 = new Edge_Data(2, 1, 8);
//        map.put(str1, e1);
//        map.put(str2, e2);
//        map.put(str3, e3);
//        for (EdgeData e: map.values())
//        {
//            System.out.println(e.getSrc() +" "+e.getWeight()+" "+e.getDest());
//        }
    }
}



