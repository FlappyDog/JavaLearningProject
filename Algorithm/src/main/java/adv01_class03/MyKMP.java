package main.java.adv01_class03;

public class MyKMP {

    static int KMP(String str, String match) {

        if (str.length() < match.length()) {
            return -1;
        }

        int[] next = getNextArray(match);
        int i = 0, j = 0;

        while (i < str.length() && j < match.length()) {
            if (str.charAt(i) == match.charAt(j)) {
                i ++;
                j ++;
            } else if (next[j] == -1) {
                i ++;
            } else {
                j = next[j];
            }
        }

        return j == match.length() ? i - j : -1;
    }

    /**
     * generate next array
     * @param match
     * @return
     */
    static int[] getNextArray(String match) {

        int[] next = new int[match.length()+2];

        next[0] = -1;
        next[1] = 0;

        int i = 2;
        int cn = 1;
        while (i < match.length()) {
            if (match.charAt(i) == match.charAt(cn)) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abccaabccf";
        String match = "abccs";

        System.out.println(KMP(str, match));
    }

}
