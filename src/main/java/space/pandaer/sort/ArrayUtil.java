package space.pandaer.sort;

import java.util.Arrays;

public class ArrayUtil {
    public static int[] createRandomArray(int maxLen, int maxNum) {
        int len = (int) (Math.random() * maxLen);
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = (int) (Math.random() * maxNum) - (int) (Math.random() * maxNum);
        }
        return res;

    }

    public static void output(int[] test) {
        for (int num : test) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr ,int i,int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static boolean isEquals(int[] arr1,int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i<arr1.length;i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    //对数器
    public static void test(int testTime,int maxLen,int maxNum,TestBlock testBlock){
        for (int i = 0; i<testTime;i++) {
            //生成随机数组
            int[] arr = createRandomArray(maxLen,maxNum);
            int[] arr1 = Arrays.copyOf(arr,arr.length);
            int[] arr2 = Arrays.copyOf(arr,arr.length);

            Arrays.sort(arr1);
            testBlock.block(arr2);
            if (!isEquals(arr1,arr2)) {
                output(arr);
                System.out.println("失败了");
                return;
            }
        }
        System.out.println("成功了");



    }
}
