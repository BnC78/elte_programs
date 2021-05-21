package graphs;

import java.util.ArrayList;

import helper.Helper;

public class FloydWarshall {

    private Graph graph;
    private int[][] A;
    private int[][] D;
    private int[][] Pi;
    
    public FloydWarshall(Graph graph) {
        int nodeCount = graph.getNodes().size();
        this.graph = graph;
        this.A = new int[nodeCount + 1][nodeCount + 1];
        this.D = new int[nodeCount + 1][nodeCount + 1];
        this.Pi = new int[nodeCount + 1][nodeCount + 1];

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
                D[i][j] = A[i][j];
                if (i != j && A[i][j] < Integer.MAX_VALUE/2) {
                    Pi[i][j] = i;
                } else {
                    Pi[i][j] = 0;
                }

            }
        }
        printTable(0);
        for (int k = 1; k <= nodeCount; ++k) {
            for (int i = 0; i <= nodeCount; ++i) {
                for (int j = 0; j <= nodeCount; ++j) {
                    if (D[i][j] > D[i][k] + D[k][j]) {
                        D[i][j] = D[i][k] + D[k][j];
                        Pi[i][j] = Pi[k][j];
                    }
                }
            }
            printTable(k);
        }
    }

    private void printTable(int locked) {
        int nodeCount = graph.getNodes().size();
        System.out.print(Helper.alignMiddle("D(" + locked + ")", 7) + "||");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 5) + "|");
        }
        System.out.print(Helper.alignMiddle("", 10));
        System.out.print(Helper.alignMiddle("Pi(" + locked + ")", 7) + "||");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 5) + "|");
        }
        System.out.println();
        drawHorizontalLine("=");
        ArrayList<Node> nodes = graph.getNodes();
        for (int i = 1; i <= nodeCount; ++i) {
            System.out.print(Helper.alignMiddle(nodes.get(i-1).getName(), 7) + "||");
            for (int j = 1; j <= nodeCount; ++j) {
                if (D[i][j] < 10000)
                    System.out.print(Helper.alignMiddle("" + D[i][j], 5) + "|");
                else
                    System.out.print(Helper.alignMiddle("\\", 5) + "|");
            }
            System.out.print(Helper.alignMiddle("", 10));
            System.out.print(Helper.alignMiddle(nodes.get(i-1).getName(), 7) + "||");
            for (int j = 1; j <= nodeCount; ++j) {
                System.out.print(Helper.alignMiddle("" + Pi[i][j], 5) + "|");
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
        System.out.print(Helper.alignMiddle("", 10));
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
