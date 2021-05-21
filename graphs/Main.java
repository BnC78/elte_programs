package graphs;

public class Main {

    public static void main(String[] args) {

        //Directed Graph BFS
        /*
        Graph ugBFS = new DirectedGraph(8);
        ugBFS.createNodes(8);
        ugBFS.addEdgeByName("2", "1", 1);
        ugBFS.addEdgeByName("2", "4", 1);
        ugBFS.addEdgeByName("2", "6", 1);
        ugBFS.addEdgeByName("2", "7", 1);
        ugBFS.addEdgeByName("3", "2", 1);
        ugBFS.addEdgeByName("4", "8", 1);
        ugBFS.addEdgeByName("5", "3", 1);
        ugBFS.addEdgeByName("5", "8", 1);
        ugBFS.addEdgeByName("6", "8", 1);

        BFS bfs = new BFS(ugBFS, ugBFS.getNodeByName("5"));
        try {
            bfs.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\n");
        //*/

        //Directed Graph DFS
        /*
        Graph ugDFS = new DirectedGraph(6, true);
        ugDFS.createNodes(6);
        ugDFS.addEdgeByName("1", "2", 2);
        ugDFS.addEdgeByName("1", "4", 1);
        ugDFS.addEdgeByName("1", "5", 5);
        ugDFS.addEdgeByName("2", "5", 3);
        ugDFS.addEdgeByName("2", "6", 2);
        ugDFS.addEdgeByName("3", "2", 3);
        ugDFS.addEdgeByName("3", "5", 2);
        ugDFS.addEdgeByName("3", "6", 1);
        ugDFS.addEdgeByName("4", "5", 2);
        ugDFS.addEdgeByName("5", "6", 3);
        
        DFS dfs = new DFS(ugDFS);
        dfs.run();
        
        System.out.println("\n\n\n");
        //*/
    
        //Undirected Graph Prime
        /*
        Graph udPrime = new UndirectedGraph(6, true);
        udPrime.createNodes(6);
        udPrime.addEdgeByName("1", "2", 2);
        udPrime.addEdgeByName("1", "4", 2);
        udPrime.addEdgeByName("2", "3", 1);
        udPrime.addEdgeByName("2", "4", 3);
        udPrime.addEdgeByName("2", "5", 5);
        udPrime.addEdgeByName("2", "6", 4);
        udPrime.addEdgeByName("3", "6", 3);
        udPrime.addEdgeByName("4", "5", 1);
        udPrime.addEdgeByName("5", "6", 6);

        Prime udprime = new Prime(udPrime, udPrime.getNodeByName("6"));
        udprime.run();
        //*/

        //Directed Graph Dijkstra
        /*
        Graph dgDijk = new DirectedGraph(6, true);
        dgDijk.createNodes(6);
        dgDijk.addEdgeByName("1", "2", 2);
        dgDijk.addEdgeByName("1", "4", 1);
        dgDijk.addEdgeByName("1", "5", 5);
        dgDijk.addEdgeByName("2", "5", 3);
        dgDijk.addEdgeByName("2", "6", 2);
        dgDijk.addEdgeByName("3", "2", 3);
        dgDijk.addEdgeByName("3", "5", 2);
        dgDijk.addEdgeByName("3", "6", 1);
        dgDijk.addEdgeByName("4", "5", 2);
        dgDijk.addEdgeByName("5", "6", 3);

        Dijkstra dijkstra = new Dijkstra(dgDijk, dgDijk.getNodeByName("1"));
        dijkstra.run();
        //*/
    
        //Directed Graph FloydWarshall
        /*
        Graph dgFW = new DirectedGraph(4, false);
        dgFW.createNodes(8);
        dgFW.addEdgeByName("1", "2", 4);
        dgFW.addEdgeByName("1", "7", 7);
        dgFW.addEdgeByName("1", "8", 4);
        dgFW.addEdgeByName("2", "3", 9);
        dgFW.addEdgeByName("2", "6", 6);
        dgFW.addEdgeByName("2", "7", 8);
        dgFW.addEdgeByName("2", "8", 1);
        dgFW.addEdgeByName("3", "5", 10);
        dgFW.addEdgeByName("5", "3", 8);
        dgFW.addEdgeByName("5", "4", 6);
        dgFW.addEdgeByName("5", "6", 5);
        dgFW.addEdgeByName("6", "5", 6);
        dgFW.addEdgeByName("7", "2", 4);
        dgFW.addEdgeByName("7", "6", 7);
        dgFW.addEdgeByName("8", "3", 3);

        FloydWarshall dgfw = new FloydWarshall(dgFW);
        dgfw.run();
        //*/

        //Directed Graph Transitive
        /*
        Graph dgTr = new DirectedGraph(4, false);
        dgTr.createNodes(4);
        dgTr.addEdgeByName("1", "2", 1);
        dgTr.addEdgeByName("2", "4", 1);
        dgTr.addEdgeByName("3", "2", 1);
        dgTr.addEdgeByName("4", "3", 1);
        Transitive dgtr = new Transitive(dgTr);
        dgtr.run();
        //*/

        //Directed Graph Bellman-Ford
        /*
        Graph dgBF = new DirectedGraph(7, true);
        dgBF.createNodes(7);
        dgBF.addEdgeByName("1", "2", 4);
        dgBF.addEdgeByName("1", "4", 1);
        dgBF.addEdgeByName("1", "6", 2);
        dgBF.addEdgeByName("3", "2", 3);
        dgBF.addEdgeByName("3", "5", 3);
        dgBF.addEdgeByName("4", "2", 1);
        dgBF.addEdgeByName("4", "3", 1);
        dgBF.addEdgeByName("6", "4", 2);
        dgBF.addEdgeByName("6", "5", 1);
        dgBF.addEdgeByName("7", "5", 1);
        dgBF.addEdgeByName("7", "6", 3);

        BellmanFord dgbf = new BellmanFord(dgBF, dgBF.getNodeByName("1"));
        dgbf.run();
        //*/
    }
}
