package graphs;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import helper.Helper;

public class BFS {

    private Graph graph;
    private ArrayList<Integer> prevD;
    private ArrayList<Node> prevPi;
    private Node start;

    public BFS(Graph graph) {
        this.graph = graph;
    } 
    
    public BFS(Graph graph, Node start) {
        this.graph = graph;
        this.start = start;
    } 

    public void setStartNode(Node start) {this.start = start;}
    public Node getStartNode() {return start;}
    
    public void run() throws InterruptedException {
        int nodeCount = graph.getNodes().size();
        prevD = new ArrayList<>(nodeCount);
        prevPi = new ArrayList<>(nodeCount);

        for (int i = 0; i < nodeCount; i++) {
            graph.getNode(i).setD(Integer.MAX_VALUE);
            graph.getNode(i).setPi(null);
            graph.getNode(i).setColor(Color.WHITE);
            prevD.add((Integer) graph.getNode(i).getD());
            prevPi.add(graph.getNode(i).getPi());
        }
        start.setD(0);
        start.setColor(Color.GRAY);

        ArrayBlockingQueue<Node> q = new ArrayBlockingQueue<>(nodeCount);
        q.put(start);
        start.setColor(Color.GRAY);

        printHeader(graph);

        while (!q.isEmpty()) {
            Node u = q.take();
            for (Edge edge : u.getEdges()) {
                Node v = edge.getTo();
                if (!q.contains(v) && v.getColor() == Color.WHITE) {
                    v.setD(u.getD() + 1);
                    v.setPi(u);
                    v.setColor(Color.GRAY);
                    q.put(v);
                }
            }
            u.setColor(Color.BLACK);
            printStep(q, u);
        }

        printSolution();
    }

    private void printHeader(Graph graph) {
        int nodeCount = graph.getNodes().size();
        System.out.println("Kiterjesztett |" + Helper.alignMiddle("csucsok d ertekei", 4 * nodeCount - 1) + "|" + Helper.alignMiddle("Sor", 2 * nodeCount + 1) + "|" + Helper.alignMiddle("csucsok pi ertekei", 4 * nodeCount - 1) + "|");
        System.out.print("              |");
        for (int i = 0; i < nodeCount; i++) {
            System.out.print("----");
        }
        System.out.print("\b|" + Helper.alignMiddle("", 2 * nodeCount + 1) + "|");
        for (int i = 0; i < nodeCount; i++) {
            System.out.print("----");
        }
        System.out.println("\b|");

        System.out.print(Helper.alignMiddle("csucs", 14) + "|");
        for (int i = 0; i < nodeCount; ++i) {
            String currentName = graph.getNode(i).getName();
            if (currentName.length() == 1)
                System.out.print(" " + currentName + " |");
            else if (currentName.length() == 2)
                System.out.print(currentName + " |");
            else if (currentName.length() == 3)
                System.out.print(currentName + "|");
            else if (currentName.length() > 3)
                System.out.print("---|");
        }
        System.out.print(Helper.alignMiddle("tartalma", 2 * nodeCount + 1) + "|");
        for (int i = 0; i < nodeCount; ++i) {
            String currentName = graph.getNode(i).getName();
            if (currentName.length() == 1)
                System.out.print(" " + currentName + " |");
            else if (currentName.length() == 2)
                System.out.print(currentName + " |");
            else if (currentName.length() == 3)
                System.out.print(currentName + "|");
            else if (currentName.length() > 3)
                System.out.print("---|");
        }
        System.out.println();

        printHorizontalLine();
        System.out.print(Helper.alignMiddle("", 13) + " |");
        for (int i = 0; i < nodeCount; ++i) {
            if (graph.getNode(i) == start)
                System.out.print(" 0 |");
            else
                System.out.print(" \\ |");
        }
        String startNodeName = start.getName();
        System.out.print(Helper.alignMiddle("<" + startNodeName + ">", 2 * nodeCount + 1) + "|");
        for (int i = 0; i < nodeCount; ++i)
            System.out.print(" 0 |");
        System.out.println();
    }

