package space.pandaer.sort;



import java.util.Arrays;
import java.util.Random;

//排序大集合
/*
    1. 快速排序
    2. 归并排序
    3. 堆排序
    4. 计数排序
    5. 基数排序
    6. 插入排序
    7. 选择排序
    8. 冒泡排序
    9. 希尔排序
 */
public class SortSetTest {

    //测试代码
    public static void main(String[] args) {
        int testTime = 1000;
        int maxLen = 1000;
        int maxNum = 1000;
        test(testTime, maxLen, maxNum);
    }


    public static final Random random = new Random();

    //检查是否需要排序
    public static boolean isNeedSort(int[] arr) {
        return arr != null && arr.length >= 2;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static boolean isEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;

    }


    public static int randomNum(int maxNum) {
        return random.nextInt(maxNum) - random.nextInt(maxNum);
    }

    public static int randomLen(int maxLen) {
        return random.nextInt(maxLen) + 1; //长度不能为零
    }

    public static int[] randomArray(int maxLen, int maxNum) {
        int len = randomLen(maxLen);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = randomNum(maxNum);
        }
        return arr;
    }


    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            int[] res = randomArray(maxLen, maxNum);
            int[] res1 = Arrays.copyOf(res, res.length);
            int[] res2 = Arrays.copyOf(res, res.length);
