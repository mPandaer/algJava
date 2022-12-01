package space.pandaer.sort;

//最简单的冒泡排序
import static space.pandaer.sort.ArrayUtil.*;

public class BubbleSort {

    //核心思路
    //利用 两两比较 将大的推到最后
    // 0 - n-1 推出一个最大
    // 0 - n-2 推出一个最大
    // 0 - 1 推出一个最大的
    public static void bubbleSort(int[] arr) {

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

    }

    //test
    public static void main(String[] args) {
        int testTime = 10000;
        int maxLen = 500;
        int maxNum = 100;
        //对数器
        test(testTime, maxLen, maxNum, BubbleSort::bubbleSort);
    }


}
