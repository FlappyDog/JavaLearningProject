package collection_and_map.blocking_queue;

import java.util.concurrent.SynchronousQueue;

/**
 * 2个线程通信
 */
public class SynchronousQueueTest {

    static SynchronousQueue<String> sQ = new SynchronousQueue<>();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    sQ.put(Thread.currentThread().getName() + " #" + i);
                    System.out.println(Thread.currentThread().getName() + " Received : " + sQ.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Received : " + sQ.take());
                    sQ.put(Thread.currentThread().getName() + " #" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
