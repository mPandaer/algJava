package space.pandaer.bit;

import space.pandaer.sort.ArrayUtil;

import java.util.*;

public class BitFindTwoNum {

    //要求：利用位运算 寻找出两个出现奇数次数的数，其他的都是偶数
    //同样利用异或的性质，找到这两个数 a^b 然后在去寻找他们的不同，寻找出一个数出来。
    public static int[] bitFindTwoNum(int[] arr) {
        int twoXor = 0;
        for (int j : arr) {
            twoXor ^= j;
        }
        //到这里就寻找到了两个奇数次的数了
        //然后开始寻找其中的一个奇数
        //核心思路 ：
        /* 1. 找到twoXor中为1的一个位数，
        因为twoXor = a ^ b 所以如果有1 就表示a b 在这个位数上的 0 1 位不同
        所以我们只需要寻找一个位数上有一就可以了
        */
        int oneIndex = bitFindOneIndex(twoXor);
        if (oneIndex == -1) throw new IllegalArgumentException("数组不满足条件");
        int oneXor = 0;
        for (int j : arr) {
            if ((j & (1 << oneIndex)) != 0) {
                oneXor ^= j;
            }
        }
        //为了方便测试
        int[] res = {oneXor,twoXor^oneXor};
        Arrays.sort(res);
        return res;

    }



    public static int bitFindOneIndex(int num) {
        int res = num & (-num); //取出最右边那个一
        for (int i = 0; i<32;i++) {
            if ((res & (1<<i)) != 0) return i;
        }
        return -1;//如果没有1就返回-1 表示这个数是0
    }

    //hash的思路
    public static int[] hashFindTwoNum(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        //统计每个数出现的次数
        for (int num : arr) {
            map.merge(num,1,Integer::sum);
        }

        int[] res = new int[2];
        int index = 0;
        for (int num : map.keySet()) {
            if (map.get(num) % 2 != 0) {
                res[index++] = num;
            }
        }
        //方便测试
        Arrays.sort(res);
        return res;
    }


    //获得一个随机数
    public static int getRandomNum(int maxNum) {
        int flag = Math.random() < 0.5 ? -1 : 1;
        return (int) (Math.random() * maxNum) * flag; // (-maxNum,maxNum)
    }

    //随机一个奇数
    public static int getRandomOdd(int maxNum) {
        int num  = 0;
        do {
            num = (int) (Math.random() * maxNum);
        }while (num % 2 == 0);
        return num;
    }
    //随机一个偶数
    public static int getRandomEven(int maxNum) {
        int num  = 0;
        do {
            num = (int) (Math.random() * maxNum);
        }while (num % 2 != 0 || num == 0);
        return num;
    }

    public static int[] listToIntArray(List<Integer> list) {
        int[] res = new int[list.size()];
        int index = 0;
        for (int num : list) {
            res[index++] = num;
        }
        return res;
    }

    //获取满足条件的随机数组
    public static int[] getConditionedArrayWithRandom(int maxNumKinds,int maxNum) {
        int numKinds = (int) (Math.random() * maxNumKinds); //数的种类
        HashSet<Integer> set = new HashSet<>();
        // 先固定两个随机数
        int num1 = getRandomNum(maxNum);
        set.add(num1);
        int num2;
        do {
            num2 = getRandomNum(maxNum);
        }while (set.contains(num2));
        set.add(num2);
        //固定随机数的次数
        int time1 = getRandomOdd(maxNum);
        int time2 = getRandomOdd(maxNum);
        numKinds -= 2;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i<time1;i++) {
            list.add(num1);
        }
        for (int i = 0; i<time2;i++) {
            list.add(num2);
        }

        while(numKinds > 0) {
            //随机生成一个偶数次数
            int time = getRandomEven(maxNum);
            int num;
            do {
                num = getRandomNum(maxNum);
            }while (set.contains(num));
            for (int i = 0; i < time;i++) {
                list.add(num);
            }

            numKinds--;
        }

        return listToIntArray(list);


    }

    //对数器
    public static void test(int testTime,int maxKinds,int maxNum) {
        for (int i = 0; i<testTime;i++) {
            int[] arr = getConditionedArrayWithRandom(maxKinds,maxNum);
            int[] res1 = bitFindTwoNum(arr);
            int[] res2 = hashFindTwoNum(arr);
            boolean isTrue = ArrayUtil.isEquals(res1,res2);
            if (!isTrue) {
                ArrayUtil.output(arr);
                ArrayUtil.output(res1);
                ArrayUtil.output(res2);
                System.out.println("失败了");
                return;
            }
        }
        System.out.println("成功了");


    }

    //test
    public static void main(String[] args) {
        int testTime = 10000;
        int maxKinds = 40;
        int maxNum = 100;
        test(testTime,maxKinds,maxNum);
    }


}
