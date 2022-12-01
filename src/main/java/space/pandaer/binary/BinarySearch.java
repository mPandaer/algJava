package space.pandaer.binary;

import space.pandaer.sort.ArrayUtil;

import java.util.Arrays;

public class BinarySearch {

    //二分查找
    public static boolean binarySearch(int[] arr, int target) {
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1); // 防止溢出

            if (arr[mid] > target) {
                R = mid - 1;
            } else if (arr[mid] < target) {
                L = mid + 1;
            } else {
                return true;
            }

        }
        return arr[mid] == target;
    }

    public static boolean search(int[] arr, int target) {
        for (int j : arr) {
            if (j == target) return true;
        }
        return false;
    }

    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        int[] arr = ArrayUtil.createRandomArray(maxLen, maxNum);
        Arrays.sort(arr);
        int ranNum = (int) (Math.random() * maxNum);
        for (int i = 0; i < testTime; i++) {
            if (binarySearch(arr, ranNum) != search(arr, ranNum)) {
                ArrayUtil.output(arr);
                System.out.println(ranNum);
                System.out.println("失败了");
                return;
            }
        }
        System.out.println("成功了");
    }


    public static void main(String[] args) {
        int testTime = 1000;
        int maxLen = 200;
        int maxNum = 100;
        test(testTime, maxLen, maxNum);
    }


}
