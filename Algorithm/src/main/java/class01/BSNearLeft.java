package class01;

/**
 * 给定一个sorted的数组，和一个值value
 * 找数组中 >= value 的最左数值
 * 没有，则返回-1
 */

public class BSNearLeft {

    static int findLeftMost1(int[] arr, int value) {
        int left = 0, right = arr.length - 1;
        int idx = -1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (arr[mid] >= value) {
                right = mid - 1;
                idx = mid;
            } else {
                left = mid + 1;
            }
        }

        return idx != -1 ? arr[idx] : -1;

    }

    static int findLeftMost(int[] arr, int value) {
        if (value > arr[arr.length-1]) {
            return -1;
        }

        int pos = binarySearch(arr, 0, arr.length - 1, value);

        return arr[pos] >= value ? arr[pos] : arr[pos + 1];
    }

    static int binarySearch(int[] arr, int left, int right, int value) {
        if (left == right) {
            return left;
        }

        int mid = left + ((right - left) >> 1);

        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] > value) {
            return binarySearch(arr, left, mid - 1, value);
        } else {
            return binarySearch(arr, mid + 1, right, value);
        }

    }

    public static void main(String[] args) {
        int[] sorted = {1,3,5,7,9,10,11,15,16,17};

        System.out.println(findLeftMost(sorted, 7));
        System.out.println(findLeftMost(sorted, 8));
        System.out.println(findLeftMost(sorted, 14));
        System.out.println(findLeftMost(sorted, -1));

        System.out.println("===================================");

        System.out.println(findLeftMost1(sorted, 7));
        System.out.println(findLeftMost1(sorted, 8));
        System.out.println(findLeftMost1(sorted, 14));
        System.out.println(findLeftMost1(sorted, -1));
    }

}
