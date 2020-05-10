package class01;

public class InsertionSort {

    public static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i ++) {
            for (int j = i - 1; j >= 0 && arr[j+1] > arr[j]; j --) {
                swap(arr, j, j + 1);
            }
        }

    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {2,3,5,1,2,5,6,2,1,2,3};
        insertionSort(arr1);
        for (int i : arr1) System.out.print(i + ", ");
        System.out.println();
    }

}
