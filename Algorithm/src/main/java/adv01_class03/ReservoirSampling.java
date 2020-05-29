package main.java.adv01_class03;

/**
 *  蓄水池问题
 *  给出一个数据流，长度未知
 *  每个元素只能访问一次
 *  写出一个随机算法，使得数据流中所有元素被选中的概率相等
 */
public class ReservoirSampling {

    public static class RandomBox {

        private int[] bag;
        private int N;
        private int count;

        public RandomBox(int capacity) {
            bag = new int[capacity+1];
            N = capacity;
            count = 0;
        }

        public void add(int num) {
            count ++;
            // 当数组内元素数量小于N时，直接加入
            // 否则
            // 1) 判断新元素是否入数组，概率 N / num
            // 2) 如果新元素入数组，随机弹出一个元素
            if (count <= N) {
                bag[count] = num;
            } else {
                if (rand(num) <= N) {
                    bag[rand(N)] = num;
                }
            }

        }

        public int[] choices() {
            int[] result = new int[bag.length-1];
            for (int i = 0; i < bag.length - 1; i ++) {
                result[i] = bag[i+1];
            }
            return result;
        }

        private int rand(int max) {
            return (int) (Math.random() * max) + 1;
        }

    }

    public static void main(String[] args) {
        System.out.println("hello");
        int all = 100;
        int choose = 10;
        int testTimes = 50000;
        int[] counts = new int[all + 1];
        for (int i = 0; i < testTimes; i++) {
            RandomBox box = new RandomBox(choose);
            for (int num = 1; num <= all; num++) {
                box.add(num);
            }
            int[] ans = box.choices();
            for (int j = 0; j < ans.length; j++) {
                counts[ans[j]]++;
            }
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " times : " + counts[i]);
        }

    }

}
