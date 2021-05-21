package graphs;

import java.util.ArrayList;

import helper.Helper;

public class Transitive {

    private Graph graph;
    private int[][] A;
    private boolean[][] T;
    
    public Transitive(Graph graph) {
        int nodeCount = graph.getNodes().size();
        this.graph = graph;
        this.A = new int[nodeCount + 1][nodeCount + 1];
        this.T = new boolean[nodeCount + 1][nodeCount + 1];

        for (int i = 1; i <= nodeCount; ++i) {
            for (int j = 1; j <= nodeCount; ++j) {
                if (i == j)
                    this.A[i][j] = 0;
                else
                    this.A[i][j] = Integer.MAX_VALUE/2;
            }
        }
        ArrayList<Node> nodes = graph.getNodes();
        for (int i = 0; i < nodeCount; ++i) {
            for (Edge edge : nodes.get(i).getEdges()) {
                Node to = edge.getTo();
                if (graph.isABC()) {
                    int j = 0;
                    while (to.getName().equals(graph.getABC()[j])) ++j;
                    this.A[i+1][j+1] = edge.getWeight();
                } else {
                    this.A[i+1][Integer.valueOf(to.getName())] = edge.getWeight();
                }
            }
        }
    }

    public void run() {
        int nodeCount = graph.getNodes().size();
        for (int i = 1; i <= nodeCount; ++i) {
            for (int j = 1; j <= nodeCount; ++j) {
                if (A[i][j] < Integer.MAX_VALUE/2 || i == j)
                    T[i][j] = true;
                else
                    T[i][j] = false;
            }
        }
        printTable(0);
        for (int k = 1; k <= nodeCount; ++k) {
            for (int i = 0; i <= nodeCount; ++i) {
                for (int j = 0; j <= nodeCount; ++j) {
                    T[i][j] = T[i][j] || (T[i][k] && T[k][j]);
                }
            }
            printTable(k);
        }
    }

    private void printTable(int locked) {
        int nodeCount = graph.getNodes().size();
        System.out.print(Helper.alignMiddle("T(" + locked + ")", 7) + "||");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 5) + "|");
        }
        System.out.println();
        drawHorizontalLine("=");
        ArrayList<Node> nodes = graph.getNodes();
        for (int i = 1; i <= nodeCount; ++i) {
            System.out.print(Helper.alignMiddle(nodes.get(i-1).getName(), 7) + "||");
            for (int j = 1; j <= nodeCount; ++j) {
                System.out.print(Helper.alignMiddle(T[i][j] ? "1" : "0", 5) + "|");
            }
            System.out.println();
            drawHorizontalLine("-");
        }
        System.out.println();
    }

    private void drawHorizontalLine(String ch) {
        int nodeCount = graph.getNodes().size();
        for (int i = 0; i < 7; ++i) {
            System.out.print(ch);
        }
        System.out.print("||");
        for (int i = 0; i < nodeCount; ++i) {
            for (int j = 0; j < 5; ++j) {
                System.out.print(ch);
            }
            System.out.print("|");
        }
        System.out.println();
    }
}
