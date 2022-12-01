package space.pandaer.sort;


//归并排序

import java.util.Arrays;

import static space.pandaer.sort.ArrayUtil.output;
import static space.pandaer.sort.ArrayUtil.test;

/**
 * 核心思路 不断的分，让一部分有序，先分再合并
 */
public class MergeSort {
    //递归式的归并排序
    public static void mergeSort(int[] arr) {
        process(arr, 0, arr.length - 1);
    }

    //这个方法让arr[L..R]有序
    public static void process(int[] arr, int L, int R) {
        //base case
        if (L >= R) return;
        int mid = L + ((R - L) >> 1);//为了避免溢出
        //不断的分
        process(arr, L, mid);
        process(arr, mid + 1, R);
        //分到不能再分了，就合并
        //开始合并
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int index = 0;
        int left = L;
        int right = M + 1;
        //这个的逻辑就是如果两个数组中都有数据的话，就先把他们先比较了
        while (left <= M && right <= R) {
            if (arr[left] >= arr[right]) {
                help[index++] = arr[right++];
            } else {
                help[index++] = arr[left++];
            }
        }

        //到这里，就说明已经有一个数组已经填完了，
        // 那么就可以直接把另外一个数组直接添加到help的后面
        while (left <= M) {
            help[index++] = arr[left++];
        }
        while (right <= R) {
            help[index++] = arr[right++];
        }


        //将排好序的数组导入arr
        index = 0;
        for (int i = L; i <= R; i++) {
            arr[i] = help[index++];
        }

    }





    //非递归式的归并排序 核心思路：步长 + 分组
    //左边 和 右边 合并
    public static void mergeSort1(int[] arr){
        process1(arr);

    }

    public static void process1(int[] arr){
        int step = 1;
        int N = arr.length;
        while (step < N) {
            int L = 0;
            int R = 0;
            int M = 0;
            while(L < N) {
                if (N-L > step) {
                    M = L + step -1;
                }else {
                    M = N-1;
                }

                if (M + step>= N-1) {
                    R = N-1;
                }else {
                    R = M + step;
                }
                merge(arr,L,M,R);
                L = R+1;

            }

            if (step > N / 2) {
                break;
            }else {
                step *=2;
            }
        }


    }






    //test
    public static void main(String[] args) {
        int maxLen = 100;
        int maxNum = 200;
        int testTimes = 100;
        test(maxLen,maxLen,testTimes,MergeSort::mergeSort);
        test(maxLen,maxLen,testTimes,MergeSort::mergeSort1);

    }


}
