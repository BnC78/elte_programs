package graphs;

import java.util.ArrayList;

public abstract class Graph {

    public final String[] abc = {"!", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private ArrayList<Node> nodes;
    private boolean abcNodes = false;

    public Node getNode(int index) {return nodes.get(index);}
    public ArrayList<Node> getNodes() {return nodes;}

    public String[] getABC() {return abc;}
    public void setABC(boolean abcNodes) {this.abcNodes = abcNodes;}
    public boolean isABC() {return abcNodes;}
    
    public Graph() {
        nodes = new ArrayList<>();
    }

    public Graph(int size) {
        nodes = new ArrayList<>(size);
    }

    public Graph(boolean abcNodes) {
        nodes = new ArrayList<>();
        this.abcNodes = abcNodes;
    }

    public Graph(int size, boolean abcNodes) {
        nodes = new ArrayList<>(size);
        this.abcNodes = abcNodes;
    }
    
    public void addNode(Node node) {
        nodes.add(node);
    }

    public Node getNodeByName(String name) {
        for (int i = 0; i < nodes.size(); ++i) {
            if (nodes.get(i).getName().equals(abcNodes ? abc[Integer.parseInt(name)] : name)) {
                return nodes.get(i);
            }
        }
        throw new NullPointerException();
    }

    public void createNodes(int count) {
        for (Integer i = 1; i <= count; ++i) {
            if (abcNodes)
                nodes.add(new Node(abc[i]));
            else
                nodes.add(new Node(i.toString()));
        }
    }

    public abstract void addEdge(Node node1, Node node2, int weight);
    public abstract void addEdgeByName(String node1, String node2, int weight);
}
