package class03;

public class MergingSortNonRecursive {

    private static void merge(int[] arr, int L, int M, int R) {
        if (L == R) {
            return ;
        }
        int[] helper = new int[R-L+1];

        int p1 = L, p2 = M +1, helperIdx = 0;
        while (p1 <= M && p2 <= R) {
            if (arr[p1] <= arr[p2]) {
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

    }

    public static void mergeSort2(int[] arr) {
        int mergeSize = 1;
        while (mergeSize < arr.length) {
            int L = 0;
            while(L < arr.length) {
                int M = L + mergeSize - 1;
                if (M >= arr.length) {
                    break ;
                }
                int R = Math.min(M + mergeSize, arr.length - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > arr.length / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,3,5,7,2,34,34,6,45,2,3,54,7};
        mergeSort2(arr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

}
