package space.pandaer.sort;

import static space.pandaer.sort.ArrayUtil.*;

//选择排序
public class SelectSort {

    //核心思路
    //0--n-1 min
    //1--n-1 min
    //n-2--n-1 min
    public static void selectSort(int[] arr) {
        for (int i = 0; i<arr.length-1;i++) {
            int minIndex = i;
            for (int j = i+1;j<arr.length;j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            //找到最小值的位置了
            if (minIndex != i) {
                swap(arr,i,minIndex);
            }
        }
    }

    //test
    public static void main(String[] args) {
        int testTime = 100000;
        int maxLen = 500;
        int maxNum = 200;
        test(testTime,maxLen,maxNum, SelectSort::selectSort);

    }

}
