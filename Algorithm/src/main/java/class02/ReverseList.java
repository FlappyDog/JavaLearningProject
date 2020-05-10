package class02;

public class ReverseList {

    static Node reverseList(Node head) {

        Node prev = null, next = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode prev = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            head.prev = next;
            prev = head;
            head = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node cur = head;
        int size = 10;
        while (size -- > 0) {
            cur.next = new Node((int) (Math.random() * 100));
            cur = cur.next;
        }

        ListUtil.printList(head);
        ListUtil.printList(reverseList(head));

        DoubleNode head2 = new DoubleNode(-1);
        DoubleNode cur2 = head2;
        size = 10;
        while (--size >= 0) {
            cur2.next = new DoubleNode((int) (Math.random() * 10));
            cur2.next.prev = cur2;
            cur2 = cur2.next;
        }
        ListUtil.printDoubleLinkedList(head2);
        ListUtil.printDoubleLinkedList(reverseDoubleList(head2));

    }

}
