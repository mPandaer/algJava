package space.pandaer.async;


import java.util.Arrays;
import java.util.HashSet;

/**
 * 给定一个已经排好序的整数数组 nums和一个要查找的数字target，
 * 若查找数字存在，返回对应下标，若不存在，返回-1，请补全代码(第二处为附加题)
 * 你可以假设 nums 中的所有元素是不重复的。
 */

public class Async {

    //暴力遍历 条件有序忽略
    public static int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }


    //利用条件有序 进行二分
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1); //等价于 (right + left) / 2
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    //附加题: 附加条件 数组中的数据 >= 0 数组还是有序
    public static int hashSearch(int[] nums, int target) {
        int max = nums[nums.length - 1]; //找到最大的数
        Integer[] hashArray = new Integer[max + 1]; //为了能用到 max下标
        /*int[]  = {0}
        Integer[] = {null,}*/
        for (int i = 0; i < nums.length; i++) {
            hashArray[nums[i]] = i; //建立 nums[i] -- i 的映射
        }
//        8 hashArray[8] = 0
//
        return target < hashArray.length && hashArray[target] != null ? hashArray[target] : -1;
    }


    //对数器
    public static void testWithNormal(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            int[] res = getRandomArray(maxLen, maxNum);
            int target = randomNum(maxNum);
            int res1 = search(res, target);
            int res2 = binarySearch(res, target);

            if (res1 != res2) {
                output(res);
                System.out.println(target);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println("失败了");
                return;
            }
        }
        System.out.println("成功了");

    }


    //对数器 附加题
    public static void testWithAdditional(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            int[] res = getRandomPosArray(maxLen, maxNum);
            int target = randomPosNum(maxNum);
            int res1 = search(res, target);
            int res2 = hashSearch(res, target);

            if (res1 != res2) {
                output(res);
                System.out.println(target);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println("失败了");
                return;
            }
        }
        System.out.println("成功了");
    }


    public static void output(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    //生成随机数组
    public static int[] getRandomArray(int maxLen, int maxNum) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] nums = new int[len];
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int num;
            do{
                num = randomPosNum(maxNum) + randomPosNum(maxNum);
            }while (set.contains(num));
            set.add(num);
            nums[i] = num;
        }
        //排序
        Arrays.sort(nums);
        return nums;
    }

    //生成随机正数数组
    public static int[] getRandomPosArray(int maxLen, int maxNum) {
        int len = (int) (Math.random() * maxLen)  + 1;
        int[] nums = new int[len];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num;
            do{
                num = randomPosNum(maxNum) + randomPosNum(maxNum);
            }while (set.contains(num));
            set.add(num);
            nums[i] = num;
        }
        //排序
        Arrays.sort(nums);
        return nums;
    }


    //生成区间 （-maxNum,maxNum）的随机数
    public static int randomNum(int maxNum) {
        int flag = Math.random() < 0.5 ? 1 : -1;
        return (int) (Math.random() * maxNum) * flag;
    }

    //随机一个>=0的数
    public static int randomPosNum(int maxNum) {
        return (int) (Math.random() * maxNum);
    }

    //test
    public static void main(String[] args) {
        int testTime = 10000;
        int maxLen = 100;
        int maxNum = 100;
//        testWithNormal(testTime,maxLen,maxNum);
        testWithAdditional(testTime,maxLen,maxNum);
    }


}
