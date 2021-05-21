package pattern_matching;

import java.util.ArrayList;

public class KMP {
    
    private int[] next;
    private String T;
    private String P;
    private ArrayList<Integer> S;

    public KMP(String T, String P) {
        this.next = new int[P.length()];
        this.T = T;
        this.P = P;
        this.S = new ArrayList<Integer>();
    }

    public void run() {
        init();
        printHeader();
        S.clear();
        int i = 0;
        int j = 0;
        int H = 0;
        while (i < T.length()) {
            ++H;
            //System.out.println(i + " " + j);
            if (T.charAt(i) == P.charAt(j)) {
                ++i;
                ++j;
                if (j == P.length()) {
                    S.add(i-P.length());
                    printStep(i, j, H, true);
                    j = next[P.length()-1];
                    H = 0;
                }
            } else {
                printStep(i, j, H, false);
                if (j == 0) {
                    ++i;
                } else {
                    j = next[j-1];
                }
                H = 0;
            }
        }
        printStep(i, j, H, false);
        System.out.print("S={");
        for (int k = 0; k < S.size(); ++k) {
            System.out.print(S.get(k) + ", ");
        }
        if (S.isEmpty()) System.out.print("}");
        else System.out.print("\b\b}");
        System.out.println();
    }

    private void printStep(int i, int j, int H, boolean found) {
        int charCountP = P.length();
        int charCountT = T.length();
        if (found) {
            if (i-charCountP < 10) System.out.print(" ");
            System.out.print("s=" + (i-charCountP) + "  |");
        } else {
            System.out.print("      |");
        }
        if (H < 10) System.out.print(" ");
        System.out.print(" " + H + " |");
        if (found) {
            for (int k = 0; k < i-charCountP; ++k) {
                System.out.print("    |");
            }
            for (int k = 0; k < charCountP; ++k) {
                System.out.print("  " + P.charAt(k) + " |");
            }
            for (int k = i; k < charCountT; ++k) {
                System.out.print("    |");
            }
        } else {
            for (int k = 0; k < i-j; ++k) {
                System.out.print("    |");
            }
            int l = 0;
            while (l < j && T.charAt(i-j+l) == P.charAt(l)) {
                System.out.print("  " + P.charAt(l) + " |");
                ++l;
            }
            System.out.print(" \\" + P.charAt(l) + " |");
            for (int k = i; k < charCountT-1; ++k) {
                System.out.print("    |");
            }
        }
        System.out.println();
        drawHorizontalLine();
    }

    private void init() {
        printInitHeader();
        next[0] = 0;
        int i = 0;
        int j =  1;
        while (j < P.length()) {
            printInitStep(i, j);
            if (P.charAt(i) == P.charAt(j)) {
                ++i;
                ++j;
                next[j-1] = i;
            } else {
                if (i == 0) {
                    ++j;
                    next[j-1] = 0;
                } else {
                    i = next[i-1];
                }
            }
        }
        printLastStep(i, j);
        printInitResult();
    }

    private void printInitHeader() {
        int charCount = P.length();
        System.out.print("    |    |         |");
        for (int i = 0; i < charCount; i++) {
            if (charCount < 9) System.out.print(" ");
            System.out.print(" " + (i+1) + " |");
        }
        System.out.println();
        System.out.print("  i |  j | next[j] |");
        for (int i = 0; i < charCount; i++) {
            System.out.print("  " + P.charAt(i) + " |");
        }
        System.out.println();
        drawInitHorizontalLine();
    }

    private void printInitStep(int i, int j) {
        int charCount = P.length();
        if (i < 10) System.out.print(" ");
        System.out.print(" " + i + " |");
        if (j < 10) System.out.print(" ");
        System.out.print(" " + j + " |");
        if (next[j-1] < 10) System.out.print(" ");
        System.out.print(" " + next[j-1] + "      |");
        for (int k = 0; k < charCount; ++k) {
            if (k != j) System.out.print("    |");
            else {
                if (P.charAt(i) == P.charAt(j)) {
                    System.out.print("  " + P.charAt(i) + " |");
                } else {
                    System.out.print(" \\" + P.charAt(i) + " |");
                }
            }
        }
        System.out.println();
        drawInitHorizontalLine();
    }

    private void printLastStep(int i, int j) {
        int charCount = P.length();
        if (i < 10) System.out.print(" ");
        System.out.print(" " + i + " |");
        if (j < 10) System.out.print(" ");
        System.out.print(" " + j + " |");
        if (next[j-1] < 10) System.out.print(" ");
        System.out.print(" " + next[j-1] + "      |");
        for (int k = 0; k < charCount; ++k) {
            System.out.print("    |");
        }
        System.out.println();
        drawInitHorizontalLine();
    }

    private void printInitResult() {
        int charCount = P.length();
        System.out.println("\nResult:");
        System.out.print("---------|");
        for (int i = 0; i < charCount; ++i) {
            System.out.print("----|");
        }
        System.out.println();
        System.out.print("P[j]=    |");
        for (int i = 0; i < charCount; ++i) {
            System.out.print("  " + P.charAt(i) + " |");
        }
        System.out.println();
        System.out.print("---------|");
        for (int i = 0; i < charCount; ++i) {
            System.out.print("----|");
        }
        System.out.println();
        System.out.print("j=       |");
        for (int i = 1; i <= charCount; ++i) {
            if (i < 10) System.out.print(" ");
            System.out.print(" " + i + " |");
        }
        System.out.println();
        System.out.print("---------|");
        for (int i = 0; i < charCount; ++i) {
            System.out.print("----|");
        }
        System.out.println();
        System.out.print("next[j]= |");
        for (int i = 0; i < charCount; ++i) {
            if (next[i] < 10) System.out.print(" ");
            System.out.print(" " + next[i] + " |");
        }
        System.out.println();
        System.out.print("---------|");
        for (int i = 0; i < charCount; ++i) {
            System.out.print("----|");
        }
        System.out.println();
    }
    
    private void printHeader() {
        int charCount = T.length();
        System.out.println("\nH = Hasonlitasok szama");
        drawHorizontalLine();
        System.out.print("i=    |");
        System.out.print("  H |");
        for (int i = 1; i <= charCount; ++i) {
            if (i < 10) System.out.print(" ");
            System.out.print(" " + i + " |");
        }
        System.out.println();
        drawHorizontalLine();
        System.out.print("T[i]= |");
        System.out.print("    |");
        for (int i = 1; i <= charCount; ++i) {
            System.out.print("  " + T.charAt(i-1) + " |");
        }
        System.out.println();
        drawHorizontalLine();
    }

    private void drawInitHorizontalLine() {
        int charCount = P.length();
        System.out.print("----|----|---------|");
        for (int i = 0; i < charCount; i++) {
            System.out.print("----|");
        }
        System.out.println();
    }

    private void drawHorizontalLine() {
        int charCount = T.length();
        System.out.print("------|");
        System.out.print("----|");
        for (int i = 1; i <= charCount; ++i) {
            System.out.print("----|");
        }
        System.out.println();
    }
}
