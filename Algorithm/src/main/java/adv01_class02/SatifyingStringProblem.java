package main.java.adv01_class02;

/**
 * 给定一个数N，只由0和1两种字符，组成所有长度为N的字符串
 * 如果某个字符串，任何0的左边都有1紧挨着，认为这个字符串达标
 * 问，有多少达标的字符串
 */
public class SatifyingStringProblem {
    static int satisfyingString(int N) {
        if (N == 1) {
            return 1;
        }

        if (N == 2) {
            return 2;
        }

        return satisfyingString(N - 1) + satisfyingString(N - 2);
    }
}
