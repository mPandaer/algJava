package space.pandaer.linkedlist;


import java.util.ArrayList;
import java.util.Random;

import static space.pandaer.linkedlist.LinkedListUtil.*;

/*
链表找中点问题
方法一：暴力遍历
方法二：快慢指针
 */
public class LinkedListMid {

    public static void main(String[] args) {
        int testTime = 10000;
        int maxLen = 500;
        int maxNum = 100;
        test(testTime, maxLen, maxNum);
    }


    //要求：链表奇数的时候，返回唯一的中点，偶数的时候返回上中点
    public static Node findMid1(Node head) {
        //快慢指针法
        Node fast = head;
        Node slow = head;

        if (fast == null || fast.next == null || fast.next.next == null) return head;


//        fast.next != null && fast.next.next != null
        //保证了，fast后面有两个节点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Node findMidWithArray1(Node head) {
        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int midIndex = (list.size() - 1) / 2;
        return list.get(midIndex);
    }


    //要求：链表奇数的时候返回唯一的中点，偶数的的时候返回下中点
    public static Node findMid2(Node head) {

        if (head == null || head.next == null) return head;
        if (head.next.next == null) return head.next;

        //通过增加虚拟节点来简化操作
        Node fast = new Node();
        fast.next = head;
        Node slow = fast;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return fast == null ? slow : slow.next;
    }

    public static Node findMidWithArray2(Node head) {
        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int midIndex = list.size() / 2;
        return list.get(midIndex);
    }


    //要求：返回上一个中点的前一个节点
    //奇数：就是唯一的上中点的前一个节点
    //偶数：上中点的前一个节点
    public static Node findMid3(Node head) {
        //快慢指针法
        Node fast = head;
        Node slow = head;
        Node prev = null; //拿个变量记着

        if (fast == null || fast.next == null || fast.next.next == null) return head;

//        fast.next != null && fast.next.next != null
        //保证了，fast后面有两个节点
        while (fast.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return prev;
    }

    public static Node findMidWithArray3(Node head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int midIndex = (list.size() - 1) / 2;
        return list.get(midIndex - 1);
    }


    //要求：返回上一个中点的前一个节点
    //奇数：就是唯一的上中点的前一个节点
    //偶数：下中点的前一个节点
    public static Node findMid4(Node head) {

        if (head == null || head.next == null || head.next.next == null) return head;

        //通过增加虚拟节点来简化操作
        Node fast = new Node();
        fast.next = head;
        Node slow = fast;
        Node prev = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        return fast == null ? prev : prev.next;
    }


    public static Node findMidWithArray4(Node head) {

        if (head == null || head.next == null || head.next.next == null) return head;
        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int midIndex = list.size() / 2;
        return list.get(midIndex-1);
    }








    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            Node head = randomLinkedList(maxLen, maxNum);
//            Node res1 = findMid1(head);
//            Node res2 = findMidWithArray1(head);
//            Node res1 = findMid2(head);
//            Node res2 = findMidWithArray2(head);
//            Node res1 = findMid3(head);
//            Node res2 = findMidWithArray3(head);

            Node res1 = findMid4(head);
            Node res2 = findMidWithArray4(head);

            if (res1 != res2) {
                System.out.println("失败了");
                System.out.println("原始链表：");
                printLikedList(head);
                System.out.println("错误的结果");
                System.out.println(res1.value + " hashcode " + res1);
                System.out.println("正确的结果");
                System.out.println(res2.value + " hashcode " + res2);
                return;
            }
        }

        System.out.println("成功了");
    }

}
