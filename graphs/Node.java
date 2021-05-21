package graphs;

import java.util.ArrayList;

enum Color {
    WHITE, GRAY, BLACK
}

public class Node {

    private String name;
    private int d;
    private int f;
    private int e;
    private Node pi;
    private Color color;
    private ArrayList<Edge> edges;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public String getName() {return name;}

    public void setD(int d) {this.d = d;}
    public int getD() {return d;}
    
    public void setF(int f) {this.f = f;}
    public int getF() {return f;}

    public void setE(int e) {this.e = e;}
    public int getE() {return e;}

    public void setPi(Node pi) {this.pi = pi;}
    public Node getPi() {return pi;}
    
    public void setColor(Color color) {this.color = color;}
    public Color getColor() {return color;}

    public ArrayList<Edge> getEdges() {return edges;}

    public void addEdge(Node to, int weight) {
        edges.add(new Edge(this, to, weight));
    }
}