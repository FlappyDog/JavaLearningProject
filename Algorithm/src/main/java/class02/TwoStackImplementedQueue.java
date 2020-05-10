package class02;

import java.util.Stack;

public class TwoStackImplementedQueue {

    Stack<Integer> pushStack;
    Stack<Integer> popStack;

    TwoStackImplementedQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void push(int val) {
        pushStack.push(val);
    }

    public int poll() {
        if (popStack.isEmpty()) {
            pushToPop();
        }
        return popStack.pop();
    }

    public int peek() {
        if (popStack.isEmpty()) {
            pushToPop();
        }
        return popStack.peek();
    }

    private void pushToPop() {
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
    }

    public static void main(String[] args) {
        TwoStackImplementedQueue myQueue = new TwoStackImplementedQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(5);
        myQueue.push(6);


        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());

        myQueue.push(2);
        myQueue.push(4);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());

    }

}
