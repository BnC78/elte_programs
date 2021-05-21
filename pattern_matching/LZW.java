package pattern_matching;

import java.util.ArrayList;

public class LZW {

    private ArrayList<Item> D;

    public LZW(String in) {
        D = new ArrayList<>();
        int code = 1;
        for (int i = 0; i < in.length(); ++i) {
            String currChar = String.valueOf(in.charAt(i));
            if (!dictionaryContainsString(D, currChar)) {
                D.add(new Item(currChar, code++));
            }
        }
    }

    public LZW(Item[] D) {
        this.D = new ArrayList<>();
        for (int i = 0; i < D.length; ++i) {
            this.D.add(new Item(D[i].string, D[i].code));
        }
    }

    private static boolean dictionaryContainsString(ArrayList<Item> dict, String string) {
        for (int i = 0; i < dict.size(); ++i) {
            if (dict.get(i).string.equals(string))
                return true;
        }
        return false;
    }

    private static int code(ArrayList<Item> dict, String string) {
        for (int i = 0; i < dict.size(); ++i) {
            if (dict.get(i).string.equals(string))
                return dict.get(i).code;
        }
        return 0;
    }

    private static String string(ArrayList<Item> dict, int code) {
        for (int i = 0; i < dict.size(); ++i) {
            if (dict.get(i).code == code)
                return dict.get(i).string;
        }
        return " | ";
    }
    
    public void compress(String in, int MAXCODE) {
        if (MAXCODE == 0)
            MAXCODE = Integer.MAX_VALUE;
        int code = D.size() + 1;
        String out = "";
        String s = String.valueOf(in.charAt(0));
        for (int i = 1; i < in.length(); ++i) {
            String c = String.valueOf(in.charAt(i));
            if (dictionaryContainsString(D, s + c)) {
                s += c;
            } else {
                out += code(D, s);
                if (code <= MAXCODE) {
                    D.add(new Item(s + c, code++));
                }
                s = c;
            }
        }
        for (int i = 0; i < D.size(); ++i) {
            System.out.println(D.get(i).string + "\t| " + D.get(i).code);
        }
        out = out + code(D, s);
        System.out.println("\n" + out);
    }

    public void decompress(int[] in) {
        int code = D.size() + 1;
        String s = string(D, in[0]);
        String out = s;
        for (int i = 1; i < in.length; ++i) {
            int k = in[i];
            String t = "";
            if (k < code) {
                t = string(D, k);
                out += t;
                D.add(new Item(s + t.charAt(0), code));
            } else {
                t = s + s.charAt(0);
                out += t;
                D.add(new Item(t, k));
            }
            s = t;
            ++code;
        }
        for (int i = 0; i < D.size(); ++i) {
            System.out.println(D.get(i).string + "\t| " + D.get(i).code);
        }
        System.out.println("\n" + out);
    }
}