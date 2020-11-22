I. File list       File Type
----------------------------
node_info----------Java File/Interface	
NodeInfo-----------Java File/Class -- Nested inside WGraph_DS
weighted_graph--------------Java File/Interface
WGraph_DS-----------Java File/Class
weighted_graph_algorithims--Java File/Interface
WGraph_Algo---------Java File/Class

----------------------------
A bit about Graphs and basic terminoligy(Our Project represents an weighted undirected graph).
An undirected graph is graph, i.e., a set of objects (called vertices or nodes) that are connected together,
where all the edges are bidirectional. An undirected graph is sometimes called an undirected network.
In contrast, a graph where the edges point in a direction is called a directed graph.

Weighted Graph - A weighted graph or a network is a graph in which a number (the weight) is assigned to each edge. Such weights might represent for example costs, lengths or capacities, depending on the problem at hand. 
Such graphs arise in many contexts, for example in shortest path problems such as the traveling salesman problem. 

One can formally define an undirected graph as G = (V, E), where V is a set whose elements are called vertices (singular: vertex), 
and E is a set of paired vertices, whose elements are called edges (sometimes links or lines).

----------------------------

Terminology:
A graph consists of:

A set, V, of vertices (nodes)

A collection, E, of pairs of vertices from V called edges (arcs)

Edges, also called arcs, are represented by (u, v) and are either:

Directed if the pairs are ordered (u, v)

u the origin
v the destination

Undirected if the pairs are unordered

Then a graph can be:

Directed graph (di-graph) if all the edges are directed
Undirected graph (graph) if all the edges are undirected
Mixed graph if edges are both directed or undirected
Illustrate terms on graphs

End-vertices of an edge are the endpoints of the edge.

Two vertices are adjacent if they are endpoints of the same edge.

An edge is incident on a vertex if the vertex is an endpoint of the edge.

Outgoing edges of a vertex are directed edges that the vertex is the origin.
Incoming edges of a vertex are directed edges that the vertex is the destination.

Degree of a vertex, v, denoted deg(v) is the number of incident edges.
Out-degree, outdeg(v), is the number of outgoing edges.
In-degree, indeg(v), is the number of incoming edges.

Parallel edges or multiple edges are edges of the same type and end-vertices
Self-loop is an edge with the end vertices the same vertex
Simple graphs have no parallel edges or self-loops

Path is a sequence of alternating vetches and edges such that each successive vertex is connected by the edge.  Frequently only the vertices are listed especially if there are no parallel edges.
Cycle is a path that starts and end at the same vertex.
Simple path is a path with distinct vertices.
Directed path is a path of only directed edges
Directed cycle is a cycle of only directed edges.

Sub-graph is a subset of vertices and edges.
Spanning sub-graph contains all the vertices.

Connected graph has all pairs of vertices connected by at least one path.
Connected component is the maximal connected sub-graph of a unconnected graph.
Forest is a graph without cycles.
Tree is a connected forest (previous type of trees are called rooted trees, these are free trees)
Spanning tree is a spanning subgraph that is also a tree.

------------------------------

Each Class and it's functions and it's parameters:

NodeData: Class Representing The Node and it's data + Functions to apply on.
------------------------------
Parameters:
int Key - The Unique Key/ID of each Node.
double Tag - Temporal data that represents weight from a certain node .
String info - Meta data associated with this node. Represents if a node has been visited.

-------------
Functions:
public NodeInfo - Constructor
public int getKey(); - Return the key (id) associated with this node.
public String getInfo(); - Returns the meta data associated with this node.
public void setInfo(String s); - Change the meta data.
public double getTag(); - Recieve temporal data.(WEIGHT)
public void setTag(double t); - Set temporal data.
public int compareTo(Object o); - A function to compare two node tags
public boolean equals(Object obj) - This function checks if two nodes are Equal and if so Return T.
------------------------------
WGraph_DS Class Representing The Graph and it's data + Functions to apply on.
------------------------------
Parameters:
Hashmap vertices - Hashmap of the nodes/vertices on the Graph
Hashmap edges- Hashmap of Hashmaps that contain edges and weights
int edgesize - number of edges on the graph
int mc- number of modifications that have occured on the graph
-------------
Functions:
public WGraph_DS(); - Weighted Graph Constructor
public node_info getNode(int key); - This function based on a given node ID checks if the node exists on the graph. If so return T, Else Null.
public boolean hasEdge(int node1, int node2); - Boolean function to check is an edge exists two specific nodes
public double getEdge(int node1, int node2); - Function that returns the existing edge between teo nodes, if they dont exist will return -1.
public void addNode(int key); - Add node to Weighted Graph 
public void connect(int node1, int node2, double w); - Connects two nodes on a Weighted Graph and updates W.
public Collection<node_info> getV(); - Returns a Collection of all the Vertices on the Graph.
public Collection<node_info> getV(int node_id); - Eill return a collection of a specific nodes neighbor.
public node_info removeNode(int key); - Remove a specific node from Graph.
public void removeEdge(int node1, int node2); - Function that will remove and edge between two nodes if exists.
public int nodeSize(); - Return Size of Graph/ # of Nodes in it.
public int edgeSize(); - Returns Edge size of Graph.
public int getMc(); - Get modification Count
public boolean equals(Object obj); - Boolean function to check if two weighted Graphs are equal.
-------------

------------------------------
WGraph_Algo Class Representing The Algorithims that are appliciable on my Graph.
------------------------------
Parameters:
Hashmap neighbors - A hashmap of neighbors
weighted_graph finalgraph - Weighted Graph...
-------------
Functions:
public weighted_graph getGraph(); - Returns our Graph.
public void init(weighted_graph g); - Init the graph on which this set of algorithms operates on.
public weighted_graph copy(); - Returns a Graph identical to the one I  DEEP COPIED.
public boolean isConnected(); - Returns T/F if Graph connected(Look Terminoligy section to undersyand Connected Graph)
public double shortestPathDist(int src, int dest); - Returns if Exixsts the shortest path(based on weight) between the recieved nodes.
public List<node_info> shortestPath(int src, int dest); - Returns a list of nodes that represent the shortest path(weight based) between two given nodes.
public boolean save(String file); Saves this weighted (undirected) graph to the given file name, Return T if Succesfull, Else F
public boolean load(String file); Loades the weighted undirected Graph
CREATED BY NADAV SUISA 
















