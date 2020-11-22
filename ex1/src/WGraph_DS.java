package ex1.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class WGraph_DS implements weighted_graph, Serializable
{
    
    /**
     * CREATED BY NADAV SUISSA:
     * This class represents a general Weighted Graph that has a nested class for the nodes(Vertices)
     * The Graph Consists of four parts -- Two HashMaps that are:
     * 1-A HashMap that represents all the Vertices in the graph called - vertices.
     * 2-A Hashmap that represent all the Edges in the graph- called edges- for each node we connect another Hashmap within the value
     *      of the previous Hashmap that contains all the keys of it's neighbors and all the weight's of the edges between them.
     * 3-edgesize- Count of Edges on the weighted Graph
     * 4-MC/Modcount counts the number of changes that occurred in the Weighted Graph
     */
    private HashMap<Integer,node_info> vertices;
    private HashMap<Integer,HashMap<Integer,Double>> edges;
    private int edgesize,mc;


    //Constructor of the Weighted Graph, initiates the Hashmap's.
    public WGraph_DS ()
    {
        vertices=new HashMap<>();
        edges=new HashMap<>();
    }


     // This function based on a given node ID checks if the node exists on the graph
     // if so - return the expected node, else will return false.
    @Override
    public node_info getNode(int key)
    {
        if(vertices.get(key) != null){
            return vertices.get(key);
        }
        return null;
    }


    // This function based on two given node ID's checks if an edge between them exists
    // if so - return true, else or if one of the node's does not exist on the graph - will return false.
    @Override
    public boolean hasEdge(int node1, int node2)
    {
        if (vertices.get(node1)!=null && vertices.get(node2) !=null)
        {
            if (edges.get(node1).get(node2) != null && edges.get(node2).get(node1) != null) {
                return true;
            }
        }
        return false;
    }

    // This function based on two given node ID's will return the edge between them
    // if exists - return the edge, else - will return -1.
    @Override
    public double getEdge(int node1, int node2)
    {
        if (edges.get(node1).containsKey(node2))
        {
            return edges.get(node1).get(node2);
        }
        return -1;
    }

    // This function will add a node to the Weighted Graph and update the relative parameters(if doesn't already exist)
    @Override
    public void addNode(int key)
    {
        if (!vertices.containsKey(key))
        {
            if(WGraph_Algo.neighbors==null) {
                node_info n = new NodeInfo(key);
                vertices.put(key, n);
                HashMap<Integer, Double> neighbors = new HashMap<Integer, Double>();
                edges.put(key, neighbors);
            }
            else{
                node_info n = new NodeInfo(key);
                vertices.put(key, n);
                edges.put(key, WGraph_Algo.neighbors);
            }
            mc++;
        }
    }

    // This function based on two specific node's connects them with a given weight, if an edge between them exists the function
    // will update the current Weight, if one of the node's doesnt exist on the Weighted Graph then the function does nothing
    @Override
    public void connect(int node1, int node2, double w)
    {
        if(node1 == node2 |w<0)
        {
            return;
        }
        if (!vertices.containsKey(node1) & !vertices.containsKey(node2)){return;}
        if (edges.get(node1).containsKey(node2))
        {
            if (edges.get(node1).get(node2)!=w)
            {
                edges.get(node1).replace(node2, w);
                edges.get(node2).replace(node1,w);
                mc++;
            }
        }
        else
        {
            edges.get(node1).put(node2, w);
            edges.get(node2).put(node1, w);
            edgesize++;
            mc++;
        }

    }

    // This function returns all the Vertices in the Weighted Graph
    @Override
    public Collection<node_info> getV() {
        return vertices.values();
    }

    // This function based on a given node ID will return all of it's neighbors.
    @Override
    public Collection<node_info> getV(int node_id)
    {
        if (edges.get(node_id) == null || vertices.get(node_id) ==null)
        {
            return null;
        }
        ArrayList<node_info> nei = new ArrayList<node_info>();
        for (int key: edges.get(node_id).keySet())
        {
            nei.add(vertices.get(key));
        }
        return nei;
    }


    // This function based on a specific node deletes the node from the graph and disconnects himself from it's
    // existing neighbors,edgelist and at the end shall return the deleted node.
    @Override
    public node_info removeNode(int key)
    {
        if (vertices.get(key)==null)
        {
            return null;
        }
        if (edges.containsKey(key))
        {
            for (int k : edges.get(key).keySet()) {
                edges.get(k).remove(key);
                edgesize--;
                mc++;
            }
        }
        edges.remove(key);
        mc++;
        node_info n = vertices.get(key);
        vertices.remove(key);
        return n;
    }

    //This function based on two specific nodes deletes the edge between them if exists.
    @Override
    public void removeEdge(int node1, int node2)
    {
        if (edges.get(node1).get(node2)!=null)
        {
            edges.get(node1).remove(node2);
            edges.get(node2).remove(node1);
            edgesize--;
        }
    }

    //Obviously will return the size of graph that represents the vertices/nodes....
    @Override
    public int nodeSize() {
        return vertices.size();
    }

    // Obviously will return the number of edges on the Weighted Graph
    @Override
    public int edgeSize() {
        return edgesize;
    }


    //This function returns the number of changes that occurred in the Weighted Graph.
    @Override
    public int getMC() {
        return mc;
    }


    // Needed this Implementation over Java's Equals in order to correctly check Save/Load Function
    // Graphs will be called Equal if they have the same vertices with the same edges and weight.
    // If Equal shall return True, Else - False.
    @Override
    public boolean equals(Object obj)
    {
        weighted_graph tempGraph = (weighted_graph) obj;
        Iterator<node_info> aNode = getV().iterator();
        Iterator<node_info> bNode = tempGraph.getV().iterator();
        while (aNode.hasNext() && bNode.hasNext())
        {
            node_info a =aNode.next();
            node_info b =bNode.next();
            if (a.equals(b) == false)
            {
                return false;
            }
            if (getV(a.getKey()) !=null)
            {
                if (tempGraph.getV(b.getKey()) ==null)
                {
                    return false;
                }
                Iterator<node_info> neighborsA = getV(a.getKey()).iterator();
                Iterator<node_info> neighborsB = tempGraph.getV(b.getKey()).iterator();
                while (neighborsA.hasNext()&& neighborsB.hasNext())
                {
                    node_info neiA= neighborsA.next();
                    node_info neiB= neighborsB.next();
                    if (neiA.equals(neiB) == false)
                    {
                        return false;
                    }
                    if (getEdge(a.getKey(), neiA.getKey()) != tempGraph.getEdge(b.getKey(), neiB.getKey()))
                    {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    /**
     * Nested Class that represents the Vertices on the Graph.
     */

    private class NodeInfo implements node_info, Comparable, Serializable
    {
        /**
         * Node Consists of 3 parts -
         * 1. Key- the node ID
         * 2. Info- Parameter for the Graph Algo that represents if a node has been visited or not
         * 3.Tag- Represents weight from a certain node
         */
        private int key;
        private String info;
        private double tag;

        // Node Constructor
        public NodeInfo(int k)
        {
            key= k;
        }

        // Will return the Key of the Node
        @Override
        public int getKey() {
            return key;
        }

        // Will return the Info of the Node
        @Override
        public String getInfo() {
            return info;
        }

        // Will set the Info of the Node
        @Override
        public void setInfo(String s)
        {
            info =s;
        }

        // A simple function to retrieve the node's TAG
        @Override
        public double getTag() {
            return tag;
        }

        // A simple function to update the node's TAG
        @Override
        public void setTag(double t) {
            tag=t;
        }


        // The function Compares two node Tags, Will return:
        //-1 the given nodes tag is greater then our nodes tag
        //1 the given nodes tag is smaller then our nodes tag
        //0 if they are the same
        @Override
        public int compareTo(Object o)
        {
            node_info n = (node_info) o;
            if (n.getTag()>tag)
                return -1;
            if (n.getTag()<tag)
                return 1;
            return 0;
        }


        // This function will check if two nodes are the same and if so will return true, else will return false.
        //Used as part of the equals implementation to help with save/load.
        @Override
        public boolean equals(Object obj) {
            node_info n = (node_info) obj;
            if (key == n.getKey())
                return true;
            return false;
        }
    }

}
