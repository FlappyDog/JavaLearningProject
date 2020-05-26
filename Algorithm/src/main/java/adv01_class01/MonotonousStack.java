package main.java.adv01_class01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个整型数组arr，(不)包含重复数据
 * 对于数组中的每一个元素，找到左侧/右侧最近的比该元素小的数
 */
public class MonotonousStack {

    static int[][] getNearLessNoRepeat(int[] array) {
        Stack<Integer> stack = new Stack<>();

        int[][] result = new int[array.length][2];
        for (int i = 0; i < array.length; i ++) {
            result[i][0] = -1;
            result[i][1] = -1;
        }

        for (int i = 0; i < array.length; i ++) {
            while (!stack.isEmpty() && array[stack.peek()] > array[i]) {
                int target = stack.pop();
                result[target][1] = i;
                result[target][0] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int peek = stack.pop();
            result[peek][0] = stack.isEmpty() ? -1 : stack.peek();
        }

        return result;

    }

    static int[][] getNearLess(int[] array) {

        Stack<LinkedList<Integer>> stack = new Stack();
        int[][] result = new int[array.length][2];

        for (int i = 0; i < array.length; i ++) {
            while (!stack.isEmpty() && array[stack.peek().get(0)] > array[i]) {
                for (int target : stack.pop()) {
                    result[target][0] = stack.isEmpty() ? -1 : stack.peek().getLast();
                    result[target][1] = i;
                }
            }

            if (stack.isEmpty() || array[stack.peek().getFirst()] != array[i]) {
                stack.push(new LinkedList<>());
            }
            stack.peek().addLast(i);
        }

        while (!stack.isEmpty()) {
            for (int target : stack.pop()) {
                result[target][0] = stack.isEmpty() ? -1 : stack.peek().getLast();
                result[target][1] = -1;
            }
        }

        return result;

    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

//        int[] array = {4,1,0,2,5,6,3};
//        for (int[] res : getNearLessNoRepeat(array)) {
//            System.out.println(res[0] + ", " + res[1]);
//        }

        int size = 10;
        int max = 20;
        int testTimes = 20000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
    }

}
