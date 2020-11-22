package ex1.tests;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WGraphTest
{
    @Test
    void Connect ()
    {
        weighted_graph g = ourtestgraph();
        assertFalse(g.hasEdge(4,4)); // Obvious False....
        assertFalse(g.hasEdge(4,5));// Does not exist in our test graph
        assertFalse(g.hasEdge(5,4));// Neither should this
        g.connect(5,4,3);
        assertTrue(g.hasEdge(4,5));
        assertTrue(g.hasEdge(5,4));
    }

    @Test
    void edgeSize()
    {
        weighted_graph graph =ourtestgraph();
        int edgeSize= graph.edgeSize();

        graph.removeEdge(0,1);//first edge deleted
        graph.removeEdge(0,2);//second
        graph.removeEdge(0,3);//third
        assertEquals(edgeSize-3, graph.edgeSize());
        WGraph_DS graph2 = new WGraph_DS();
        graph2.addNode(1);
        // No edge Graph
        assertEquals(0, graph2.edgeSize());

    }
    @Test
    void isConnected ()
    {
        WGraph_DS graph = new WGraph_DS();
        for (int i=0; i<10;i++)
        {
            graph.addNode(i);
        }
        WGraph_Algo g =new WGraph_Algo();
        g.init(graph);// In order to use Algo....
        assertFalse(g.isConnected());// I have not yet connected any nodes, therefore obvious false.
        for (int i=1; i<10; i++)// For to connect all nodes....
        {
            graph.connect(0,i,1.1);
        }
        g.init(graph);
        assertTrue(g.isConnected());// Because of For above this is T
        graph.removeEdge(0,1);// No possible Path to node 1
        g.init(graph);
        assertFalse(g.isConnected());
    }

    @Test
    void shortestPathD ()
    {

        WGraph_Algo g =new WGraph_Algo();
        g.init(ourtestgraph());

        double d =g.shortestPathDist(0,9);
        assertEquals(4.1, d);

    }

    @Test
    void shortestPath ()
    {
        WGraph_Algo g =new WGraph_Algo();
        g.init(ourtestgraph());

        List<node_info> get= g.shortestPath(0,9);
        int [] check ={0,2,4,9};
        int i=0;
        for (node_info n:get)
        {
            assertEquals(n.getKey(),check[i]);
            i++;
        }
    }

    @Test
    void saveLoad()
    {
        WGraph_DS graph = createGraph(255,700,1);
        WGraph_Algo g1 = new WGraph_Algo();
        g1.init(graph);
        assertTrue(g1.save("graph.obj"));// Saved Successfully
        assertTrue(g1.load("graph.obj"));// Loaded Successfully
        WGraph_Algo g2 = new WGraph_Algo();
        assertTrue(g2.save("graph.obj"));//Saved Successfully
        assertTrue(g2.load("graph.obj"));// Loaded Successfully
    }

    private WGraph_DS createGraph (int v, int e, int seed)// Taken from master code...
    {
        WGraph_DS graph = new WGraph_DS();
        Random rand = new Random(seed);
        for (int i=0; i<v;i++)
        {
            graph.addNode(i);
        }
        while (graph.edgeSize()<e)
        {
            int a = rand.nextInt(v);
            int b = rand.nextInt(v);
            double w = rand.nextDouble()*10;
            graph.connect(a,b,w);
        }
        return graph;
    }
    //
    private WGraph_DS ourtestgraph ()// Builds our Crazy Test Graph
    {
        WGraph_DS graph = new WGraph_DS();
        for (int i=0; i<11;i++)
        {
            graph.addNode(i);
        }
        graph.connect(0,1,1);
        graph.connect(0,2,2);
        graph.connect(0,3,3);

        graph.connect(1,6,17);
        graph.connect(1,7,1);
        graph.connect(2,4,1);
        graph.connect(2, 5,10);
        graph.connect(3,10,100);
        graph.connect(4,9,1.1);
        graph.connect(5,8,10);
        graph.connect(6,10,2);
        graph.connect(7,9,3);
        graph.connect(8,4,10);
        graph.connect(8,10,30);
        graph.connect(9,5,10);
        graph.connect(9,3,10);
        graph.connect(10,9,10);

        return graph;
    }
}