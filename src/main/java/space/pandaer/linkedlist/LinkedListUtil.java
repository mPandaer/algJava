package space.pandaer.linkedlist;

import java.util.Random;

public class LinkedListUtil {

    public static final Random rand = new Random();

    public static int randomLen(int maxLen) {
        return rand.nextInt(maxLen) + 1;
    }

    public static int randomNum(int maxNum) {
        return rand.nextInt(maxNum) - rand.nextInt(maxNum);
    }


    public static Node randomLinkedList(int maxLen, int maxNum) {
        Node head = null;
        Node tail = null;
        int len = randomLen(maxLen);
        for (int i = 0; i < len; i++) {
            Node tmp = new Node(randomNum(maxNum));
            if (head == null) {
                head = tmp;
            } else {
                tail.next = tmp;
            }
            tail = tmp;

        }

        return head;
    }

    public static void printLikedList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static Node copyLinkedList(Node head) {
        Node newHead = new Node(-1);
        Node tmp = newHead;
        while(head != null) {
            tmp.next = new Node(head.value);
            tmp = tmp.next;
            head = head.next;
        }

        return newHead.next;
    }
}
