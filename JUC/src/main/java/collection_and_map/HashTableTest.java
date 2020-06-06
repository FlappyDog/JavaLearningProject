package collection_and_map;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * 1）多线程往HashTable里加入值
 * 2）多线程读值
 */
public class HashTableTest {

    static UUID[] uuids = new UUID[100001];

    static Hashtable<UUID, UUID> hashtable = new Hashtable<>();

    static {
        for (int i = 0; i < 100001; i++) {
            uuids[i] = UUID.randomUUID();
        }
    }

    static class MyWriteCallable implements Callable<Void> {

        int start;
        int end;

        public MyWriteCallable(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Void call() throws Exception {
            for (int i = start; i <= end; i++) {
                hashtable.put(uuids[i], uuids[i]);
            }
            return null;
        }
    }

    public static void main(String[] args) {
        long start, end;

        // 1) 写10W条数据
        ExecutorService executor = Executors.newCachedThreadPool();
        LinkedList<Callable<Void>> tasks = new LinkedList<>();
        for (int i = 0; i < 10; i ++) {
            tasks.add(new MyWriteCallable(i * 10000 + 1, (i+1) * 10000));
        }
        start = System.currentTimeMillis();
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();

        System.out.println("Write Use: " + (end - start));
        System.out.println("hashtable size: " + hashtable.size());

        executor.shutdownNow();

        // 2）10个线程读10W此数据
        ExecutorService executor1 = Executors.newCachedThreadPool();
        List<Callable<Void>> readTasks = new LinkedList<>();
        for (int i = 0; i < 50; i ++) {
            readTasks.add(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (int j = 0; j < 100000; j++) {
                        hashtable.get(uuids[j]);
                    }
                    return null;
                }
            });
        }
        start = System.currentTimeMillis();
        try {
            executor1.invokeAll(readTasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("Read Use: " + (end - start));
        System.out.println("hashtable size: " + hashtable.size());

        System.exit(0);
    }





}
