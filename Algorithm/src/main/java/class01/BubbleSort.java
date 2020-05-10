package class01;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; i ++) {
            for (int j = arr.length - 1; j > i; j --) {
                if (arr[j] > arr[j-1]) {
                    Util.swap(arr, j, j-1);
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {2,31,1,4,5,1,2,4,5,5,6,7,2,2};
        bubbleSort(arr);
        Util.printArr(arr);
    }
}
