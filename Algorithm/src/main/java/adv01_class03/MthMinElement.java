package main.java.adv01_class03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在数组中，判断第K-th小的数
 */
public class MthMinElement {

    /**
     * 大根堆实现
     * 复杂度O(N*logN)
     */
    static int minKthMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < arr.length; i ++) {
            if (i < k) {
                queue.offer(arr[i]);
            } else if (arr[i] < queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        return queue.peek();
    }

    /**
     * 快排（荷兰国旗问题）
     * 复杂度O(N)
     */
    static int minKthQuickSort(int[] arr, int L, int R, int K) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int)(Math.random() * (R - L + 1))];
        int[] boundaries = partition(arr, L, R, pivot);
        if (K < boundaries[0]) {
            return minKthQuickSort(arr, L, boundaries[0]-1, K);
        } else if (K > boundaries[1]) {
            return minKthQuickSort(arr, boundaries[1]+1, R, K);
        } else {
            return arr[boundaries[0]];
        }
    }

    /**
     * bfprt 算法
     * 跟荷兰国旗求解思路类似，只是在找pivot的时候有了优化，使得每次固定至少淘汰30%的数据
     */
    static int minKthBfprt(int[] arr, int L, int R, int K) {
        if (L == R) {
            return arr[L];
        }
        int pivot = findMidianOfMedians(arr, L, R);
        int[] boundaries = partition(arr, L, R, pivot);
        if (K < boundaries[0]) {
            return minKthQuickSort(arr, L, boundaries[0]-1, K);
        } else if (K > boundaries[1]) {
            return minKthQuickSort(arr, boundaries[1]+1, R, K);
        }else {
            return arr[boundaries[0]];
        }
    }

    static int findMidianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] medians = new int[size/5 + offset];
        for (int team = 0; team < medians.length; team ++) {
            // 找到每个大小为5的数组的中位数
            int l = team * 5;
            int r = Math.min((team + 1) * 5 - 1, R);
            Arrays.sort(arr, l, r + 1);
            medians[team] = arr[l + ((r-l) >> 1)];
        }
        return minKthBfprt(medians, 0, medians.length - 1, medians.length / 2);
    }

    static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur ++;
            }
        }
        return new int[]{less+1, more-1};
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;

        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKthMaxHeap(Arrays.copyOf(arr, arr.length), k);
            int ans2 = minKthQuickSort(Arrays.copyOf(arr, arr.length), 0, arr.length - 1, k - 1);
            int ans3 = minKthBfprt(Arrays.copyOf(arr, arr.length), 0, arr.length - 1, k - 1);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("test finish");

    }


}
