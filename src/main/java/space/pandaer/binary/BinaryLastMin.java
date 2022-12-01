package space.pandaer.binary;

import space.pandaer.sort.ArrayUtil;

import java.util.Arrays;

public class BinaryLastMin {

    //找到比目标值小的最后一个
    //核心思路：二分找到，小于等于的，然后看右边
    public static int binaryLastMin(int[] arr,int target) {
        int left = 0;
        int right = arr.length - 1;
        int pos = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] <= target) {
                left = mid + 1;
                pos = pos == -1 ? mid : Math.max(pos,mid);
            }else {
                right = mid - 1;
            }
        }

        return pos;
    }


    public static int lastMin(int[] arr,int target) {
        int pos = -1;
        int i;
        for (i = 0; i<arr.length;i++) {
            if (arr[i] <= target) {
                pos = i;
            }
        }
        return pos;
    }

    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        int[] arr = ArrayUtil.createRandomArray(maxLen, maxNum);
        Arrays.sort(arr);
        int ranNum = (int) (Math.random() * maxNum);
        for (int i = 0; i < testTime; i++) {
            if (binaryLastMin(arr, ranNum) != lastMin(arr, ranNum)) {
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
        int testTime = 10000;
        int maxLen = 500;
        int maxNum = 100;
        test(testTime,maxLen,maxNum);
    }
}
