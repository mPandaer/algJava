package space.pandaer.basedatastructure;


import java.util.ArrayList;

/**
 * 反转双链表
 */
public class ReverseDoubleLinkedList {

    static class DoubleNode {
        int value;
        DoubleNode prev;
        DoubleNode next;

        public DoubleNode(int val) {
            this.value = val;
        }
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode prev = null;
        DoubleNode next = null;

        //空链表或者只有一个元素的空链表
        if (head == null || head.next == null) return head;

        while (head != null) {
            next = head.next;
            head.next = prev;
            head.prev = next;
            prev = head;
            head = next;
        }
        prev.next = null;
        return prev;
    }

    //容器法
    public static DoubleNode reverseDoubleLinkedListWithList(DoubleNode head) {
        ArrayList<DoubleNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        DoubleNode prev = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            DoubleNode cur = list.get(i);
            cur.prev = prev;
            if (i - 1 >= 0) {
                cur.next = list.get(i - 1);
            } else {
                cur.next = null;
            }
            prev = cur;
        }

        return list.get(list.size() - 1);

    }


    //生成随机双链表
    public static DoubleNode getRandomLinkedList(int maxLen, int maxNum) {
        int len = RandomUtil.randomLen(maxLen);
        DoubleNode head = new DoubleNode(-1); //哨兵节点
        DoubleNode prev = null;
        DoubleNode cur = head;
        DoubleNode next;
        for (int i = 0; i < len; i++) {
            next = new DoubleNode(RandomUtil.randomNum(maxNum));
            cur.next = next;
            cur.prev = prev;
            prev = cur;
            cur = next;
        }
        head = head.next;
        head.prev = null;
        return head;
    }


    //复制双链表
    public static DoubleNode copy(DoubleNode head) {
        DoubleNode newHead = new DoubleNode(-1);
        DoubleNode prev = null;
        DoubleNode cur = newHead;

        while (head != null) {
            cur.next = new DoubleNode(head.value);
            cur.prev = prev;
            prev = cur;
            cur = cur.next;
            head = head.next;
        }
        newHead = newHead.next;
        newHead.prev = null;
        return newHead;
    }

    public static boolean isEquals(DoubleNode head1, DoubleNode head2) {
        while (head1 != null && head2 != null) {
            if (head1.value != head2.value) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    public static void output(DoubleNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }


    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            DoubleNode head = getRandomLinkedList(maxLen, maxNum);
            DoubleNode head1 = copy(head);
            DoubleNode head2 = copy(head);
            reverseDoubleLinkedList(head1);
            reverseDoubleLinkedListWithList(head2);
            if (!isEquals(head1, head2)) {
                output(head);
                output(head1);
                output(head2);
                System.out.println("失败了");
                return;
            }


        }
        System.out.println("成功了");
    }


    //test
    public static void main(String[] args) {
        int testTime = 1000;
        int maxLen = 100;
        int maxNum = 100;
        test(testTime,maxLen,maxNum);
    }

}
