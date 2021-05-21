package pattern_matching.huffman;

public class Main {
    
    public static void main(String[] args) {

        String msg;
        if (args.length == 0) msg = "AZABBRAKADABRAA";
        else msg = args[0];
        
        Huffman huff = new Huffman(msg);

        String encodedMsg = huff.encode(msg);

        System.out.println("Encoded message: " + encodedMsg + " | Number of bits: " + encodedMsg.length());

        String decodedMsg = huff.decode(encodedMsg);

        System.out.println("Decoded message: " + decodedMsg);

        // Kódtábla kiíratása
        // huff.printCodeTable();

    }
}