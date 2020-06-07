package print_problem;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class BlockingQueueSolution {
    static TransferQueue<String> tQ = new LinkedTransferQueue<>();

    public static void main(String[] args) {

        try {
            tQ.put("T1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                try {
                    while(!"T1".equals(tQ.take())) {
                        tQ.put("T2");
                        Thread.sleep(10);
                    }
                    System.out.print((char)('A' + i));
                    tQ.put("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                try {
                    while(!"T2".equals(tQ.take())) {
                        tQ.put("T1");
                        Thread.sleep(10);
                    }
                    System.out.print(1 + i);
                    tQ.put("T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
