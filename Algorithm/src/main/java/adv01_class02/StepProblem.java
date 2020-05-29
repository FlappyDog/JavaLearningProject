package main.java.adv01_class02;

public class StepProblem {
    static int step1(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        return step1(n - 1) + step1(n - 2);
    }

    static int step2(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        int tmp = 0, prev = 1, result = 2;
        for (int i = 3; i <= n; i ++) {
            tmp = result;
            result = prev + result;
            prev = tmp;
        }

        return result;
    }

    static int step3(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        int[][] base = {{1,1},{1,0}};
        int[][] result = FibonacciProblem.matrixPow(base, n - 2);
        return 2 * result[0][0] + result[1][0];
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(step1(n));
        System.out.println(step2(n));
        System.out.println(step3(n));
    }
}
