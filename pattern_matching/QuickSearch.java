package pattern_matching;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSearch {
    
    private ArrayList<Character> shiftChar;
    private ArrayList<Integer> shiftInt;
    private String T;
    private String P;
    private ArrayList<Integer> S;
    private int H;

    public QuickSearch(String T, String P) {
        this.shiftChar = new ArrayList<>();
        this.shiftInt = new ArrayList<>();
        this.T = T;
        this.P = P;
        this.S = new ArrayList<Integer>();
        this.H = 0;
    }

    public void run() {
        initShift();
        System.out.println();
        printHeader();
        int s = 0;
        while (s + P.length() <= T.length()) {
            printStep(s);
            int j = 0;
            while (j < P.length() && T.charAt(s+j) == P.charAt(j)) {
                ++H;
                ++j;
            }
            if (j == P.length()) S.add(s);
            else ++H;
            if (s + P.length() < T.length())
                s+= shiftInt.get(shiftChar.indexOf(T.charAt(s + P.length()))); 
            else break;
        }
        System.out.print("S={");
        for (int k = 0; k < S.size(); ++k) {
            System.out.print(S.get(k) + ", ");
        }
        if (S.isEmpty()) System.out.print("}");
        else System.out.print("\b\b}");
        System.out.println();
        System.out.println("H = " + H);
    }

    public void initShift() {
        for (int i = 0; i < T.length(); ++i) {
            if (!shiftChar.contains(T.charAt(i))) {
                shiftChar.add(T.charAt(i));
                shiftInt.add(P.length()+1);
            }
        }
        Collections.sort(shiftChar);
        printInitHeader();
        for (int i = 0; i < P.length(); ++i) {
            int index = shiftChar.indexOf(Character.valueOf(P.charAt(i)));
            shiftInt.set(index, P.length() - i);
            printInitStep(i, index);
        }
        printInitResult();
    }

    private void printInitHeader() {
        System.out.print("       |");
        for (int i = 0; i < shiftChar.size(); ++i) {
            System.out.print("  " + shiftChar.get(i) + " |");
        }
        System.out.println();
        System.out.print("       |");
        for (int i = 0; i < shiftInt.size(); ++i) {
            System.out.print("  " + shiftInt.get(i) + " |");
        }
        System.out.println();
        System.out.print("-------|");
        for (int i = 0; i < shiftInt.size(); ++i) {
            System.out.print("----|");
        }
        System.out.println();
    }

    private void printInitStep(int i, int index) {
        System.out.print(P.charAt(i) + " ");
        if (i < 10) System.out.print(" ");
        System.out.print((i+1) + "   |");
        for (int k = 0; k < shiftInt.size(); ++k) {
            if (index == k) {
                if (shiftInt.get(index) < 10) System.out.print(" ");
                System.out.print(" " + shiftInt.get(index) + " |");
            } else {
                System.out.print("    |");
            }
        }
        System.out.println();
    }

    private void printInitResult() {
        System.out.print("-------|");
        for (int i = 0; i < shiftInt.size(); ++i) {
            System.out.print("----|");
        }
        System.out.println();
        System.out.print("SHIFT  |");
        for (int i = 0; i < shiftInt.size(); ++i) {
                if (shiftInt.get(i) < 10) System.out.print(" ");
                System.out.print(" " + shiftInt.get(i) + " |");
        }
        System.out.println();
        System.out.print("-------|");
        for (int i = 0; i < shiftInt.size(); ++i) {
            System.out.print("----|");
        }
        System.out.println();
    }

    private void printHeader() {
        for (int i = 1; i <= T.length(); ++i) {
            if (i < 10) System.out.print(" ");
            System.out.print(" " + i + " |");
        }
        System.out.println();
        for (int i = 0; i < T.length(); ++i) {
            System.out.print("  " + T.charAt(i) + " |");
        }
        System.out.println();
    }

    private void printStep(int s) {
        for (int i = 0; i < s; ++i) {
            System.out.print("    |");
        }
        for (int i = 0; i < P.length(); ++i) {
            if (T.charAt(s+i) != P.charAt(i))
                System.out.print(" \\" + P.charAt(i) + " |");
            else
                System.out.print("  " + P.charAt(i) + " |");
        }
        for (int i = s + P.length(); i < T.length(); ++i) {
            System.out.print("    |");
        }
        System.out.println();
    }
}
