package collection_and_map.list_test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SynchronizedListTest {
    static List<String> syncList = Collections.synchronizedList(new LinkedList<>());


    public static void main(String[] args) {
        Thread[] writes = new Thread[5];
        Thread remove = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                while (syncList.size() == 0);
                syncList.remove(0);
            }
        });

        for (int i = 0; i < 5; i++) {
            writes[i] = new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    syncList.add(Thread.currentThread().getName() + ": #" + j);
                }
            });
        }

        remove.start();
        Arrays.stream(writes).forEach(Thread::start);
        Arrays.stream(writes).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            remove.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Size: " + syncList.size());
    }

}
