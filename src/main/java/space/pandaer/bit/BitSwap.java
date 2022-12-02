package space.pandaer.bit;


import space.pandaer.sort.ArrayUtil;

// 用位运算实现交换
public class BitSwap {

    //前提 i 和 j 所对应的内存空间不是同一份
    //核心思路 利用异或的两个性质
    //1. a^a = 0 主要是运用这个性质
    //2. a^0 = a
    public static void bitSwap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    //正常的交换 没有任何限制
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        int[] arr = ArrayUtil.createRandomArray(maxLen, maxNum);
        for (int i = 0; i < testTime; i++) {
            int index1 = (int) (Math.random() * arr.length);
            int index2 = (int) (Math.random() * arr.length);
            while (index2 == index1) {
                index2 = (int) (Math.random() * (arr.length - 1));
            }
            int res1 = arr[index1];
            int res2 = arr[index2];
            bitSwap(arr, index2, index1);
            swap(arr, index2, index1);
            if (res1 != arr[index1] || res2 != arr[index2]) {
                ArrayUtil.output(arr);
                System.out.println(index1 + " " + index2);
                System.out.println("失败了");
                return;
            }

        }
        System.out.println("成功了");

    }

    //test
    public static void main(String[] args) {
        int testTime = 1000;
        int maxLen = 1000;
        int maxNum = 200;
        test(testTime, maxLen, maxNum);
    }


}
