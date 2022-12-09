package space.pandaer.partition;

import space.pandaer.sort.ArrayUtil;

public class QuickSort {

    //快速排序1.0
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length <2 ) return;
        quickSortImpl1(arr, 0, arr.length - 1);
    }

    public static void quickSortImpl1(int[] arr,int left,int right) {
        if (left >= right) return;
        int index = partition1(arr,left,right);
        quickSortImpl1(arr,left,index-1);
        quickSortImpl1(arr,index+1,right);
    }

    //快速排序2.0
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length <2 ) return;
        quickSortImpl2(arr, 0, arr.length - 1);
    }

    public static void quickSortImpl2(int[] arr,int left,int right) {
        if (left >= right) return; //不符合规则pass
        int[] areas = partition2(arr,left,right);
        quickSortImpl2(arr,left,areas[0]-1);
        quickSortImpl2(arr,areas[1] + 1,right);
    }

    //快速排序3.0 (随机快速排序)

    public static void quickSort3(int[] arr) {
        quickSort3Impl(arr,0,arr.length-1);
    }

    public static void quickSort3Impl(int[] arr,int left,int right) {
        if (arr == null || arr.length <2 ) return;
        int randomIndex = (int) (Math.random() * (right - left + 1)); //随机一个索引
        swap(arr,randomIndex,arr.length-1);
        int[] areas = partition2(arr,left,right);
        quickSortImpl2(arr,left,areas[0]-1);
        quickSortImpl2(arr,areas[1] + 1,right);
    }







    public static int partition1(int[] arr,int left,int right) {

//        if (left == right) return left;

        int lessR = left-1;
        int moreL = right;
        int index = left;


        while (index < moreL) { //不要撞区
            if (arr[index] >= arr[right]) {
                swap(arr,index,--moreL);
            }else {
                swap(arr,index++,++lessR);
            }
        }

        swap(arr,index,right);

        return index;
    }


    public static int[] partition2(int[] arr,int left,int right) {

        int lessR = left-1;
        int moreL = right;
        int index = left;
        while(index < moreL) {
            if (arr[index] == arr[right]) {
                index++;
            }else if (arr[index] > arr[right]) {
                swap(arr,index,--moreL);
            }else {

                swap(arr,index++,++lessR);
            }
        }
        swap(arr,index,right);

//        System.out.println("lessR " +lessR);
//        System.out.println("moreL " +moreL);
        return new int[]{lessR+1,moreL};

    }




    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    //test
    public static void main(String[] args) {
        int testTime = 1000;
        int maxLen = 100;
        int maxNum = 100;
//        int arr[] = new int[10000];
//        for (int i = 0; i<arr.length;i++) {
//            arr[i] = i;
//        }

        int[] arr = {5,2,3,1};
        quickSort3(arr);
//        quickSort2(arr);
        ArrayUtil.output(arr);

//        long start = System.currentTimeMillis();
//        quickSort3(arr);
//        long end = System.currentTimeMillis();
//        System.out.println("时间为："  + (end - start));
//
//        System.out.println("===================================");
//
//        start = System.currentTimeMillis();
//        quickSort2(arr);
//        end = System.currentTimeMillis();
//        System.out.println("时间为："  + (end - start));

//        quickSort2(arr);
//        ArrayUtil.test(testTime,maxLen,maxNum,QuickSort::quickSort1);
//        ArrayUtil.test(testTime,maxLen,maxNum,QuickSort::quickSort2);
//        ArrayUtil.test(testTime,maxLen,maxNum,QuickSort::quickSort3);
    }





}
