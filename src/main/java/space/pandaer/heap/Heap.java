package space.pandaer.heap;

import space.pandaer.sort.ArrayUtil;

import java.util.Arrays;

/**
 * 什么是堆？ 本质：完全二叉树 + 一些限制
 * 堆的基本操作：入堆 + 出顶
 * 堆的分类 ： 大跟堆 和 小跟堆
 * 大跟堆就是在一个子树中最大的的树是跟节点
 * 小跟堆与之相反
 * 好让我们用数组的底层实现大跟堆并实现堆排序 和 限制条件下的排序
 */

//大跟堆
public class Heap {
    int heapSize = 0; //堆的大小
    final int len = 100;
    int[] num = new int[len]; //堆的容量

    //加入数据
    public void push(int value) {
        if (heapSize > len) throw new IllegalArgumentException("堆满了");

        num[heapSize] = value;
        heapSize++;
        //调整成大跟堆
        heapInsert(num, heapSize - 1);
    }


    //index 当前数在arr中的位置
    private static void heapInsert(int[] arr, int index) {

        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }


    //弹出堆
    public int pop() {
        if (heapSize <= 0) throw new IllegalArgumentException("堆没有数据");

        int tmp = num[0];
        swap(num, 0, heapSize - 1);
        heapSize--;
        //下沉 调成堆
        heapify(num, 0, heapSize);
        return tmp;
    }


    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; //当前数的左孩子索引
        while (left < heapSize) {
            //选出孩子中较大的一个
            int childMaxIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //父子pk
            int maxIndex = arr[childMaxIndex] > arr[index] ? childMaxIndex : index;

            if (maxIndex == index) break; //孩子没有我大

            swap(arr, maxIndex, index); //孩子就要越级

            index = maxIndex; //原来的父节点来到当前最大孩子的位置
            left = index * 2 + 1; //为下一次循环做准备：看看现在位置上的节点有无孩子。

        }
    }


    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    //对数器 验证自己写的堆对不对
    public static void test(int testTime) {
        Heap heap = new Heap();
        int maxNum = 100;
        for (int i = 0; i < testTime; i++) {
            int[] res = ArrayUtil.createRandomArray(heap.len, maxNum);
            int[] src = Arrays.copyOf(res, res.length);
            int[] compareRes = Arrays.copyOf(res, res.length);
            for (int num : res) {
                heap.push(num);
            }
            for (int index = res.length - 1; index >= 0; index--) {
                res[index] = heap.pop();
            }

            Arrays.sort(compareRes);

            if (!ArrayUtil.isEquals(res, compareRes)) {
                ArrayUtil.output(src);
                ArrayUtil.output(res);
                ArrayUtil.output(compareRes);
                System.out.println("失败了");
                return;
            }
        }

        System.out.println("成功了");
    }


    //堆排序
    /*
        核心思路：
        1. 将无序数组变成大跟堆
        2. 每次pop一个数，放在最后，然后在剩下的数中继续这样，知道大跟堆的size == 1 (优化对数器中的空间复杂度)
     */

    public static void heapSort(int[] arr) {
        //建立大跟堆
        //方式一 上升 时间复杂度 O(nlogn)
//        for (int i = 0; i<arr.length;i++) {
//            heapInsert(arr,i);
//        }
        //方式二 下沉 O(n)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        //得到大跟堆的大小
        int heapSize = arr.length;

        while (heapSize > 1) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }

    }


    public static void main(String[] args) {
//        test(10000);

        ArrayUtil.test(10000, 100, 100, Heap::heapSort);

    }


}