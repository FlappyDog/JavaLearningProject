package main.java.adv01_class03;

import java.util.ArrayList;

public class TreeEqual {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    static boolean containsTree1(TreeNode big, TreeNode small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isTreeEqual(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    static boolean isTreeEqual(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.val != head2.val) {
            return false;
        }
        return isTreeEqual(head1.left, head2.left) && isTreeEqual(head1.right, head2.right);
    }

    static boolean containsTree2(TreeNode t1, TreeNode t2) {
        return KMP(pres(t1, new StringBuilder()), pres(t2, new StringBuilder())) != -1;
    }

    static String pres(TreeNode head, StringBuilder sb) {
        if (head == null) {
            sb.append("_none_");
        } else {
            sb.append(String.format("_%d_", head.val));
            pres(head.left, sb);
            pres(head.right, sb);
        }
        return sb.toString();
    }

    static int KMP(String str1, String str2) {
        if (str2.length() > str1.length()) {
            return -1;
        }
        int[] next = getNextArr(str2);
        int idx1 = 0, idx2 = 0;
        while (idx1 < str1.length() && idx2 < str2.length()) {
            if (str1.charAt(idx1) == str2.charAt(idx2)) {
                idx1 ++;
                idx2 ++;
            } else if (next[idx2] == -1) {
                idx1 ++;
            } else {
                idx2 = next[idx2];
            }
        }
        return idx2 == str2.length() ? idx1 - idx2 : -1;
    }

    static int[] getNextArr(String str) {
        int[] next = new int[str.length()+2];
        next[0] = -1;
        next[1] = 0;
        int idx = 2;
        int cn = 0;
        while (idx < str.length()) {
            if (str.charAt(idx-1) == str.charAt(cn)) {
                next[idx++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[idx++] = 0;
            }
        }

        return next;
    }


    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static int[] getNextArray(String ms) {
        if (ms.length() == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[ms.length()];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (ms.charAt(i - 1) == ms.charAt(cn)) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static boolean containsTree3(TreeNode big, TreeNode small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        ArrayList<String> b = preSerial(big);
        ArrayList<String> s = preSerial(small);
        String[] str = new String[b.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = b.get(i);
        }

        String[] match = new String[s.size()];
        for (int i = 0; i < match.length; i++) {
            match[i] = s.get(i);
        }
        return getIndexOf(str, match) != -1;
    }

    public static ArrayList<String> preSerial(TreeNode head) {
        ArrayList<String> ans = new ArrayList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(TreeNode head, ArrayList<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.val));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    public static int getIndexOf(String[] str1, String[] str2) {
        if (str1 == null || str2 == null || str1.length < 1 || str1.length < str2.length) {
            return -1;
        }
        int x = 0;
        int y = 0;
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length) {
            if (isEqual(str1[x], str2[y])) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(String[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (isEqual(ms[i - 1], ms[cn])) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }

    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            TreeNode big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            TreeNode small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            boolean ans3 = containsTree3(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println(pres(big, new StringBuilder()));
                System.out.println(pres(small, new StringBuilder()));
                int[] arr1 = getNextArray(pres(small, new StringBuilder()));
                int[] arr2 = getNextArr(pres(small, new StringBuilder()));
                containsTree2(big, small);
                containsTree1(big, small);
            }
        }
        System.out.println("test finish!");

    }
}
