package print_problem;

import java.util.concurrent.atomic.AtomicInteger;

public class CASSolution {
    static AtomicInteger selector = new AtomicInteger();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                while (selector.get() % 2 != 0);
                System.out.print((char)(i + 'A'));
                selector.incrementAndGet();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                while (selector.get() % 2 != 0);
                System.out.print(i + 1);
                selector.getAndIncrement();
            }
        }).start();
    }

}
