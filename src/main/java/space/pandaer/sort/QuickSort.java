package space.pandaer.sort;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import static space.pandaer.sort.ArrayUtil.test;

//快速排序
//核心思想 根据标志位 分区 分为 小区 等区 大区，
//然后不断的分下去，直到分不了之后，就完成了
public class QuickSort {
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;//不用排序
        process(arr,0,arr.length-1);
    }

    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void process(int[] arr,int L,int R) {
        if (L > R) return; //不符合规则pass掉
        int lessR = L -1;//小区的右边界
        int moreL = R; //大区的左边界
        int index = L;//当前索引
        while(index < moreL) {

            if (arr[index] > arr[R]) {
                //交换
                swap(arr,--moreL,index);
            }else if (arr[index] < arr[R]) {
                swap(arr,++lessR,index++);
            }else {
                index++;
            }

        }
        swap(arr,index,R);
        process(arr,L,lessR);
        process(arr,moreL+1,R);

    }



    static class Job{
        int L;
        int R;

        public Job(int left,int right) {
            this.L = left;
            this.R = right;
        }
    }

    public static int[] process1(int[] arr,int L,int R) {
        if (L >= R) return null; //不符合规则pass掉
        int lessR = L -1;//小区的右边界
        int moreL = R; //大区的左边界
        int index = L;//当前索引
        while(index < moreL) {

            if (arr[index] > arr[R]) {
                //交换
                swap(arr,--moreL,index);
            }else if (arr[index] < arr[R]) {
                swap(arr,++lessR,index++);
            }else {
                index++;
            }

        }
        swap(arr,index,R);
        return new int[] {lessR+1,moreL}; //返回 相等数的第一个 和 最后一个

    }

    //非递归实现
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) return;
        Job job = new Job(0,arr.length-1);
        Deque<Job> stack = new LinkedList<>();
        stack.push(job);

        while(!stack.isEmpty()) {
            Job cur = stack.pop();
            int[] areas = process1(arr, cur.L, cur.R);
            if (areas == null) continue;
            if (areas[0] > cur.L) stack.push(new Job(cur.L,areas[0]-1));
            if (areas[1] < cur.R) stack.push(new Job(areas[1]+1,cur.R));
        }



    }





    //test
    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLen = 5000;
        int maxNum = 100;

        test(testTimes,maxLen,maxNum,QuickSort::quickSort);
        test(testTimes,maxLen,maxNum,QuickSort::quickSort1);


    }


}
