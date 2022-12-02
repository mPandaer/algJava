package space.pandaer.sort;

import java.util.Arrays;

import static space.pandaer.sort.ArrayUtil.*;

//插入排序
public class InsertSort {

    //核心思路
    // 0-0 有序
    // 0-1 有序
    // ...
    // 0-n-1 有序
    public static void insertSort(int[] arr) {
        //0-0必然有序
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                //满足条件--交换
                swap(arr, j, j + 1);
            }
        }
    }

    //优化 用移动代替交换 核心思路不变
    public static void insertSort1(int[] arr) {
        //0-0必然有序
        for (int i = 1; i < arr.length; i++) {
            int j;
            int num = arr[i];
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] < num) break; //满足条件就退出
                arr[j + 1] = arr[j]; // 移动
            }
            //到这里，位置就找到了
            arr[j + 1] = num; //带入实际例子
        }

    }


    //test
    public static void main(String[] args) {
        int testTime = 100000;
        int maxLen = 1000;
        int maxNum = 1000;
        test(testTime, maxLen, maxNum,InsertSort::insertSort);
        test(testTime, maxLen, maxNum,InsertSort::insertSort1);

    }


}
