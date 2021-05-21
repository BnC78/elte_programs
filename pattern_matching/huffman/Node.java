package pattern_matching.huffman;

public class Node {

    public char character;
    public int frequency;
    public Node leftNode;
    public Node rightNode;

    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public Node(char character, int frequency, Node leftNode, Node rightNode) {
        this.character = character;
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public boolean isLeafNode() {
        return this.leftNode == null && this.rightNode == null;
    }

    @Override public String toString() {
        return "Char: " + character + " | Freq: " + frequency;
    }
}