#  Ex2

## Overview:
Writing an algorithm and creating a link graph.
the data structure that the assignment is built on is an directional weighted graph. 
for info about this type of graph you can visit: [https://en.wikipedia.org/wiki/Directed_graph].
the process of building this data structrue was to implement and program the interfaces sent by Boaz-Ben-Moshe.
here are the classes of this data structure:

| Class      | Description |
| :------------| :------------|
| Node_Data  | represents a vertices on the grap |
| Edge_Data   |   represent an edge which connects two different vertices|
| Geo_Location      |    represents a 3 coordinates point|
| Directed_Weighted_Graph      |    represents an graph based on the vertices and edges |
| Directed_Weighted_Graph_Algorithms      |    a data structure based on the DirectedWeightedGraph structrue that offers variuos algorithms | 

## Node_Data
+ Represents a vertice on the graph.
   + **Key:** specific key associated with this node.
   + **Tag:** contains an integer, helps with graph algorithms.
   + **Weight:** the weight of the node.
   + **Info:** a string associated with the node, helps with graph algorithms.
   + **Location:** the location of the node in 3D.

## Edge_Data
+ Represent an edge which connects two different node.
    + **Src:** source node.
    + **Dest:** destination node.
    + **Tag:** contains an integer, helps with graph algorithms.
    + **Info:** a string associated with the edge, helps with graph algorithms.
    + **Weight:** the weight of the edge from source to destination.
    
## Geo_Location
+ Represents a location in 3D.
  + **X:** x value.
  + **Y:** y value.
  + **Z:** z value.

## Directed_Weighted_Graph
+ Represents an graph based on the nodes and edges.
  + **NodeMap:** an HashMap contains all the vertices of the graph.
  + **EdgeMap:** an HashMap contains all the edges of the graph.
  + **MC:** counts the changes in the graph.
  + **edgeSize:** the amount of edges.
  + **nodeSize:** the amount of nodes.

## Directed_Weighted_Graph_Algorithms
+ The class that contains the whole algorithms that can be used on a graph.
     + **graph:** the graph that the algorithms being used on. 

# putting UML here ??

## The algorithems we used:
in order to calculate the shortest path between two given edges on the graph, we used dijkstra's algorithm.
for info about this specific algorithm please visit: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm.
in order to calculate the center in the graph, we used Floyd–Warshall algorithm.
for info about this algorithem please visit: https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm.
-in this assignment we also asked to build a GUI were we could load graphs using a JSON file.

## putting pictuer of the GUI here


### Collaborators
  + **HADAR BITAN**
  + **EDEN ROAS**
