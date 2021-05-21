package graphs;

import helper.Helper;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
    
    private Graph graph;
    private Node start;
    private ArrayList<Node> done;

    public Dijkstra(Graph graph, Node start) {
        this.graph = graph;
        this.start = start;
        done = new ArrayList<Node>(graph.getNodes().size());
    }

    public void run() {
        int nodeCount = graph.getNodes().size();
        PriorityQueue<Node> minQ = new PriorityQueue<Node>(nodeCount, new NodeComparator());
        for (Node node : graph.getNodes()) {
            node.setD(this.getMaxWeight());
            node.setPi(null);
        }
        start.setD(0);
        minQ.addAll(graph.getNodes());

        printHeader();

        Node u = minQ.poll();
        done.add(u);
        while (u.getD() < this.getMaxWeight() && !minQ.isEmpty()) {
            for (Edge edge : u.getEdges()) {
                if (edge.getTo().getD() > u.getD() + edge.getWeight()) {
                    minQ.remove(edge.getTo());
                    edge.getTo().setD(u.getD() + edge.getWeight());
                    edge.getTo().setPi(u);
                    minQ.add(edge.getTo());
                }
            }
            printStep(u);
            u = minQ.poll();
            done.add(u);
        }
        printStep(u);
        printResult();
    }

    private void printHeader() {
        int nodeCount = graph.getNodes().size();
        System.out.println(Helper.alignMiddle("d ertekek Q-ban", 6 * nodeCount - 1) + "|" + Helper.alignMiddle("kiterjesztett csucs:d", 25) + "|" + Helper.alignMiddle("d ertekek Q-ban", 6 * nodeCount - 1) + "|");
        for (int i = 0; i < 6 * nodeCount - 1; ++i) {
            System.out.print("-");
        }
        System.out.print("|" + Helper.alignMiddle("", 25) + "|");
        for (int i = 0; i < 6 * nodeCount - 1; ++i) {
            System.out.print("-");
        }
        System.out.println("|");

        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 5) + "|");
        }
        System.out.print(Helper.alignMiddle("", 25) + "|");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 5) + "|");
        }
        System.out.println();
        printHorizontalLine();
        for (Node node : graph.getNodes()) {
            if (node == start) 
                System.out.print(Helper.alignMiddle("0", 5) + "|");
            else
                System.out.print(Helper.alignMiddle("\\", 5) + "|");
        }
        System.out.print(Helper.alignMiddle("-", 25) + "|");
        for (int i = 0; i < nodeCount; ++i) {
            System.out.print(Helper.alignMiddle("/", 5) + "|");
        }
        System.out.println();
    }

    private void printStep(Node currentNode) {
        printHorizontalLine();
        for (Node node : graph.getNodes()) {
            if (done.contains(node))
                System.out.print(Helper.alignMiddle("", 5) + "|");
            else
                if (node.getD() == this.getMaxWeight())
                    System.out.print(Helper.alignMiddle("\\", 5) + "|");
                else
                    System.out.print(Helper.alignMiddle("" + node.getD(), 5) + "|");
        }
        System.out.print(Helper.alignMiddle(currentNode.getName() + ":" + currentNode.getD() , 25) + "|");
        for (Node node : graph.getNodes()) {
            if (done.contains(node))
                System.out.print(Helper.alignMiddle("", 5) + "|");
            else
                if (node.getD() == this.getMaxWeight() || currentNode != node.getPi())
                    System.out.print(Helper.alignMiddle("", 5) + "|");
                else
                    System.out.print(Helper.alignMiddle(node.getPi().getName(), 5) + "|");
        }
        System.out.println();

    }

    private void printResult() {
        printHorizontalLine();
        for (Node node : graph.getNodes()) {
            if (node.getD() == this.getMaxWeight())
                System.out.print(Helper.alignMiddle("\\", 5) + "|");
            else
                System.out.print(Helper.alignMiddle("" + node.getD(), 5) + "|");
        }
        System.out.print(Helper.alignMiddle("eredmeny" , 25) + "|");
        for (Node node : graph.getNodes()) {
            if (node.getPi() == null)
                System.out.print(Helper.alignMiddle("/", 5) + "|");
            else
                System.out.print(Helper.alignMiddle(node.getPi().getName(), 5) + "|");
        }
        System.out.println();
    }

    private void printHorizontalLine() {
        int nodeCount = graph.getNodes().size();
        for (int i = 0; i < nodeCount; ++i) {
            System.out.print("-----|");
        }
        for (int i = 0; i < 25; ++i) {
            System.out.print("-");
        }
        System.out.print("|");
        for (int i = 0; i < nodeCount; ++i) {
            System.out.print("-----|");
        }
        System.out.println();
    }

    private int getMaxWeight() {
        int max = 0;
        for (Node node : graph.getNodes()) {
            for (Edge edge : node.getEdges()) {
                max += edge.getWeight();
            }
        }
        return max;
    }
}
