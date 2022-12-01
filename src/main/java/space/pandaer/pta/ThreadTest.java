//package space.pandaer.pta;
//
//import java.util.ArrayList;
//
//class Thread_test extends Thread
//{
//    int number;
//    public static int sum;
//    public Thread_test(int n) //构造函数
//    {
//        number=n;
//    }
//    public static synchronized void add(int num){  //同步方法
//        sum += num;
//    }
//    public void run()
//    {
//        int count=0;
//        for(int i=0;i<10;i++)
//        {
//            count+=number+i;
//        }
//        System.out.println("The "+((int)number/10+1)+" time: "+count);
//        add(count);
//    }
//
//}
//
///* 请在这里填写答案 */
//
//class Main{
//
//    public static void main(String[] args) throws InterruptedException {
//        Thread_test[] list = new Thread_test[10];
//        for (int i = 0; i<10;i++) {
//            list[i] = new Thread_test(i*10 + 1);
//            list[i].start();
//        }
//
//        for (int i = 0; i<10;i++) {
//            list[i].join();
//        }
//
//        System.out.println(Thread_test.sum);
//
//    }
//}