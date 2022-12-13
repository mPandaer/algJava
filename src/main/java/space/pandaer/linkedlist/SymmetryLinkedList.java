package space.pandaer.linkedlist;


import java.util.ArrayDeque;
import java.util.Deque;

import static space.pandaer.linkedlist.LinkedListUtil.*;

//判断链表是不是回文
public class SymmetryLinkedList {
    //容器法
    public static boolean isSymmetryWithStack(Node head) {
        Deque<Node> stack = new ArrayDeque<>();
        Node tmp = head;
        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
        }

        tmp = head;
        while (tmp != null) {
            if (tmp.value != stack.pop().value) return false;
            tmp = tmp.next;
        }

        return true;
    }


    //降低时间复杂度
    public static boolean isSymmetry(Node head) {
        if (head == null) return false;
        if (head.next == null) return true; //有一个元素必然是回文

        //找中点
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //调整成对称结构
        Node newHead = slow;
        Node prev = null;
        while (newHead != null) {
            Node next = newHead.next;
            newHead.next = prev;
            prev = newHead;
            newHead = next;
        }

        //左右对比
        Node L = head;
        Node R = prev;
        boolean isSymmetry = true;
        while (L != null && R != null) {
            if (L.value != R.value) {
                isSymmetry = false;
                break;
            }
            L = L.next;
            R = R.next;
        }

        //恢复结构
        newHead = prev;
        prev = null;
        while (newHead != null) {
            Node next = newHead.next;
            newHead.next = prev;
            prev = newHead;
            newHead = next;
        }

        return isSymmetry;

    }


    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            Node head = randomLinkedList(maxLen, maxNum);
            boolean res1 = isSymmetry(head);
            boolean res2 = isSymmetryWithStack(head);

            if (res1 != res2) {
                System.out.println("失败了");
                System.out.println("失败的结果");
                System.out.println(res1);
                System.out.println("成功的结果");
                System.out.println(res2);
                return;
            }

        }

        System.out.println("成功了");


    }


    public static void main(String[] args) {
        int testTime = 1000;
        int maxLen = 100;
        int maxNum = 100;

        test(testTime,maxLen,maxNum);
    }


}
