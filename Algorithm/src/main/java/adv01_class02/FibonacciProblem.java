package main.java.adv01_class02;

public class FibonacciProblem {
    /**
     * O(N) solution
     * recursive
     */
    static int fibonacciProblem(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }

        return fibonacciProblem(n-1) + fibonacciProblem(n-2);
    }

    /**
     * O(N) solution
     * non-recursive
     */
    static int fibonacciProblem2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        int prev = 1, result = 1, tmp = 0;
        for (int i = 3; i <= n; i ++) {
            tmp = result;
            result = prev + tmp;
            prev = tmp;
        }
        return result;
    }

    /**
     * O(logN)
     * F(N) = F(N-1) + F(N - 2)
     * ==>
     * |F(N), F(N-1)| = |F(N-1), F(N-2)| * 二阶矩阵
     * ==>
     * |F(N), F(N-1)| = |F(2), F(1)| * 二阶矩阵的n-2次幂
     *
     */
    static int fibonacciProblem3(int n) {
        int[][] base = {{1,1},{1,0}};
        int[][] result = matrixPow(base, n - 2);

        return result[0][0] + result[1][0];

    }

    /**
     * * 求一个数的n次幂
     *  可以用logN的复杂度求出
     *  N => 二进制表示，tmp初始为1，res初始为单位矩阵
     *  每次右移一位，并且tmp *= tmp
     *  如果该二进制为1，则
     *      *  res *= tmp
     * 否则，跳过
     */
    static int[][] matrixPow(int[][] m, int n) {
        int[][] result = new int[m.length][m.length];
        for (int i = 0; i < m.length; i ++) {
            result[i][i] = 1;
        }

        int[][] tmp = m;
        for (; n != 0; n >>= 1) {
            if ((n & 1) != 0) {
                result = matrixProduc(result, tmp);
            }
            tmp = matrixProduc(tmp, tmp);
        }

        return result;

    }

    static int[][] matrixProduc(int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i ++) {
            for (int j = 0; j < m2[0].length; j ++) {
                for (int k = 0; k < m1[0].length; k ++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(fibonacciProblem(n));
        System.out.println(fibonacciProblem2(n));
        System.out.println(fibonacciProblem3(n));
        System.out.println("===");


    }
}