//            quickSort(res1);
//            mergeSort(res1);
//            heapSort(res1);
//            countSort(res1);
//            baseSort(res1);
//            inertSort(res1);
//            selectSort(res1);
//            bubbleSort(res1);
            shellSort(res1);
            Arrays.sort(res2);
            if (!isEquals(res1, res2)) {
                System.out.println("失败了");
                System.out.println("原数组：");
                System.out.println(Arrays.toString(res));
                System.out.println("失败的数组：");
                System.out.println(Arrays.toString(res1));
                System.out.println("成功的数组：");
                System.out.println(Arrays.toString(res2));
                return;
            }

        }
        System.out.println("成功了");
    }


    //1.快速排序
    public static void quickSort(int[] arr) {
        if (!isNeedSort(arr)) return; //不需要排序
        //规定一个函数：quickSortImpl能够在规定的区间利用快速排序的思想排好序
        quickSortImpl(arr, 0, arr.length - 1);
    }

    private static void quickSortImpl(int[] arr, int L, int R) {
        if (L >= R) return; //不需要分区

        //小技巧：随机快排
        int randomIndex = random.nextInt(R - L + 1) + L;
        swap(arr, R, randomIndex);
        //1.分区 -- 以最后一个为标志数
        int lessR = L - 1;
        int moreL = R;
        int curIndex = L;

        while (curIndex < moreL) {
            if (arr[curIndex] == arr[R]) {
                curIndex++;
            } else if (arr[curIndex] > arr[R]) {
                //交换
                swap(arr, --moreL, curIndex);
            } else {
                //交换
                swap(arr, ++lessR, curIndex++);
            }
        }
        swap(arr, curIndex, R);
        //分区完毕 这个过程递归下去
        //2.递归
        quickSortImpl(arr, L, lessR);
        quickSortImpl(arr, moreL + 1, R);

    }
    //-------------------------------------------------------------


    //2.归并排序
    public static void mergeSort(int[] arr) {
        if (!isNeedSort(arr)) return;

        //规定一个函数：mergeSortImpl在规定的区间用归并排序的思想去排序
        mergeSortImpl(arr, 0, arr.length - 1);


    }

    private static void mergeSortImpl(int[] arr, int L, int R) {
        if (L >= R) return; //不需要合并
        int mid = L + ((R - L) >> 1);
        //让左边排好序
        mergeSortImpl(arr, L, mid);
        //让右边排好序
        mergeSortImpl(arr, mid + 1, R);
        //合并
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int index = 0;
        int leftIndex = left;
        int rightIndex = mid + 1;
        //合并
        while (leftIndex <= mid && rightIndex <= right) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                help[index++] = arr[leftIndex++];
            } else {
                help[index++] = arr[rightIndex++];
            }
        }

        //看左右数组是否还有元素
        while (leftIndex <= mid) {
            help[index++] = arr[leftIndex++];
        }
        while (rightIndex <= right) {
            help[index++] = arr[rightIndex++];
        }

        //将help的数据拷贝回去对应的区域
        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }


    }

    //--------------------------------------------------------------------------

    //3.堆排序
    public static void heapSort(int[] arr) {
        //arr ==> 变成大根堆 从下往上建
        int heapSize = arr.length;

        //建立堆的复杂度 O(n)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }

        while (heapSize > 0) {
            //交换
            swap(arr, 0, --heapSize);
            //维护堆化
            heapify(arr, 0, heapSize);
        }
    }

    //调整堆
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            //寻找孩子的较大值
            int childMaxIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //父子pk
            int maxIndex = arr[index] > arr[childMaxIndex] ? index : childMaxIndex;

            if (maxIndex == index) break;
            swap(arr, maxIndex, index);
            index = maxIndex;
            left = index * 2 + 1;

        }
    }
    //----------------------------------------------------------------------------


    //4.计数排序 限制 num >= 0的数 这个是优化版本（要求：不约界就可以）
    public static void countSort(int[] arr) {
        //找最大
        int max = findMax(arr);
        int min = findMin(arr);
        int len = min < 0 ? max - min + 1 : max + 1;
        int[] count = new int[len];
        int offset = min < 0 ? -min : 0;
        //计数
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] + offset]++;
        }
        //拷贝
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            int num = count[i];
            for (int j = 0; j < num; j++) {
                arr[index++] = i - offset;
            }
        }
    }

    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    public static int findMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }
    //--------------------------------------------------------------------------------

    //5.基数排序(优化版本可以排序负数) （经典的是不能排负数的，当然我们这个有一定的缺陷，约界风险）
    public static void baseSort(int[] arr) {
        int min = findMin(arr);
        int offset = min < 0 ? -min : 0;

        //增加偏移量
        for (int i = 0; i < arr.length; i++) {
            arr[i] += offset;
        }

        //最大数的位数
        int maxDigit = digit(findMax(arr));


        //指定位数上的数（从0计数 个位开始）
        for (int i = 0; i < maxDigit; i++) {
            int[] count = new int[10];
            for (int j = 0; j < arr.length; j++) {
                count[getNumWithDigit(arr[j], i)]++;
            }
            //用前缀和代替桶
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j - 1] + count[j];
            }

            //辅助数组
            int[] help = new int[arr.length];
            //出桶
            //解释一下：为什么倒序
            //因为现在count的含义是 0--i位置有多少个数，比如 count[3] = 5 表示 <=3的有5个，我们又想用这个5来当索引用，
            //所以我们用的这个5一定是<=3的最后一个，所以我们倒序遍历，那么出现的第一个符合条件的，就一定是最后一个。
            //又因为下标从0开始，所以就是 5 - 1 = 4
            for (int j = arr.length - 1; j >= 0; j--) {
                int index = --count[getNumWithDigit(arr[j], i)];
                help[index] = arr[j];
            }
            //拷贝
            for (int j = 0; j < help.length; j++) {
                arr[j] = help[j];
            }

        }

        //复原
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= offset;
        }
    }

    public static int digit(int num) {
        if (num == 0) return 1;

        int count = 0;
        while (num != 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    public static int getNumWithDigit(int num, int index) {
        //从0计数
        for (int i = 0; i < index; i++) {
            num /= 10;
        }
        return num % 10;

    }
    //---------------------------------------------------------------------------------

    //6.插入排序
    public static void inertSort(int[] arr) {
        if (!isNeedSort(arr)) return;

        // 0--0有序了
        for (int j = 1; j < arr.length; j++) {
            int num = arr[j];
            int index = j - 1;
            while (index >= 0 && num < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = num;//解释为什么index + 1,因为index表示当前目标数的前一个。

        }


    }
    //----------------------------------------------------------------------------------

    //7.选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] < arr[j] ? minIndex : j;
            }
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }

    }
    //------------------------------------------------------------------------------------


    //8.冒泡排序
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }


    }
    //------------------------------------------------------------------------------------

    //9.希尔排序(插入排序的优化)
    public static void shellSort(int[] arr) {
        if (!isNeedSort(arr)) return;

        int inc = arr.length / 2;
        while(inc > 0) {
            //规定一个函数，以指定的步长进行插入排序思想
            shellSortImpl(arr,inc);
            inc /= 2;
        }

    }

    public static void shellSortImpl(int[] arr,int step) {

        for (int i = step;i<arr.length;i+=step) {

            int num = arr[i];//记录当前数
            int curIndex = i;//记录当前下标
            while (curIndex - step >=0 && arr[curIndex - step] > num) {
                arr[curIndex] = arr[curIndex - step];
                curIndex -= step;
            }
            arr[curIndex] = num;
        }
    }
    //-------------------------------------------------------------------------------------

}
