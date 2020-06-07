package collection_and_map.list_test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 2个线程再写
 * 5个线程在读
 */
public class CopyOneWriteListTest {

    static CopyOnWriteArrayList<String> copyOnWriteList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        Thread[] writeThreads = new Thread[2];
        for (int i = 0; i < 2; i++) {
            writeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 500; j++) {
                    copyOnWriteList.add("# " + j);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        Arrays.stream(writeThreads).forEach(Thread::start);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + " : "
                            + copyOnWriteList.get((int)(Math.random() * copyOnWriteList.size())));
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }

        Arrays.stream(writeThreads).forEach((thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));


        System.out.println("list size: " + copyOnWriteList.size());

    }

}
