package space.pandaer.basedatastructure;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * 反转单链表
 */

public class ReverseLinkedList {
    //链表节点
    static class Node {
        int value;
        Node next;

        public Node(int v) {
            this.value = v;
        }
    }

    //反转链表
    public static Node reverseLinkedList(Node head) {
        if (head == null || head.next == null) return head; //没有链表或者只有一个不反转
        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next; //抓住后面的链条
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;

    }

    //容器法反转链表
    public static Node reverseLinkedListWithList(Node head) {
        if (head == null || head.next == null) return head;
        List<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        Node newHead = list.get(list.size() - 1);
        Node tmp = newHead;

        for (int i = list.size() - 2; i >= 0; i--) {
            tmp.next = list.get(i);
            tmp = tmp.next;
        }
        return newHead;
    }

    public static Node getRandomLinkedList(int maxLen, int maxNum) {
        int len = RandomUtil.randomLen(maxLen);
        Node head = new Node(RandomUtil.randomNum(maxNum));
        Node cur = head;
        for (int i = 0; i < len - 1; i++) {
            cur.next = new Node(RandomUtil.randomNum(maxNum));
            cur = cur.next;
        }
        return head;
    }

    public static Node copyLinkedList(Node head) {
        Node newHead = new Node(-1);
        Node cur = newHead;
        while (head != null) {
            cur.next = new Node(head.value);
            cur = cur.next;
            head = head.next;
        }
        return newHead.next;
    }

    public static void output(Node head) {
        while (head != null) {
            System.out.println(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static boolean isEquals(Node n1, Node n2) {
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }


    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            Node head = getRandomLinkedList(maxLen, maxNum);
            Node head1 = copyLinkedList(head);
            Node head2 = copyLinkedList(head);

            Node tmp1 = reverseLinkedList(head1);
            Node tmp2 = reverseLinkedListWithList(head2);
            if (!isEquals(tmp2, tmp1)) {
                output(head);
                output(tmp1);
                output(tmp2);
                System.out.println("失败了");
                return;
            }


        }

        System.out.println("成功了");
    }

    //test
    public static void main(String[] args) {
        int testTime = 100000;
        int maxLen = 1000;
        int maxNum = 100;
        test(testTime,maxLen,maxNum);
    }


}
