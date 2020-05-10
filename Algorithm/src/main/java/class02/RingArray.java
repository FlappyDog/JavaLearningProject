package class02;

/**
 * 用数组的方式实现队列
 */

public class RingArray {
    int[] arr;
    int size;
    int capacity;
    int start;
    int end;

    public RingArray(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
    }

    public void push(int value) {
        if (this.size == this.capacity) {
            return;
        }

        arr[start] = value;
        start = (start + 1) % capacity;
        size ++;
    }

    public int poll() {
        if (size > 0) {
            int value = arr[end];
            size --;
            end = (end + 1) % capacity;
            return value;
        }
        return -1;
    }

    public static void main(String[] args) {
        RingArray queue = new RingArray(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.push(6);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
