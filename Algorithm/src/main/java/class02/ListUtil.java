package class02;

public class ListUtil {

    static void printList(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.val + ", ");
            cur = cur.next;
        }
        System.out.println();
    }

    static void printDoubleLinkedList(DoubleNode head) {
        DoubleNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + ", ");
            cur = cur.next;
        }
        System.out.println();
    }

}
