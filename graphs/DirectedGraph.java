package graphs;

public class DirectedGraph extends Graph {
    
    public DirectedGraph(int size) {
        super(size);
    }

    public DirectedGraph(boolean abcNodes) {
        super(abcNodes);
    }

    public DirectedGraph(int size, boolean abcNodes) {
        super(size, abcNodes);
    }

    @Override
    public void addEdge(Node from, Node to, int weight) {
        from.addEdge(to, weight);
    }

    @Override
    public void addEdgeByName(String from, String to, int weight) {
        getNodeByName(from).addEdge(getNodeByName(to), weight);
    }
}
