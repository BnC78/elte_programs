package graphs;

public class Edge {
    
    private Node from;
    private Node to;
    private int weight;
    private String type;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Node getFrom() {return from;}
    public Node getTo() {return to;}
    public int getWeight() {return weight;}
    public void setType(String type) {this.type = type;}
    public String getType() {return type;}
}
