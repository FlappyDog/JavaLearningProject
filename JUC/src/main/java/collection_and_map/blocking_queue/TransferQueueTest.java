package collection_and_map.blocking_queue;

import java.util.Arrays;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * 1个接受
 * 1个收听
 */
public class TransferQueueTest {

    static TransferQueue<String> tQ = new LinkedTransferQueue<>();

    public static void main(String[] args) throws InterruptedException {
        // 1) 单个接受，单个发送
        Thread pubT = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    tQ.transfer(i + "");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread subT = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Received : " + tQ.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pubT.start();
        subT.start();
        pubT.join();
        subT.join();

        // 群发，群接
        System.out.println("群发，群接");
        Thread[] pubTs = new Thread[5];
        Thread[] subTs = new Thread[3];

        for (int i = 0; i < 5; i++) {
            pubTs[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    try {
                        tQ.transfer(Thread.currentThread().getName() + " : " + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i = 0; i < 3; i++) {
            subTs[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Received : " + tQ.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        Arrays.stream(pubTs).forEach(Thread::start);
        Arrays.stream(subTs).forEach(Thread::start);

        Arrays.stream(pubTs).forEach((thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        Arrays.stream(subTs).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

}
