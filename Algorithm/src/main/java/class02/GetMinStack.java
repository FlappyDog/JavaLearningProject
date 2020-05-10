package class02;

import java.util.Stack;

/**
 * 实现一个栈，满足
 * push()
 * pop()
 * peek()
 * peekMin() : 拿到当前栈中的最小元素
 */

public class GetMinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public GetMinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
        minStack.push(minStack.isEmpty() ? value : Math.min(value, minStack.peek()));
    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        GetMinStack myStack = new GetMinStack();
        myStack.push(10);
        myStack.push(6);
        myStack.push(9);
        myStack.push(2);
        myStack.push(1);
        myStack.push(10);

        System.out.printf("min: %d, cur : %d\n", myStack.getMin(), myStack.pop());
        System.out.printf("min: %d, cur : %d\n", myStack.getMin(), myStack.pop());
        System.out.printf("min: %d, cur : %d\n", myStack.getMin(), myStack.pop());
        System.out.printf("min: %d, cur : %d\n", myStack.getMin(), myStack.pop());
        System.out.printf("min: %d, cur : %d\n", myStack.getMin(), myStack.pop());
        System.out.printf("min: %d, cur : %d\n", myStack.getMin(), myStack.pop());
    }

}
