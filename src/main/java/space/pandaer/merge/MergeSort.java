package space.pandaer.merge;

/**
 * 归并排序 递归和非递归实现
 */
public class MergeSort {
    /**
     * 递归实现
     * 核心思想 将数组分成 两组 左边一组 右边一组 然后 让左边有序，右边有序，然后合并
     */
    public static void mergeSort1(int[] arr) {
        //验证数组是否需要排序
        if (arr == null || arr.length < 2) return;

        mergeSortImpl(arr, 0, arr.length - 1);

    }

    public static void mergeSortImpl(int[] arr, int left, int right) {
        //判断边界合法性 
        if (left >= right) return;

        int mid = left + ((right - left) >> 1);
        mergeSortImpl(arr, left, mid);
        mergeSortImpl(arr, mid + 1, right);
        //合并
        merger(arr, left, mid, right);

    }

    private static void merger(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1]; //辅助数组
        int index = 0;

        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        //寻找未约界的数组
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= right) {
            help[index++] = arr[p2++];
        }

        //将help数组拷贝
        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

    }


    //非递归实现 核心思路不变 通过步长来代替递归
    public static void mergeSort2(int[] arr) {
        int step = 1;
        int len = arr.length;
        while (step < len) {
            int L = 0; //每次分组时 左组的第一个
            while (L < len) {
                int M = L + step - 1; //左组的最后一个
                if (M > len) {
                    break;
                }
                int R = Math.min(M + 1, arr.length - 1);
                merger(arr, L, M, R);
                L = R + 1;
            }

            if (step > len / 2) {
                break;
            }
            step <<= 1;
        }

    }


    //对数器 todo(未实现)



}
