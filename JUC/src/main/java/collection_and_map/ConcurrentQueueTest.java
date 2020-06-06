package collection_and_map;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueueTest {

    static ConcurrentLinkedQueue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            new Thread(() -> {
                String s;
                while ((s = tickets.poll()) != null) {
                    System.out.println(s);
                }
            }).start();
        }
    }


}
