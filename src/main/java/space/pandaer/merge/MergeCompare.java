package space.pandaer.merge;

import space.pandaer.sort.ArrayUtil;

import java.util.Arrays;

public class MergeCompare {

    // 要求：如果当前数num > 他后面的数 * 2 就记录，就这种数的总和
    // 还是借助归并的思想 左组如果大于右组的两倍就记录
    public static int mergeCompare(int[] arr) {
        return mergeCompareImpl(arr, 0, arr.length - 1);

    }

    public static int mergeCompareImpl(int[] arr, int left, int right) {
        // base case
        if (left >= right) return 0;
        int mid = left + ((right - left) >> 1);
        return mergeCompareImpl(arr, left, mid) +
                mergeCompareImpl(arr, mid + 1, right) +
                merge(arr, left, mid, right);
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        if (left >= right) return 0;
        int p1 = left;
        int p2 = mid + 1;
        int areaR = mid + 1;
        int count = 0;

        for (int i = left; i <= mid; i++) {
            while (areaR <= right && arr[i] > (arr[areaR] * 2)) {
                areaR++;

            }
            count += areaR - mid - 1;
        }

        int[] help = new int[right - left + 1];
        int index = 0;
        while (p1 <= mid && p2 <= right) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }

        while (p2 <= right) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return count;


    }


    //笨方法暴力
    public static int compare(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                count += num > arr[j] * 2 ? 1 : 0;
            }
        }

        return count;
    }


    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            int[] res = ArrayUtil.createRandomArray(maxLen, maxNum);
            int[] res1 = Arrays.copyOf(res, res.length);
            int[] res2 = Arrays.copyOf(res, res.length);
            int ans1 = mergeCompare(res1);
            int ans2 = compare(res2);

            if (ans1 != ans2) {
                ArrayUtil.output(res);
                System.out.println("merge compare " + ans1);
                System.out.println("compare " + ans2);
                System.out.println("失败了");
                return;
            }


        }

        System.out.println("成功啦");
    }


    //test
    public static void main(String[] args) {
        int testTime = 1000;
        int maxLen = 5;
        int maxNum = 8;
        test(testTime, maxLen, maxNum);
    }

}
