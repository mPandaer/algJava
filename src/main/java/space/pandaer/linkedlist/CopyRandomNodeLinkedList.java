package space.pandaer.linkedlist;


import javax.print.attribute.standard.RequestingUserName;
import java.util.HashMap;
import java.util.Random;

/*
复制随机节点链表
 */
public class CopyRandomNodeLinkedList {
    public static final Random random = new Random();

    /*
    Node结构
     */
    static class Node {
        int value;
        Node next;
        Node rand; //随机指向

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

    }

    //copy一份这样的链表
    //方法一 容器法
    public static Node copyRandomLinkedListWithMap(Node head) {
        //建立map映射
        HashMap<Node, Node> map = new HashMap<>();
        Node tmp = head;
        while (tmp != null) {
            map.put(head, new Node(tmp.value));
            tmp = tmp.next;
        }

        //遍历，建立联系
        tmp = head;
        while (tmp != null) {
            Node copyTmp = map.get(tmp);
            copyTmp.next = map.get(tmp.next);
            copyTmp.rand = map.get(tmp.rand);
            tmp = tmp.next;
        }

        //返回copy的头节点
        return map.get(head);

    }

    //降低空间复杂度的非容器法
    public static Node copyRandomLinkedList(Node head) {
        Node tmp = head;

        //复制tmp节点
        while (tmp != null) {
            Node next = tmp.next;
            Node copyTmp = new Node(tmp.value);
            tmp.next = copyTmp;
            copyTmp.next = next;
            tmp = next;
        }

        //调整rand节点
        tmp = head;
        while (tmp != null) {
            Node old = tmp;
            Node copy = tmp.next;
            Node next = tmp.next.next;
            copy.rand = old.rand == null ? null : old.rand.next;
            tmp = next;
        }

        //分离old节点和copy节点
        tmp = head;
        Node newHead = head.next;
        Node copyTmp = head.next;

        while (copyTmp != null) {
            Node next = copyTmp.next;
            if (next == null) break;
            tmp.next = next;
            copyTmp.next = next.next;
            tmp = next;
            copyTmp = next.next;
        }
        tmp.next = null;

        return newHead;

    }


    public static void main(String[] args) {
        int testTime = 10000;
        int maxLen = 100;
        int maxNum = 100;
        test(testTime,maxLen,maxNum);

    }

    public static void printNextNode(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();

    }

    public static void printRandNode(Node head) {
        while (head != null) {
            System.out.print(head.rand == null ? "null " : head.rand.value + " ");
            head = head.next;
        }
        System.out.println();

    }

    public static int randomLen(int maxLen) {
        return random.nextInt(maxLen) + 1;
    }

    public static int randomNum(int maxNum) {
        return random.nextInt(maxNum) - random.nextInt(maxNum);
    }

    public static int randomCount(int maxCount) {
        return random.nextInt(maxCount);
    }


    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {

            Node head = randomLinkedList(maxLen, maxNum);
            Node copyHead = copyRandomLinkedList(head);

            while (head != null) {
                if (!((head.value == copyHead.value)
                        && ((head.rand != null && copyHead.rand != null && head.rand.value == copyHead.rand.value)
                        || (head.rand == null && copyHead.rand == null))
                )){
                    System.out.println("失败了");
                    System.out.println("原数组：Next");
                    printNextNode(head);
                    System.out.println("copy数组：Next");
                    printNextNode(copyHead);

                    System.out.println("原数组：Rand");
                    printRandNode(head);
                    System.out.println("copy数组：Rand");
                    printRandNode(copyHead);
                    return;
                }

                head = head.next;
                copyHead = copyHead.next;
            }


        }
        System.out.println("成功了");

    }


    public static Node randomLinkedList(int maxLen, int maxNum) {
        Node head = new Node(-1);
        Node tmp = head;
        int len = randomLen(maxLen);
        for (int i = 0; i < len; i++) {
            tmp.next = new Node(randomNum(maxNum));
            tmp = tmp.next;
        }

        tmp = head.next;
        while (tmp != null) {
            int count = randomCount(len) + 1;
            Node countNode = head;
            for (int i = 0; i < count; i++) {
                countNode = countNode.next;
            }
            tmp.rand = countNode;

            tmp = tmp.next;
        }


        return head.next;
    }


}
