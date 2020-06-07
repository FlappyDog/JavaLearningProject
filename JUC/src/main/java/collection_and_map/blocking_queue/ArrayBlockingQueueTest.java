package collection_and_map.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 1个线程put
 * 1个线程take
 */
public class ArrayBlockingQueueTest {

    static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(2);

    public static void main(String[] args) {

//        for (int i = 0; i < 5; i++) {
//            System.out.println("add item " + (i + 1));
//            arrayBlockingQueue.offer(i + "");
//        }
//        arrayBlockingQueue.forEach((s) -> System.out.println(s));

        Thread putT = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    arrayBlockingQueue.put("#" + i);
                    System.out.println("put #" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread takeT = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("take " + arrayBlockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        putT.start();
        takeT.start();

        try {
            putT.join();
            takeT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
