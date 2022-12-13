package space.pandaer.linkedlist;

import java.util.ArrayList;
import java.util.List;

import static space.pandaer.linkedlist.LinkedListUtil.*;

/*
链表分区
 */
public class PartitonLinkedList {


    //容器法
    public static Node partitionLinkedListWithArray(Node head, int target) {
        ArrayList<Node> list = new ArrayList<>();

        while (head != null) {
            list.add(head);
            head = head.next;
        }

        //分区
        int lessR = -1;
        int moreL = list.size();
        int curIndex = 0;
        while (curIndex < moreL) {
            if (list.get(curIndex).value == target) {
                curIndex++;
            } else if (list.get(curIndex).value < target) {
                //交换
                swap(list, ++lessR, curIndex++);
            } else {
                swap(list, --moreL, curIndex);
            }
        }
        //重新连接
        head = list.get(0);
        Node tail = head;
        for (int i = 1; i < list.size(); i++) {
            tail.next = list.get(i);
            tail = list.get(i);
        }
        tail.next = null;
        return head;

    }


    //降低空间复杂度
    public static Node partitionLinkedList(Node head, int target) {
        //分类
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;

        while (head != null) {
            Node next = head.next;
            head.next = null;
            if (head.value == target) {
                if (eH == null) {
                    eH = head;
                } else {
                    eT.next = head;
                }
                eT = head;
            } else if (head.value > target) {

                if (bH == null) {
                    bH = head;
                } else {
                    bT.next = head;
                }
                bT = head;

            } else {
                if (sH == null) {
                    sH = head;
                } else {
                    sT.next = head;
                }
                sT = head;
            }

            head = next;
        }


        //开始连接的过程

        if (sT != null) {
            //如果有小区
            sT.next = eH;
            eT = eT == null ? sT : eT; //尽量让eT不为空
        }


        //如果没有小区也有等区。
        if (eT != null) {
            eT.next = bH;
        }

        //如果没有小区也有等区 就直接返回大区 不过有没有
        return sH != null ? sH : eH != null ? eH : bH;

    }

    public static void test(int testTime, int maxLen, int maxNum) {

        for (int i = 0; i < testTime; i++) {
            Node head = randomLinkedList(maxLen, maxNum);
            Node head1 = copyLinkedList(head);
            Node head2 = copyLinkedList(head);
            int randNum = randomNum(maxNum);
            head1 = partitionLinkedList(head1, randNum);
            head2 = partitionLinkedListWithArray(head2, randNum);
//            List<Integer> list1 = linkedListToArray(head1);
            List<Integer> list1 = linkedListToArray(head2);

            if (!isRight(list1, randNum)) {
                System.out.println("失败了");
                System.out.println("随机数：" + randNum);
                System.out.println("原链表：");
                printLikedList(head);
                System.out.println("错误的链表");
                printLikedList(head1);
                return;
            }


        }
        System.out.println("成功了");

    }


    public static void swap(ArrayList<Node> list, int i, int j) {
        Node tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }


    public static List<Integer> linkedListToArray(Node head) {
        ArrayList<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    public static boolean isRight(List<Integer> list, int target) {
        boolean isContains = list.contains(target);
        if (!isContains) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > target) {
                    list.add(i, target);
                    break;
                }
            }
            list.add(target);
        }
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == target) {
                index = i;
                break;
            }
        }

        for (int i = 0; i < index; i++) {
            if (list.get(i) > target) {
                return false;
            }
        }

        for (int i = 0; i < index; i++) {
            if (list.get(i) > target) {
                return false;
            }
        }

        for (int i = index + 1; i < list.size(); i++) {
            if (list.get(i) < target) {
                return false;
            }
        }

        return true;

    }


    public static void main(String[] args) {
        int testTime = 10000;
        int maxLen = 100;
        int maxNum = 100;
        test(testTime, maxLen, maxNum);
    }
}


