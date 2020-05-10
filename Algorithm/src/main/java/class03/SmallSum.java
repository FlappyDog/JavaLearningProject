package class03;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 */
public class SmallSum {

    static int smallSum1(int[] arr) {
        return mergeSortSmallSum1(arr, 0, arr.length - 1);
    }

    private static int mergeSortSmallSum1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int smallSum = 0;

        int M = L + ((R - L) >> 1);
        smallSum += mergeSortSmallSum1(arr, L, M);
        smallSum += mergeSortSmallSum1(arr, M + 1, R);
        smallSum += merge(arr, L, M, R);
        return smallSum;
    }

    static int smallSum2(int[] arr) {
        int smallSum = 0;
        int mergeSize = 1;

        while (mergeSize < arr.length) {
            int L = 0;
            while (L < arr.length) {
                int M = L + mergeSize - 1;
                if (M > arr.length) {
                    break;
                }
                int R = Math.min(M + mergeSize, arr.length - 1);
                smallSum += merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > arr.length / 2) {
                break;
            }
            mergeSize <<= 1;
        }

        return smallSum;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        if (L == R) {
            return 0;
        }

        int smallSum = 0;

        int[] helper = new int[R-L+1];

        int p1 = L, p2 = M +1, helperIdx = 0;
        while (p1 <= M && p2 <= R) {
            if (arr[p1] < arr[p2]) {
                smallSum += arr[p1] * (R - p2 + 1);
                helper[helperIdx++] = arr[p1++];
            } else {
                helper[helperIdx++] = arr[p2++];
            }
        }

        while (p1 <= M) {
            helper[helperIdx++] = arr[p1++];
        }

        while (p2 <= R) {
            helper[helperIdx++] = arr[p2++];
        }

        for (int i = 0; i < helper.length; i ++) {
            arr[L + i] = helper[i];
        }

        return smallSum;

    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};
        System.out.println(smallSum1(arr));

        int[] arr1 = {1,3,4,2,5};
        System.out.println(smallSum2(arr1));
    }

}
