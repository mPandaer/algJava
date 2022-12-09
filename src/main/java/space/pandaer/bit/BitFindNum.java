package space.pandaer.bit;

import space.pandaer.sort.ArrayUtil;

import java.util.HashMap;

public class BitFindNum {
    //从一个数组中找一推数
    //这个数组满足这个条件 只有一个数出现奇数次，其他数都出现偶数次
    //核心思路：
    //根据 异或的交换率和结合率
    // a^b = b^a
    // a^(b^c) = (a^b)^c
    // 0 ^ a = a

    public static int bitFindNum(int[] arr) {
        int res = 0;
        for (int num : arr) {
            res ^= num;
        }
        return res;

    }

    //利用hash
    public static int findNum(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.merge(num, 1, Integer::sum);
        }

        for (int num : arr) {
            if (map.get(num) % 2 != 0) return num;
        }
        return -1;

    }

    //对数器
    public static void test(int testTime, int maxLen, int maxNum) {
        for (int i = 0; i < testTime; i++) {
            int target = randomWithRange(maxNum); //目标数
            int targetTime = (int) (Math.random() * maxLen); //目标次数
            //保证指定的数出现的次数是奇数
            targetTime = targetTime % 2 == 0 ? targetTime + 1 : targetTime;
            //保证长度是奇数
            int len = (int) (Math.random() * maxLen + 2);
            len = len % 2 == 0 ? len : len - 1;
            len += targetTime;
            //开始填充数据
            int[] arr = new int[len];
            int index = 0;
            for (int j = 0; j < targetTime; j++) {
                arr[index++] = target;
            }

            //填充其他数据
            while (index < arr.length) {
                int num = (int) (Math.random() * maxLen);
                num = num % 2 == 0 ? num : num - 1;
                int testNum = (int) (Math.random() * maxNum);
                for (int j = 0; j < num && index < arr.length; j++) {
                    arr[index++] = testNum;
                }
            }

            //打乱数据
            for (int j = 0; j < arr.length; j++) {
                int randomI = (int) (Math.random() * arr.length);
                ArrayUtil.swap(arr, j, randomI);
            }

            //开始测试
            int res1 = bitFindNum(arr);
            int res2 = findNum(arr);
            if (res1 != res2) {
                ArrayUtil.output(arr);
                System.out.println(res1 + " " + res2);
                System.out.println("失败了");
                return;
            }


        }
        System.out.println("成功了");


    }

    public static int randomWithRange(int maxNum) {
        return Math.random() < 0.5 ? (int) (Math.random() * maxNum) * -1
                : (int) (Math.random() * maxNum);

    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxLen = 500;
        int maxNum = 100;
        test(testTime, maxLen, maxNum);
    }

}
