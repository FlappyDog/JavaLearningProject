package collection_and_map.list_test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;

/**
 * 3个push
 * 3个pop
 */
public class StackTest {

    static Stack<String> stack = new Stack<>();

    public static void main(String[] args) {
        // Write
        Thread[] pushes = new Thread[3];
        for (int i = 0; i < 3; i++) {
            pushes[i] = new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    stack.push("#" + j);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        Arrays.stream(pushes).forEach(Thread::start);
        Arrays.stream(pushes).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("After push: " + stack.size());

        Thread[] pops = new Thread[3];
        for (int i = 0; i < 3; i++) {
            pops[i] = new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    stack.pop();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Arrays.stream(pops).forEach(Thread::start);
        Arrays.stream(pops).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("After pop: " + stack.size());
    }

}
