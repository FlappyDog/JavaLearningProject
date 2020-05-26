package main.java.adv01_class01;

import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，返回每一次滑出状况的最大值
 * 例如，arr=[4,3,5,4,3,3,6,7]，W=3
 * 返回：[5,5,5,4,6,7]
 */
public class SlidingWindowMaxArray {

    static int[] slidingWindowMaxArray(int[] arr, int w) {
        LinkedList<Integer> queue = new LinkedList<>();

        int[] result = new int[arr.length - w + 1];
        int index = 0;

        for (int i = 0; i < arr.length; i ++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);

            if (queue.getFirst() == i - w) {
                queue.pollFirst();
            }

            if (i >= w - 1) {
                result[index++] = arr[queue.peekFirst()];
            }

        }

        return result;

    }

    /**
     * 返回每一次滑出状况的最大值
     */
    static int[] minSlidingWindow(int[] arr, int w) {
        LinkedList<Integer> minQueue = new LinkedList<>();

        int[] result = new int[arr.length - w + 1];
        int index = 0;

        for (int i = 0; i < arr.length; i ++) {
            while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[i]) {
                minQueue.pollLast();
            }
            minQueue.addLast(i);

            if (minQueue.peekFirst() == i - w) {
                minQueue.pollFirst();
            }

            if (i + 1 >= w) {
                result[index ++] = arr[minQueue.peekFirst()];
            }

        }

        return result;

    }

    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        for (int i : slidingWindowMaxArray(arr, 3)) {
            System.out.print(i + ", ");
        }
        System.out.println();

        for (int i : minSlidingWindow(arr, 3)) {
            System.out.print(i + ", ");
        }
    }

}
