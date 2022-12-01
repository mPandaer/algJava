package space.pandaer.binary;

import space.pandaer.sort.ArrayUtil;

public class BinaryUnSorted {
    //要求：数组中数据相邻不相等 利用二分寻找 寻找局部最小值
    public static int binaryUnSorted(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1 || arr[0] < arr[1]) return 0;
        if (arr[arr.length - 1] < arr[arr.length - 2]) return arr.length - 1;

        int left = 0;
        int right = arr.length - 2;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;

    }


    //特殊的对数器
    public static void test(int testTime, int maxLen, int maxNum) {


        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = new int[len];
            for (int j = 0; j < len; ) {
                int num = (int) (Math.random() * maxNum);
                if (j == 0) arr[j++] = num;
                else {
                    if (num != arr[j - 1]) arr[j++] = num;
                }
            }

            int index = binaryUnSorted(arr);
            if (index > 0 && index < arr.length - 1 && (arr[index - 1] < arr[index] || arr[index + 1] < arr[index])) {
                ArrayUtil.output(arr);
                System.out.println(index);
                System.out.println("失败了");
                return;
            }
        }
        System.out.println("成功了");

    }

    //test
    public static void main(String[] args) {
        int testTime = 1000000;
        int maxLen = 50;
        int maxNum = 100;
        test(testTime, maxLen, maxNum);
    }
}
