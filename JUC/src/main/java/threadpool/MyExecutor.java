package threadpool;

import java.util.concurrent.Executor;

public class MyExecutor implements Executor {

    public MyExecutor() {
        System.out.println("This is my Executor");
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        new MyExecutor().execute(() -> System.out.println("YYYYYYY"));
    }

}
