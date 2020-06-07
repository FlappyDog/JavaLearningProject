package print_problem;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionSolution {
    static ReentrantLock lock = new ReentrantLock();
    
    static Condition c1 = lock.newCondition();
    static Condition c2 = lock.newCondition();

    static volatile boolean t1Start = false;

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                while(!t1Start);
                lock.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.print((1 + i));
                    c1.signal();
                    c2.await();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();


        new Thread(() -> {
            try {
                lock.lock();
                t1Start = true;
                for (int i = 0; i < 26; i++) {
                    System.out.print((char)('A' + i));
                    c2.signal();
                    c1.await();
                }
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();


    }
    
}
