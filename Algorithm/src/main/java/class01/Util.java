package class01;

public class Util {

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}

