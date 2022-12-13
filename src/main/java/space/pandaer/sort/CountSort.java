package space.pandaer.sort;


/*
计数排序思路很简单：
1. 将数组中的元素个数通过一个辅助数据记录下来，然后通过数组的下标天然有序，所以就可以使得原数组排好序
不过，有很大的限制，如果数据的范围太广而且只能是非负数（经典的话），就会浪费空间，而且如果是对象排序的话，就会比较难以复原对象信息。
 */
public class CountSort {
    public static void countSort(int[] arr) {
        int max = findMax(arr);
        int[] help = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            help[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i<help.length;i++) {
            int num = help[i];
            for (int j = 0; j<num;j++) {
                arr[index++] = i;
            }
        }

    }

    public static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1,1,1,4,4,5};
        countSort(arr);
        ArrayUtil.output(arr);
    }

}
