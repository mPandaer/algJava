package space.pandaer.bit;

import space.pandaer.sort.ArrayUtil;

import java.util.HashMap;
import java.util.HashSet;

public class BitFindNumKM {

    /**
     * 在数组中有一个数出现了 k 次 其他的数都出现了 m次，要求利用位运算找出这个出现k次的数 k < m && m > 1
     * 核心思路
     * 1. 一个数在计算机底层是32位，所以我们可以用一个32长度长的数组存储每一位出现1的次数
     * 2.我们根据 出现的次数 = k * a的那位1 + m * b的那位1 很容易得出，出现的次数 % m == k
     */
    public static int bitFindNumKM(int[] arr, int k, int m) {
        int[] bits = new int[32];

        for (int num : arr) {

            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    bits[i]++;
                }
            }

        }
        int ans = 0;

        for (int i = 0; i<bits.length;i++) {
            if (bits[i] % m != 0) {
                ans |= (1 << i);
            }
        }


        return ans;
    }

    //hash思想
    public static int hashFindNumKM(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            map.merge(num, 1, Integer::sum);
        }

        for (int key : map.keySet()) {
            if (map.get(key) == k) return key;
        }
        //不会通过这个返回
        return -1;
    }


    //生成随机数
    public static int getRandomNum(int maxNum) {
        int flag = Math.random() < 0.5 ? -1 : 1;
        return (int) (Math.random() * maxNum) * flag;
    }

    //生成符合条件的数组
    public static int[] getConditionedArray(int maxKinds, int maxNum, int k, int m) {
        int kinds = (int) (Math.random() * maxKinds) + 1;
        HashSet<Integer> set = new HashSet<>();
        int[] arr = new int[k + (kinds - 1) * m];
        int index = 0;
        int target = getRandomNum(maxNum); //生成k次数的目标值
        set.add(target);
        for (int i = 0; i < k; i++) {
            arr[index++] = target;
        }
        kinds--;
        while (kinds > 0) {
            int num;
            do {
                 num = getRandomNum(maxNum);
            }while (set.contains(num));
            for (int i = 0; i < m; i++) {
                arr[index++] = num;
            }
            kinds--;
        }

        return arr;
    }

    //对数器
    public static void test(int testTime, int maxKinds, int maxNum, int k, int m) {
        for (int i = 0; i < testTime; i++) {
            int[] res = getConditionedArray(maxKinds, maxNum, k, m); //获取到随机数组
            int res1 = bitFindNumKM(res, k, m);
            int res2 = hashFindNumKM(res, k, m);
            if (res1 != res2) {
                ArrayUtil.output(res);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println("失败了");
                return;
            }
        }

        System.out.println("成功了");
    }


    //test
    public static void main(String[] args) {
        int testTime = 100000;
        int maxKinds = 40;
        int maxNum = 100;
        int k = (int) (Math.random() * 10) + 1;
        int m;
        do {
            m = (int) (Math.random() * 10) + 1;
        }while (m == k);

        test(testTime,maxKinds,maxNum,k,m);
    }


}
