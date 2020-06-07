package print_problem;

import java.util.concurrent.locks.LockSupport;

public class LockSupportSolution {
    static Thread t1 = null, t2 = null;
    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print((char)(i + 'A'));
                LockSupport.unpark(t2);
                LockSupport.park();
            }
            LockSupport.unpark(t2);
        });

        t2 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print(i + 1);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
            LockSupport.unpark(t1);
        });

        t1.start();
        t2.start();

    }
}
