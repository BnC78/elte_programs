package graphs;

import java.util.ArrayList;

import helper.Helper;

public class BellmanFord {
    
    private Graph graph;
    private Node start;

    public BellmanFord(Graph graph, Node start) {
        this.graph = graph;
        this.start = start;
    }

    public void run() {
        int nodeCount = graph.getNodes().size();
        ArrayList<Node> queue = new ArrayList<>(nodeCount);
        for (Node node : graph.getNodes()) {
            node.setD(Integer.MAX_VALUE/2);
            node.setE(0);
            node.setPi(null);
        }
        printHeader();
        start.setD(0);

        queue.add(start);
        while (queue.size() > 0) {
            Node u = queue.remove(0);
            for (Edge edge : u.getEdges()) {
                Node to = edge.getTo();
                if (to.getD() > u.getD() + edge.getWeight()) {
                    to.setD(u.getD() + edge.getWeight());
                    to.setE(u.getE() + 1);
                    to.setPi(u);
                    queue.add(to);
                }
            }
            printStep(queue, u);
        }
        printResult();
    }

    private void printHeader() {
        int nodeCount = graph.getNodes().size();
        System.out.print(Helper.alignMiddle("d;e", 6 * nodeCount - 1) + "|");
        System.out.print(Helper.alignMiddle("", 19) + "|");
        System.out.print(Helper.alignMiddle("", 3 * nodeCount) + "|");
        System.out.print(Helper.alignMiddle("pi", 6 * nodeCount - 1) + "|");
        System.out.print(Helper.alignMiddle("", 5) + "|");
        System.out.println();
        for (int i = 0; i < 6 * nodeCount - 1; ++i) {
            System.out.print("-");
        }
        System.out.print("|");
        System.out.print(Helper.alignMiddle("kiterjesztes: d;e", 19) + "|");
        System.out.print(Helper.alignMiddle("Q", 3 * nodeCount) + "|");
        for (int i = 0; i < 6 * nodeCount - 1; ++i) {
            System.out.print("-");
        }
        System.out.print("|");
        System.out.println("-----|");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 5) + "|");
        }
        System.out.print(Helper.alignMiddle("", 19) + "|");
        System.out.print(Helper.alignMiddle("", 3 * nodeCount) + "|");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 5) + "|");
        }
        System.out.println("menet|");

        drawHorizontalLine();

        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node == start ? "0;0" : "\\", 5) + "|");
        }
        System.out.print(Helper.alignMiddle("-", 19) + "|");
        System.out.print(Helper.alignMiddle("<" + start.getName() + ">", 3 * nodeCount) + "|");
        for (int i = 0; i < nodeCount; ++i) {
            System.out.print(Helper.alignMiddle("/", 5) + "|");
        }
        System.out.print(Helper.alignMiddle("", 5) + "|");
        System.out.println();
    }

    private void printStep(ArrayList<Node> queue, Node currentNode) {
        int nodeCount = graph.getNodes().size();
        drawHorizontalLine();
        for (Node node : graph.getNodes()) {
            if (queue.contains(node))
                System.out.print(Helper.alignMiddle(node.getD() + ";" + node.getE(), 5) + "|");
            else
                System.out.print(Helper.alignMiddle("", 5) + "|");
        }
        System.out.print(Helper.alignMiddle(currentNode.getName() + " : " + currentNode.getD() + ";" + currentNode.getE(), 19) + "|");
        String queueText = "<";
        for (Node node : queue) {
            queueText += node.getName() + ",";
        }
        if (queue.size() != 0) queueText += "\b";
        queueText += ">";
        if (queue.size() != 0)
            System.out.print(Helper.alignMiddle(queueText, 3 * nodeCount + 2) + "|");
        else
        System.out.print(Helper.alignMiddle(queueText, 3 * nodeCount) + "|");
        for (Node node : graph.getNodes()) {
            if (queue.contains(node))
                System.out.print(Helper.alignMiddle(node.getPi().getName(), 5) + "|");
            else
                System.out.print(Helper.alignMiddle("", 5) + "|");
        }
        System.out.print(Helper.alignMiddle("" + currentNode.getE(), 5) + "|");
        System.out.println();
    }

    private void printResult() {
        int nodeCount = graph.getNodes().size();
        drawHorizontalLine();
        for (Node node : graph.getNodes()) {
            if (node.getD() < Integer.MAX_VALUE/2)
                System.out.print(Helper.alignMiddle("" + node.getD(), 5) + "|");
            else
                System.out.print(Helper.alignMiddle("\\", 5) + "|");
        }
        System.out.print(Helper.alignMiddle("", 19) + "|");
        System.out.print(Helper.alignMiddle("", 3 * nodeCount) + "|");
        for (Node node : graph.getNodes()) {
            if (node.getPi() != null)
                System.out.print(Helper.alignMiddle("" + node.getPi().getName(), 5) + "|");
            else
                System.out.print(Helper.alignMiddle("0", 5) + "|");
        }
        System.out.print(Helper.alignMiddle("", 5) + "|");
        System.out.println();
    }

    private void drawHorizontalLine() {
        int nodeCount = graph.getNodes().size();
        for (int i = 0; i < nodeCount; i++) {
            System.out.print("-----|");
        }
        for (int i = 0; i < 19; i++) {
            System.out.print("-");
        }
        System.out.print("|");
        for (int i = 0; i < 3 * nodeCount; i++) {
            System.out.print("-");
        }
        System.out.print("|");
        for (int i = 0; i < nodeCount; i++) {
            System.out.print("-----|");
        }
        System.out.println("-----|");
    }
}
