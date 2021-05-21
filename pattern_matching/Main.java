package pattern_matching;

public class Main {
    
    public static void main(String[] args) {
        //BruteForce
        /*
        BruteForce BF = new BruteForce("abaabcdab", "ab");
        BF.run();
        //*/

        //KMP
        /*
        KMP kmp = new KMP("ABBABABABABBABABABAA", "BABABAB");
        kmp.run();
        //*/

        //QuickSearch
        /*
        QuickSearch qs = new QuickSearch("DBDGEDBCADBCAHDAGDBAABC", "DBAABC");
        qs.run();
        //*/

        //LZW
        /*
        Item[] codeTable = {
            new Item("a", 1),
            new Item("b", 2),
            new Item("c", 3)
        };
        LZW lzw = new LZW(codeTable);
        int[] codedMsg = {1,2,4,3,4,8};
        lzw.decompress(codedMsg);
        //lzw.compress("TETETETEDETETTET", 0);
        //*/
    }
}
