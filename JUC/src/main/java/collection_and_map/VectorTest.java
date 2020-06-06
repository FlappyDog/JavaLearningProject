package collection_and_map;

import java.util.Vector;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 */
public class VectorTest {

    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票：" + i);
        }
    }

    public static void main(String[] args) {
        // 错误程序
        // 即使Vector的方法是加锁的，但是中间过程并没有被同步，所以还是会出现问题
        /*
        for (int i = 0; i < 10; i ++) {
            new Thread(() -> {
                while (tickets.size() > 0) {

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(tickets.remove(0));
                }
            }).start();
        }
         */


        // 正确程序
        for (int i = 0; i < 10; i ++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    synchronized (tickets) {
                        if (tickets.size() > 0) {
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            System.out.println(tickets.remove(0));
                        }
                    }
                }
            }).start();
        }

    }

}
