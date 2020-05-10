package class01;

public class EvenOddTimes {
    /**
     * 有一种数，出现奇数次
     */
    static int findOddTimesNumber(int[] arr) {
        int res = 0;
        for (int i : arr) {
            res ^= i;
        }

        return res;
    }

    /**
     * 有两种数，出现奇数次
     */
    static int[] findTwoOddTimesNumber(int[] arr) {
        int res = 0;
        for (int i : arr) {
            res ^= i;
        }

        int rightOne = res & (~res + 1);
        int onlyOne = 0;

        for (int i : arr) {
            if ((i & rightOne) != 0) {
                onlyOne ^= i;
            }
        }

        return new int[]{onlyOne, res ^= onlyOne};


    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,2,2,3,3,3,5,5,8,8,8,8};
        System.out.println(findOddTimesNumber(arr));

        int[] arr1 = {1,1,1,1,2,2,3,3,3,5,5,5,8,8,8,8};
        Util.printArr(findTwoOddTimesNumber(arr1));
    }
}
