package pattern_matching.huffman;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Huffman {

    public class Codes {
        char character;
        String code;

        Codes(char character, String code) {
            this.character = character;
            this.code = code;
        }
    }
    public Node codeTree;
    public ArrayList<Codes> codeTable;

    public Huffman(String s) {
        PriorityQueue<Node> prQueue = getFrequency(s);
        this.codeTree = buildCodeTree(prQueue);
        buildCodeTable(this.codeTable, this.codeTree, "");
    }
    
    private PriorityQueue<Node> getFrequency(String s) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            int j = 0;
            boolean found = false;
            while (j < nodes.size() && found == false) {
                if (s.charAt(i) == nodes.get(j).character) {
                    ++nodes.get(j).frequency;
                    found = true;
                } else {
                    ++j;
                }
            }
            if (j == nodes.size()) nodes.add(new Node(s.charAt(i), 1));
        }

        this.codeTable = new ArrayList<>(nodes.size());

        PriorityQueue<Node> prQueue = new PriorityQueue<>(nodes.size(), new NodeComparator());
        
        prQueue.addAll(nodes);

        return prQueue;
    }

    private Node buildCodeTree(PriorityQueue<Node> prQueue) {
        while (prQueue.size() > 1) {
            Node rightNode = prQueue.poll();
            Node leftNode = prQueue.poll();
            Node parentNode = new Node('\0', leftNode.frequency + rightNode.frequency, leftNode, rightNode);
            prQueue.add(parentNode);
        }
        return prQueue.poll();
    }

    private void buildCodeTable(ArrayList<Codes> codeTable, Node node, String code) {
        if (node.leftNode != null || node.rightNode != null) {
            buildCodeTable(codeTable, node.leftNode, code + '0');
            buildCodeTable(codeTable, node.rightNode, code + '1');
        } else {
            codeTable.add(new Codes(node.character, code));
        }
    }

    public String encode(String s) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            int j = 0;
            while (this.codeTable.get(j).character != s.charAt(i)) ++j;
            code.append(this.codeTable.get(j).code);
        }
        return code.toString();
    }

    public String decode(String code) {
        StringBuilder msg = new StringBuilder();
        int i = 0;
        while (i < code.length()) {
            Node node = this.codeTree;
            while (!node.isLeafNode()) {
                if (code.charAt(i) == '0') node = node.leftNode;
                else node = node.rightNode;
                ++i;
            }
            msg.append(node.character);
        }
        return msg.toString();
    }

    public void printCodeTable() {
        for (Codes code : this.codeTable) {
            System.out.println(code.character + " | " + code.code);
        }
    }
}
