package print_problem;

/**
 * 两个线程
 * 一个打印1~26
 * 另一个打印A~Z
 * 交替打印；要求输出为A1B2C3D4....
 */
public class WaitNotifySolution {
    public static void main(String[] args) {

        Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 26; i++) {
                    try {
                        o.wait();
                        System.out.print(i+1);
                        o.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                o.notify();
            }

        }).start();

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 26; i++) {
                    try {
                        Thread.sleep(10);
                        System.out.print((char)(i+'A'));
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        }).start();



    }
}
