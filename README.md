I. File list       File Type
----------------------------
node_data----------Java File/Interface	
NodeData-----------Java File/Class
graph--------------Java File/Interface
Graph_DS-----------Java File/Class
graph_algorithims--Java File/Interface
Graph_Algo---------Java File/Class

----------------------------
A bit about Graphs and basic terminoligy(Our Project represents an unweighted graph).
An undirected graph is graph, i.e., a set of objects (called vertices or nodes) that are connected together,
where all the edges are bidirectional. An undirected graph is sometimes called an undirected network.
In contrast, a graph where the edges point in a direction is called a directed graph. 

Weighted Graph - A weighted graph or a network[9][10] is a graph in which a number (the weight) is assigned to each edge.[11] Such weights might represent for example costs, lengths or capacities, depending on the problem at hand. Such graphs arise in many contexts, for example in shortest path problems such as the traveling salesman problem.

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
int Tag - Temporal data (aka color: e,g, white, gray, black)
string info - Meta data associated with this node.
int keyindex = Index of Keys
HashMap neigbors - A map containing nodes.
-------------
Functions:
public int getKey(); - Return the key (id) associated with this node.
public Collection<node_data> getNi(); - Returns a collection with all the Neighbor nodes of this node_data
public boolean hasNi(int key); - Return T/F if the node associated with this key is a neighbor. 
public void addNi(node_data t); - This method adds the node_data (t) to this node_data.
public void removeNode(node_data node); - Deletion of node.
public String getInfo(); - Returns the meta data associated with this node.
public void setInfo(String s); - Change the meta data.
public int getTag(); - Recieve temporal data.
public void setTag(int t); - Set temporal data.
------------------------------
Graph_DS Class Representing The Graph and it's data + Functions to apply on.
------------------------------
Parameters:
int edgecount - Edge count of the graph
int modcount- modification count(internal graph modifications)
HashMap neigbors - A map of the GRAPH
-------------
Functions:
public node_data getNode(int key); - return the node_data by the node_id,
public boolean hasEdge(int node1, int node2); - Returns T/F if between V 1 and V 2 there is an edge.
public void addNode(node_data n); - Adds a new node to the graph with the given node_data.
public void connect(int node1, int node2); - Connect an edge between node1 and node2.
public Collection<node_data> getV(); - Returns collection representing all the nodes in the graph.
public Collection<node_data> getV(int node_id); - Returns a collection of nodes connected to this specific node.
public node_data removeNode(int key); - Delete's the node from the fraph and temove all edges related to this node.
public void removeEdge(int node1, int node2); - Remove edge between Node 1 and Node 2.
public int nodeSize(); - Return number of Vertices in Graph(all Nodes).
public int edgeSize(); - With the help of edgecound i return all the edges in the Graph.
public int getMC(); - With the help of modcount i return all the modifications i have made to the internal state of the Graph.
------------------------------
Graph_Algo Class Representing The functions that are appliable on my Graph.
------------------------------
Parameters:

-------------
Functions:
public void init(graph g); - Init the graph on which this set of algorithms operates on.
public graph copy(); - Returns a Graph identical to the one i copied.
public boolean isConnected(); - Returns T/F if Graph connected(Look Terminoligy section to undersyand Connected Graph)
public int shortestPathDist(int src, int dest); - Returns if Exixsts the shortest path between the recieved nodes.
public List<node_data> shortestPath(int src, int dest); - Returns a list of nodes that represent the shortest path between two given nodes.

CREATED BY NADAV SUISA 
















