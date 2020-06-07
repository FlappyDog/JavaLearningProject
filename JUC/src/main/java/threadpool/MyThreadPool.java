package threadpool;

import java.io.IOException;
import java.util.concurrent.*;

public class MyThreadPool {

    private ThreadPoolExecutor executor;

    public MyThreadPool() {
        executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new MyRejectionPolicy());
    }

    public ThreadPoolExecutor getExecutor() {
        return this.executor;
    }

    class MyRejectionPolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            for (int i = 0; i < 2; i++) {
                System.out.println("Sleep " + i + " second and retry: " + r.toString());
                try {
                    Thread.sleep(1000);
                    if (executor.getQueue().size() < 3) {
                        executor.execute(r);
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Failed: " + r.toString());
        }
    }

    public static void main(String[] args) {
        ExecutorService service = new MyThreadPool().getExecutor();
        for (int i = 0; i < 6; i++) {
            service.submit(() -> {
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }
}
