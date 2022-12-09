package space.pandaer.merge;

import space.pandaer.sort.ArrayUtil;

import java.util.Arrays;

public class MergeMimSum {


    /**
     * 要求：在排好序的同时，求出最小数之和
     * 什么是最小数之和：当前数的左边比当前数小的数之和，数组中的每个数都是一样，他们的和就是答案
     * 核心思路：
     * 换个角度，求当前数的后面有几个数比当前的数大。
     * 于是就可以用递归来实现
     */
    public static int mergeMinSum(int[] arr) {
        return mergeMinSumImpl(arr, 0, arr.length - 1);
    }

    public static int mergeMinSumImpl(int[] arr, int left, int right) {
        if (left >= right) return 0; //扣边界
        int mid = left + ((right - left) >> 1);
        return mergeMinSumImpl(arr, left, mid)
                + mergeMinSumImpl(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1]; //辅助数组
        int index = 0;
        int ans = 0;

        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            ans += arr[p1] < arr[p2] ? (right - p2 + 1) * arr[p1] : 0;
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        //寻找未约界的数组
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= right) {
            help[index++] = arr[p2++];
        }

        //将help数组拷贝
        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return ans;
    }

    //绝对正确的算法
    public static int minSum(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                res += arr[j] < num ? arr[j] : 0;
            }
        }
        return res;
    }


    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < maxLen; i++) {
            int[] res = ArrayUtil.createRandomArray(maxLen, maxNum);
            int[] res1 = Arrays.copyOf(res, res.length);
            int[] res2 = Arrays.copyOf(res, res.length);
            int ans1 = minSum(res1);
            int ans2 = mergeMinSum(res2);

            if (ans2 != ans1) {
                ArrayUtil.output(res);
                System.out.println(ans1);
                System.out.println(ans2);
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
        int maxNum = 50;
        test(testTime, maxLen, maxNum);

    }
}
