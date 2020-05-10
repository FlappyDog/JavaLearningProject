package class01;

public class MergingSort {

    static void mergingSort(int[] arr) {
        mergingSort(arr, 0, arr.length - 1);
    }

    static void mergingSort(int[] arr, int left, int right) {
        if (left >= right) {
            return ;
        }

        int mid = (left + right) >> 1;

        mergingSort(arr, left, mid);
        mergingSort(arr, mid + 1, right);

        int[] sorted = new int[right - left + 1];

        int leftIdx = left, rightIdx = mid + 1;
        int idx = 0;

        while(leftIdx <= mid && rightIdx <= right) {
            if (arr[leftIdx] >= arr[rightIdx]) {
                sorted[idx ++] = arr[leftIdx ++];
            } else {
                sorted[idx ++] = arr[rightIdx ++];
            }
        }

        while (leftIdx <= mid) {
            sorted[idx ++] = arr[leftIdx ++];
        }

        while (rightIdx <= right) {
            sorted[idx ++] = arr[rightIdx ++];
        }

        for (int i = 0; i < sorted.length; i ++) {
            arr[left + i] = sorted[i];
        }

    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,4,5,6,3,1,23};
        mergingSort(arr);
        Util.printArr(arr);
    }
}
