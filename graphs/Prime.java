package graphs;

import java.util.PriorityQueue;

import helper.Helper;

public class Prime {
    
    private Graph graph;
    private Node start;

    public Prime(Graph graph) {
        this.graph = graph;
    }
    
    public Prime(Graph graph, Node start) {
        this.graph = graph;
        this.start = start;
    }

    public void setStartNode(Node start) {this.start = start;}
    public Node getStartNode() {return start;}

    public void run() {
        PriorityQueue<Node> minQ = new PriorityQueue<>(graph.getNodes().size(), new NodeComparator());
        for (Node u : graph.getNodes()) {
            u.setD(Integer.MAX_VALUE);
            u.setPi(null);
        }
        start.setD(0);

        minQ.addAll(graph.getNodes());

        printHeader(minQ);

        while (!minQ.isEmpty()) {
            Node u = minQ.poll();
            for (Edge neigbour : u.getEdges()) {
                Node v = neigbour.getTo();
                if (minQ.contains(v) && v.getD() > neigbour.getWeight()) {
                    minQ.remove(v);
                    v.setD(neigbour.getWeight());
                    v.setPi(u);
                    minQ.add(v);
                }
            }
            printStep(minQ, u);
            if (!minQ.isEmpty()) minQ.add(minQ.poll());
        }
        printResult();
    }

    private void printHeader(PriorityQueue<Node> minQ) {
        int nodeCount = graph.getNodes().size();
        System.out.print("      |");
        for (int i = 0; i < 7 * nodeCount; ++i) {
            System.out.print(" ");
        }
        System.out.print("|");
        System.out.print(Helper.alignMiddle("(d,pi)", 10 * nodeCount - 1) + "|");
        System.out.println();

        System.out.print(" Kor  |");
        System.out.print(Helper.alignMiddle("minQ", 7 * nodeCount) + "|");
        for (int i = 0; i < 10 * nodeCount - 1; ++i) {
            System.out.print("-");
        }
        System.out.println("|");
        
        System.out.print("      |");
        for (int i = 0; i < 7 * nodeCount; ++i) {
            System.out.print(" ");
        }
        System.out.print("|");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 9) + "|");
        }
        System.out.println();

        printHorizontalLine();

        System.out.print(" INIT |");
        String queue = "<";
        for (Node node : minQ)
            queue += "(" + node.getName() + "," + (node.getD() == Integer.MAX_VALUE ? "\\" : node.getD()) + "),";
        queue += "\b>";
        System.out.print(Helper.alignMiddle(queue, 7 * nodeCount + 2) + "|");
        for (Node node : minQ)
            System.out.print(Helper.alignMiddle("(" + (node.getD() == Integer.MAX_VALUE ? "\\" : node.getD()) + "," + (node.getPi() == null ? "/" : node.getPi()) + ")",9) + "|");
        System.out.println();
    }

    private void printStep(PriorityQueue<Node> minQ, Node currentNode) {
        int nodeCount = graph.getNodes().size();
        PriorityQueue<Node> q = new PriorityQueue<>(minQ);

        printHorizontalLine();

        System.out.print(Helper.alignMiddle(currentNode.getName(), 6) + "|");
        String queue = "<";
        while (!q.isEmpty()) {
            Node node = q.poll();
            queue += "(" + node.getName() + "," + (node.getD() == Integer.MAX_VALUE ? "\\" : node.getD()) + "),";
        }
        if (minQ.size() != 0)
            queue += "\b";
        queue += ">";
        if (minQ.size() != 0) 
            System.out.print(Helper.alignMiddle(queue, 7 * nodeCount + 2) + "|");
        else
            System.out.print(Helper.alignMiddle(queue, 7 * nodeCount) + "|");
        for (Node node : graph.getNodes())
        {
            if (node == currentNode) {
                System.out.print(Helper.alignMiddle("KESZ", 9) + "|");
                continue;
            }
            int i = 0;
            while (i < node.getEdges().size() && !minQ.contains(node)) ++i;
            if (i < node.getEdges().size() && node.getPi() != null)
                System.out.print(Helper.alignMiddle("(" + node.getD() + "," + (node.getPi() == null ? "/" : node.getPi().getName()) + ")",9) + "|");
            else
                System.out.print(Helper.alignMiddle("", 9) + "|");
        }
        System.out.println();
    }

    private void printResult() {
        int nodeCount = graph.getNodes().size();
        System.out.print("======|");
        for (int i = 0; i < 7 * nodeCount; ++i) {
            System.out.print("=");
        }
        System.out.print("|");
        for (int i = 0; i < nodeCount; ++i) {
            System.out.print("=========|");
        }
        System.out.println();
        
        System.out.print("      |");
        for (int i = 0; i < 7 * nodeCount; ++i) {
            System.out.print(" ");
        }
        System.out.print("|");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle("(" + node.getD() + "," + (node.getPi() == null ? "/" : node.getPi().getName()) + ")",9) + "|");
        }
        System.out.println();

    }

    private void printHorizontalLine() {
        int nodeCount = graph.getNodes().size();
        System.out.print("------|");
        for (int i = 0; i < 7 * nodeCount; ++i) {
            System.out.print("-");
        }
        System.out.print("|");
        for (int i = 0; i < nodeCount; ++i) {
            System.out.print("---------|");
        }
        System.out.println();
    }
}
