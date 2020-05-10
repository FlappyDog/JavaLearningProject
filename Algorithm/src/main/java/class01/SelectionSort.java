package class01;

public class SelectionSort {

    public static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i ++) {

            int idx = i;
            for (int j = i + 1; j < arr.length; j ++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }
            Util.swap(arr, i, idx);
        }

    }

    public static void main(String[] args) {
        int[] arr = {2,31,1,5,2,3,3,4,6,1,2,3,4,5,5,6};
        selectionSort(arr);
        Util.printArr(arr);
    }

}
