package graphs;

public class UndirectedGraph extends Graph {
    
    public UndirectedGraph(int size) {
        super(size);
    }

    public UndirectedGraph(boolean abcNodes) {
        super(abcNodes);
    }

    public UndirectedGraph(int size, boolean abcNodes) {
        super(size, abcNodes);
    }

    @Override
    public void addEdge(Node node1, Node node2, int weight) {
        node1.addEdge(node2, weight);
        node2.addEdge(node1, weight);
    }

    @Override
    public void addEdgeByName(String node1, String node2, int weight) {
        getNodeByName(node1).addEdge(getNodeByName(node2), weight);
        getNodeByName(node2).addEdge(getNodeByName(node1), weight);
    }
}
