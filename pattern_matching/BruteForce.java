package pattern_matching;

import java.util.ArrayList;

public class BruteForce {
    
    private String T;
    private String P;
    private ArrayList<Integer> S;

    public BruteForce(String T, String P) {
        this.T = T;
        this.P = P;
        this.S = new ArrayList<>(); 
    }

    public void run() {
        S.clear();
        for (int i = 0; i < T.length(); ++i) {
            System.out.print(T.charAt(i) + " ");
        }
        System.out.println();
        for (int s = 0; s <= T.length()-P.length(); ++s) {
            int j = 0;
            for (int i = 0; i < s; ++i) System.out.print("  ");
            while (j < P.length()) {
                System.out.print(P.charAt(j) + " ");
                if (!(T.charAt(s+j) == P.charAt(j))) break;
                ++j;
            }
            System.out.println();
            if (j == P.length()) {
                S.add(s);
            }
        }
        System.out.print("S: {");
        for (int i = 0; i < S.size(); ++i) {
            System.out.print(S.get(i) + ", ");
        }
        if (!S.isEmpty()) System.out.println("\b\b}");
        else System.out.println("}");
    }
}