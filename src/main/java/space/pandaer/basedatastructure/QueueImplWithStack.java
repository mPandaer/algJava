package space.pandaer.basedatastructure;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//用栈来实现队列
//核心思路
//1. 用两个栈来实现 一个是入队栈 ，一个是出队栈。
//2. 入队就直接加入队列中，出队就先判断出队栈是否为空，如果为空，就将入队栈的数据全部加进来
public class QueueImplWithStack {
    private final Stack<Integer> pushStack = new Stack<>();
    private final Stack<Integer> popStack = new Stack<>();

    //入队
    public void enqueue(int num) {
        pushStack.push(num);
    }

    //将入队栈中的数据全部倒入出队栈中
    private void pushToPop() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    //出队
    public int dequeue() {
        if (popStack.isEmpty() && pushStack.isEmpty())
            throw new IllegalArgumentException("队列为空");
        pushToPop();
        return popStack.pop();
    }

    //查看第一个数
    public int peek() {
        pushToPop();
        return popStack.peek();
    }


    //对数器
    public static void test(int testTime) {
        QueueImplWithStack myQueue = new QueueImplWithStack();
        Queue<Integer> queue = new LinkedList<>();
        int maxNum = 100;
        for (int i = 0; i < testTime; i++) {
            boolean flag = Math.random() < 0.5;
            if (flag) {
                int num = RandomUtil.randomNum(maxNum);
                myQueue.enqueue(num);
                queue.offer(num);
            }else {
                try{
                    Integer num1 = myQueue.dequeue();
                    Integer num2 = queue.poll();
                    assert num2 != null;
                    if (!num2.equals(num1)){
                        System.out.println("失败了");
                        return;
                    }
                }catch (Exception e) {
                    int num = RandomUtil.randomNum(maxNum);
                    myQueue.enqueue(num);
                    queue.offer(num);
                }
            }

        }
        System.out.println("成功了");
    }


    public static void main(String[] args) {
        int testTime = 100;
        test(testTime);
    }


}
