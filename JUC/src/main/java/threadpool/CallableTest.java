package threadpool;

import java.util.concurrent.*;

public class CallableTest {

    static class MyCallable<String> implements Callable<String> {
        String name;
        public MyCallable(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            return this.name;
        }
    }

    static class MyRunnable implements Runnable {
        String name;
        public MyRunnable(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println("This is " + name);
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(() -> System.out.println("Default Runnable Implements"));
        Future<?> future1 = service.submit(new MyRunnable("MyRunnable"));
        Future<String> future2 = service.submit(new MyCallable<>("MyCallable"));
        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            service.shutdown();
            service.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Future<String> future = service.sub

    }

}
