package class01;

public class BinarySearch {

    static boolean binarySearch(int[] arr, int target) {
        return binarySearch(arr, 0, arr.length - 1, target) != -1;
    }

    static int binarySearch(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = left + ((right - left) >> 1);

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearch(arr, left, mid - 1, target);
        } else {
            return binarySearch(arr, mid + 1, right, target);
        }
    }

    public static void main(String[] args) {

        int[] sorted = {1,3,5,7,34,66,77,99};

        System.out.println(binarySearch(sorted, 19));
        System.out.println(binarySearch(sorted, 3));
        System.out.println(binarySearch(sorted, 7));
        System.out.println(binarySearch(sorted, 100));
    }

}
