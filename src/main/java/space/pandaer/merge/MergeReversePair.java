package space.pandaer.merge;

import space.pandaer.sort.ArrayUtil;

import java.util.Arrays;

public class MergeReversePair {


    //逆序对
    //当前数如果比他右边的数大，那就组成了逆序对
    //求解一共有多少个逆序对
    //还是可以使用归并的方式来求解
    public static int mergeReversePair(int[] arr) {
        return mergeReversePairImpl(arr, 0, arr.length - 1);
    }

    public static int mergeReversePairImpl(int[] arr, int left, int right) {
        //base case
        if (left >= right) return 0;


        int mid = left + ((right - left) >> 1);
        return mergeReversePairImpl(arr, left, mid) +
                mergeReversePairImpl(arr, mid + 1, right) +
                merge(arr, left, mid, right);

    }

    public static int merge(int[] arr, int left, int mid, int right) {

        //base case
//        if (left >= right) return 0;

        int p1 = mid;
        int p2 = right;
        int[] help = new int[right - left + 1];
        int index = help.length - 1;
        int count = 0;

        while (p1 >= left && p2 >= mid + 1) {
            if (arr[p1] > arr[p2]) {
                count += p2 - mid;
                help[index--] = arr[p1--];
            } else {
                help[index--] = arr[p2--];
            }
        }
        //如果有一个数组被拷贝完成后，就意味着无法比较了
        while (p1 >= left) {
            help[index--] = arr[p1--];
        }

        while (p2 >= mid + 1) {
            help[index--] = arr[p2--];
        }

        //拷贝回去，利用比较后的顺序
        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
        //返回逆序对的个数
        return count;

    }


    //笨方法 暴力
    public static int reversePair(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                count += num > arr[j] ? 1 : 0;
            }
        }

        return count;
    }


    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            int[] res = ArrayUtil.createRandomArray(maxLen, maxNum);
            int[] res1 = Arrays.copyOf(res, res.length);
            int[] res2 = Arrays.copyOf(res, res.length);
            int ans1 = reversePair(res1);
            int ans2 = mergeReversePair(res2);
            if (ans1 != ans2) {
                ArrayUtil.output(res);
                System.out.println("reverse pair" + res1);
                System.out.println("merge reverse pair" + res2);
                System.out.println("失败了");
                return;
            }

        }

        System.out.println("成功啦");
    }


    //test
    public static void main(String[] args) {
        int testTime = 1000;
        int maxLen = 100;
        int maxNum = 100;
        test(testTime,maxLen,maxNum);
    }

}



