package collection_and_map;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    static class Node implements Delayed {
        public int time;
        public String val;

        public Node(int time, String val) {
            this.time = time;
            this.val = val.intern();
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return time - 1000;
        }

        @Override
        public String toString() {
            return this.val;
        }
    }

    static DelayQueue<Node> dQ = new DelayQueue<>();

    public static void main(String[] args) {
        dQ.put(new Node(500, "st5"));
        dQ.put(new Node(400, "st4"));
        dQ.put(new Node(300, "st3"));
        dQ.put(new Node(200, "st2"));
        dQ.put(new Node(200, "st1"));

        while (dQ.size() > 0) {
            try {
                System.out.println(dQ.take().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
