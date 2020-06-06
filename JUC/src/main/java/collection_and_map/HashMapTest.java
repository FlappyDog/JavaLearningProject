package collection_and_map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**'
 *  1）多线程往HashTable里加入值
 *  2）多线程读值
 *  HashMap不是线程安全的!!
 */
public class HashMapTest {

    static UUID[] uuids = new UUID[100001];

    static HashMap<UUID, UUID> hashmap = new HashMap<>();

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
                hashmap.put(uuids[i], uuids[i]);
            }
            return null;
        }
    }

    public static void main(String[] args) {
        long start, end;

        // 1）写10W数据
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<Void>> writeTasks = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            writeTasks.add(new MyWriteCallable(i * 10000 + 1, (i + 1) * 10000));
        }

        start = System.currentTimeMillis();
        try {
            executor.invokeAll(writeTasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();

        System.out.println("Write use: " + (end - start));
        System.out.println("hashmap size: " + hashmap.size());

        // 2）读数据
        // 2）10个线程读10W此数据
        ExecutorService executor1 = Executors.newCachedThreadPool();
        List<Callable<Void>> readTasks = new LinkedList<>();
        for (int i = 0; i < 50; i ++) {
            readTasks.add(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (int j = 0; j < 100000; j++) {
                        hashmap.get(uuids[j]);
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
        System.out.println("hashmap size: " + hashmap.size());

        System.exit(0);

    }

}