    private void printStep(ArrayBlockingQueue<Node> q, Node processedNode) {
        printHorizontalLine();
        int nodeCount = graph.getNodes().size();
        String processedNodetext = processedNode.getName() + ", d:" + processedNode.getD();
        System.out.print(Helper.alignMiddle(processedNodetext, 14) + "|");
        for (int i = 0; i < nodeCount; ++i) {
            if (graph.getNode(i).getD() != prevD.get(i)) {
                int currentD = graph.getNode(i).getD();
                if ( currentD >= 0 && currentD < 10 )
                    System.out.print(" " + currentD + " |");
                else if ( currentD >= -9 && currentD < 100 )
                    System.out.print(currentD + " |");
                else if ( currentD >= -99 && currentD < 1000 )
                    System.out.print(currentD + "|");
                else
                    System.out.print(" _ |");
                prevD.set(i, graph.getNode(i).getD());
            } 
            else 
                System.out.print("   |");
        }

        String queue = "<";
        for ( Node node : q ) {
            queue += node.getName() + ";";
        }
        if (q.size() != 0) queue += "\b";
        queue += ">";
        if (q.size() != 0) 
            System.out.print(Helper.alignMiddle(queue, 2 * nodeCount + 3) + "|");
        else
            System.out.print(Helper.alignMiddle(queue, 2 * nodeCount + 1) + "|");

        
        for (int i = 0; i < nodeCount; ++i) {
            if (graph.getNode(i).getPi() != prevPi.get(i)) {
                String currentPiName = graph.getNode(i).getPi().getName();
                if (currentPiName.length() == 1)
                    System.out.print(" " + currentPiName + " |");
                else if (currentPiName.length() == 2)
                    System.out.print(currentPiName + " |");
                else if (currentPiName.length() == 3)
                    System.out.print(currentPiName + "|");
                else if (currentPiName.length() > 3)
                    System.out.print(" _ |");
                prevPi.set(i, graph.getNode(i).getPi());
            } 
            else 
                System.out.print("   |");
        }
        System.out.println();
    }

    private void printSolution() {
        printHorizontalLine();
        int nodeCount = graph.getNodes().size();
        System.out.print("              |");
        for (int i = 0; i < nodeCount; ++i) {
            int currentD = graph.getNode(i).getD();
            if ( currentD >= 0 && currentD < 10 )
                System.out.print(" " + currentD + " |");
            else if ( currentD >= -9 && currentD < 100 )
                System.out.print(currentD + " |");
            else if ( currentD >= -99 && currentD < 1000 )
                System.out.print(currentD + "|");
            else
                System.out.print(" \\ |");
        }
        System.out.print(Helper.alignMiddle("", 2 * nodeCount + 1) + "|");
        
        for (int i = 0; i < nodeCount; ++i) {
            String currentPiName = graph.getNode(i).getPi() == null ? "0" : graph.getNode(i).getPi().getName();
            if (currentPiName.length() == 1)
                System.out.print(" " + currentPiName + " |");
            else if (currentPiName.length() == 2)
                System.out.print(currentPiName + " |");
            else if (currentPiName.length() == 3)
                System.out.print(currentPiName + "|");
            else if (currentPiName.length() > 3)
                System.out.print(" _ |");
        }
        System.out.println();
    }

    private void printHorizontalLine() {
        int nodeCount = graph.getNodes().size();
        System.out.print("--------------|");
        for (int i = 0; i < nodeCount; i++) {
            System.out.print("----");
        }
        System.out.print("\b|");
        for (int i = 0; i < 2 * nodeCount + 1; i++) {
            System.out.print("-");
        }
        System.out.print("|");
        for (int i = 0; i < nodeCount; i++) {
            System.out.print("----");
        }
        System.out.println("\b|");
    }
}
