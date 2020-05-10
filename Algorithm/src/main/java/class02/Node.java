package class02;

public class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}

class DoubleNode {
    int val;
    DoubleNode prev;
    DoubleNode next;

    public DoubleNode(int val) {
        this.val = val;
    }
}
