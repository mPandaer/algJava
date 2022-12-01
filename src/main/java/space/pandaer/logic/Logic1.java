package space.pandaer.logic;


public class Logic1 {

    public static void main(String[] args) {
        int testTime = 1000;
        int maxNum = 100000;
        for (int i= 0 ; i<testTime;i++) {
            int num = (int) (Math.random() * maxNum);
            if ((num & 3) != num % 4){
                System.out.println(num);
                System.out.println("出错了");
                System.out.println("=====================");
                return;
            }

        }
        System.out.println("成功了");

    }
}
