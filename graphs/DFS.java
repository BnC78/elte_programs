package graphs;

import helper.Helper;

public class DFS {
    
    private Graph graph;
    private int time;

    public DFS(Graph graph) {
        this.graph = graph;
    }

    public void run() {
        for (Node u : graph.getNodes()) {
            u.setColor(Color.WHITE);
            u.setPi(null);
            u.setD(0);
            u.setF(0);
        }
        this.time = 0;
        for (Node u : graph.getNodes()) {
            if (u.getColor() == Color.WHITE) {
                visit(graph, u);
            }
        }
        printDF();
        printPi();
        printEdgeTypes();
    }

    public void visit(Graph g, Node u) {
        u.setColor(Color.GRAY);
        this.time = this.time + 1;
        u.setD(time);
        for (Edge neighbour : u.getEdges()) {
            Node v = neighbour.getTo();
            if (v.getColor() == Color.WHITE) {
                neighbour.setType("F");
                v.setPi(u);
                visit(g, v);
            } else if (v.getColor() == Color.BLACK && u.getD() < v.getD()) {
                neighbour.setType("E");
            } else if (v.getColor() == Color.GRAY) {
                neighbour.setType("V");
            } else if (v.getColor() == Color.BLACK && u.getD() > v.getD()) {
                neighbour.setType("K");
            }
        }
        this.time = this.time + 1;
        u.setF(time);
        u.setColor(Color.BLACK);
    }

    private void printDF() {
        System.out.print(" Node |");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 10) + "|");
        }
        System.out.println();
        System.out.print("------|");
        for (int i = 0; i < graph.getNodes().size(); ++i) {
            System.out.print("----------|");
        }
        System.out.println();
        System.out.print(" d/f  |");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getD() + "/" + node.getF(), 10) + "|");
        }
        System.out.println("\n");
    }

    private void printPi() {
        System.out.print(" Node |");
        for (Node node : graph.getNodes()) {
            System.out.print(Helper.alignMiddle(node.getName(), 7) + "|");
        }
        System.out.println();
        System.out.print("------|");
        for (int i = 0; i < graph.getNodes().size(); ++i) {
            System.out.print("-------|");
        }
        System.out.println();
        System.out.print("  Pi  |");
        for (Node node : graph.getNodes()) {
            Node pi = node.getPi();
            if (pi != null)
                System.out.print(Helper.alignMiddle(node.getPi().getName(), 7) + "|");
            else
            System.out.print(Helper.alignMiddle("null", 7) + "|");
        }
        System.out.println("\n");
    }

    private void printEdgeTypes() {
        for (Node u : graph.getNodes()) {
            for (Edge e : u.getEdges()) {
                System.out.println("(" + u.getName() + "," + e.getTo().getName() + "): " + e.getType());
            }
        }
        System.out.println("\n");
    }
}
