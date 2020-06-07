package collection_and_map.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

    static BlockingQueue<String> listQ = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        listQ.put("ababab");

        listQ.take();

    }
}
