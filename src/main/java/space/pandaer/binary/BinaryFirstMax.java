package space.pandaer.binary;

import space.pandaer.sort.ArrayUtil;

import java.util.Arrays;

public class BinaryFirstMax {

    //要求：找到第一个大于等于target的数 前提有序
    //核心思路
    // 二分找到现在的值，如果大于等于的话，找左边的数。一直分到不能再分为止
    public static int binaryFirstMax(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int pos = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                right = mid - 1;
                pos = pos == -1 ? mid : Math.min(pos, mid);
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }


    public static int firstMax(int[] arr,int target) {
        for (int i = 0; i<arr.length;i++) {
            if (arr[i] >= target) {
                return i;
            }
        }
        return -1;
    }

    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        int[] arr = ArrayUtil.createRandomArray(maxLen, maxNum);
        Arrays.sort(arr);
        int ranNum = (int) (Math.random() * maxNum);
        for (int i = 0; i < testTime; i++) {
            if (binaryFirstMax(arr, ranNum) != firstMax(arr, ranNum)) {
                ArrayUtil.output(arr);
                System.out.println(ranNum);
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
