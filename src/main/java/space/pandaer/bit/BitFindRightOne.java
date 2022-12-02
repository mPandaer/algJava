package space.pandaer.bit;


public class BitFindRightOne {


    //寻找一个数的二进制中最右边的1
    //核心思路 且运算 + 计算机中表示数是补码 正数的补码 就是原二进制，而负数的补码是 正数补码的二进制去反加1
    // num & (-num)
    //num = 1011
    //-num = 0101
    //0001
    //0001
    public static int bitFindRightOne(int num) {
        int res = num & (~num + 1); //取反 + 1 == -num
        // int 是 32数
        for (int i = 0; i < 32; i++) {
            if ((res & (1 << i)) != 0) {
                return i;
            }
        }
        return -1;

    }

    //特殊的对数器
    public static void test(int testTime, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            //随机一个正负数
            int target = Math.random() < 0.5 ? (int) (Math.random() * maxNum * -1)
                    : (int) (Math.random() * maxNum);

            int index = bitFindRightOne(target);
            if (index == -1 && target == 0) continue;

            if ((target & (1 << index)) == 0) {
                System.out.println(target + " " + index);
                System.out.println("失败了");
                return;
            }
        }
        System.out.println("成功了");
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxNum = 10;
        test(testTime,maxNum);
    }
}
