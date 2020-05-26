package main.java.adv01_class01;

import java.util.Stack;

public class MaxSumMinProduct {

    static int getSumMinProduct(int[] array) {

        int result = Integer.MIN_VALUE;

        int[] sum = new int[array.length];

        sum[0] = array[0];
        for (int i = 1; i < array.length; i ++) {
            sum[i] = sum[i-1] + array[i];
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < array.length; i ++) {
            while (!stack.isEmpty() && array[stack.peek()] >= array[i]) {
                int minValueIndex = stack.pop();
                int lSum = stack.isEmpty() ? 0 : sum[stack.peek()];
                int rSum = sum[i-1];
                result = Math.max(result, array[minValueIndex] * (rSum - lSum));
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int minValueIndex = stack.pop();
            int lSum = stack.isEmpty() ? 0 : sum[stack.peek()];
            int rSum = sum[sum.length-1];
            result = Math.max(result, array[minValueIndex] * (rSum - lSum));

        }

        return result;

    }

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 5;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != getSumMinProduct(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }

}
