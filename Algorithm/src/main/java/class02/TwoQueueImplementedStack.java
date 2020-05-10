package class02;

import java.util.LinkedList;
import java.util.Queue;

public class TwoQueueImplementedStack {
    Queue<Integer> from;
    Queue<Integer> to;

    TwoQueueImplementedStack() {
        from = new LinkedList<>();
        to = new LinkedList<>();
    }

    public void push(int value) {
        from.offer(value);
    }

    public int pop() {
        while (from.size() > 1) {
            to.offer(from.poll());
        }
        int val = from.poll();
        Queue<Integer> tmp = from;
        from = to;
        to = tmp;
        return val;
    }

    public int peek() {
        while (from.size() > 1) {
            to.offer(from.poll());
        }
        int value = from.poll();
        to.offer(value);
        Queue<Integer> tmp = from;
        from = to;
        to = tmp;
        return value;
    }

    public static void main(String[] args) {
        TwoQueueImplementedStack myStack = new TwoQueueImplementedStack();
        myStack.push(1);
        myStack.push(6);
        myStack.push(4);

        System.out.printf("peek: %d, pop: %d\n", myStack.peek(), myStack.pop());
        System.out.printf("peek: %d, pop: %d\n", myStack.peek(), myStack.pop());

        myStack.push(2);
        myStack.push(10);

        System.out.printf("peek: %d, pop: %d\n", myStack.peek(), myStack.pop());
        System.out.printf("peek: %d, pop: %d\n", myStack.peek(), myStack.pop());
        System.out.printf("peek: %d, pop: %d\n", myStack.peek(), myStack.pop());
    }
}
