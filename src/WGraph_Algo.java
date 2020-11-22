package ex1.src;

import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class WGraph_Algo implements weighted_graph_algorithms, Serializable {
    // Class to represent the algorithms applicable on a Weighted Graph
    // Consists of a Hashmap that contains neighbors and a weighted graph.

    public static HashMap<Integer, Double> neighbors;
    weighted_graph finalgraph;
    // Simple initiate.....
    @Override
    public void init(weighted_graph g) {
        finalgraph = g;
    }
    // Returns the Weighted Graph
    @Override
    public weighted_graph getGraph() {
        return finalgraph;
    }

    // Function preforms a DEEP COPY of Weighted Graph
    @Override
    public weighted_graph copy() {

        weighted_graph copy = new WGraph_DS();
        for (node_info node : finalgraph.getV()) {
            Collection<node_info> neiS = finalgraph.getV(node.getKey());
            for (node_info neNode : neiS) {
                neighbors.put(neNode.getKey(), finalgraph.getEdge(node.getKey(), neNode.getKey()));
            }
            copy.addNode(node.getKey());
        }
        neighbors = null;
        return copy;
    }
    //Function will check if there is a valid path from every node to another node
    //If so will return True, Else False.
    @Override
    public boolean isConnected() {
        if (finalgraph.nodeSize() != 0) {
            Collection<node_info> vs = finalgraph.getV();
            for (node_info n : vs) {
                n.setTag(0);
            }
            Iterator<node_info> tmp = finalgraph.getV().iterator();
            node_info n = tmp.next();
            n.setTag(1);
            Queue<node_info> q = new LinkedBlockingQueue<node_info>();
            q.add(n);
            while (!q.isEmpty()) {
                node_info p = q.poll();
                for (node_info nei : finalgraph.getV(p.getKey())) {
                    if (nei.getTag() == 0) {
                        nei.setTag(1);
                        q.add(nei);
                    }
                }
            }
            for (node_info test : finalgraph.getV()) {
                if (test.getTag() == 0) {
                    for (node_info v : finalgraph.getV()) {
                        v.setTag(0);
                    }
                    return false;
                }
            }
            return true;
        }
        return true;
    }
    // Function based on DS we find and return the shortest path between our Start node till the End node
    // If no path is available then we return -1
    @Override
    public double shortestPathDist(int src, int dest) {
        Collection<node_info> vs = finalgraph.getV();
        for (node_info n : vs)
            n.setTag(Double.MAX_VALUE);
        Queue<node_info> q = new LinkedBlockingQueue<node_info>();
        node_info s = finalgraph.getNode(src);
        s.setTag(0);
        q.add(s);
        while (!q.isEmpty()) {
            node_info u = q.poll();
            Collection<node_info> arrN = finalgraph.getV(u.getKey());
            for (node_info v : arrN) {
                if (u.getTag() + finalgraph.getEdge(u.getKey(), v.getKey()) < v.getTag()) {
                    v.setTag(u.getTag() + finalgraph.getEdge(u.getKey(), v.getKey()));
                    q.remove(v);
                    q.add(v);
                }
            }
        }
        double des = finalgraph.getNode(dest).getTag();
        if (des == Double.MAX_VALUE)
            return -1;
        else
            return des;
    }
    //Like the function about we find the shortest path based on DS but here we return a list of nodes that is the shortest path
    //between SRC-DEST
    //If no path exists then we return null
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        Collection<node_info> vs = finalgraph.getV();
        for (node_info n : vs)
            n.setTag(Double.MAX_VALUE);
        int[] prev = new int[finalgraph.nodeSize()];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        Queue<node_info> q = new LinkedBlockingQueue<node_info>();
        node_info s = finalgraph.getNode(src);
        s.setTag(0);
        q.add(s);
        while (!q.isEmpty()) {
            node_info u = q.poll();
            Collection<node_info> arrN = finalgraph.getV(u.getKey());
            for (node_info v : arrN) {
                if (u.getTag() + finalgraph.getEdge(u.getKey(), v.getKey()) < v.getTag()) {
                    v.setTag(u.getTag() + finalgraph.getEdge(u.getKey(), v.getKey()));
                    q.remove(v);
                    q.add(v);
                    prev[v.getKey() % finalgraph.nodeSize()] = u.getKey();
                }
            }
        }

        if (finalgraph.getNode(dest).getTag() == Double.MAX_VALUE)
            return null;
        else {
            List<node_info> l = new LinkedList<node_info>();
            int run = dest;
            while (run != -1) {
                l.add(0, finalgraph.getNode(run));
                run = prev[run % finalgraph.nodeSize()];
            }
            return l;
        }
    }
    //Saves this weighted (undirected) graph to the given file name
    // Will return T if successfully saved, else return F

    @Override
    public boolean save(String file) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream obout = new ObjectOutputStream(fileOut);
            obout.writeObject(finalgraph);
            obout.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    // A function to load a graph to this graph algorithm.
    // Will return T if successfully loaded, else return F
    @Override
    public boolean load(String file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream obout = new ObjectInputStream(fileIn);
            finalgraph = (WGraph_DS) obout.readObject();
            obout.close();

            return true;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
