package space.pandaer.merge;

public class MergeCountChildren {


    /*
     * 要求：给定范围 [lower,upper] 求数组中子数组中的和在此范围中的有多少个
     * 暴力思路：
     * 1. 0-0 0-1 0-2 ...
     * 2. 1-1 1-2 1-3 ...
     * 3. 2-2 2-3 2-4 ...
     * 我们发现一个规律，在一趟的遍历中，开始的位置没有变，那么我们换个思路，让结束位置不变，开始的位置变化
     * 例如：0-0
     *      0-1 1-1
     * 所以任意位置的和 [i,j] = [0,j] - [0,i-1] 于是得到一个方程
     *   lower <= [i,j]的和 <= upper
     *   lower <= ([0,j] - [0,i-1])的结果 <= upper
     * 于是我们再利用前缀和数组，[i,j]的和 = preSum[j] - preSum[i-1]
     * lower <= preSum[j] - preSum[i] <= upper
     * preSum[j] - upper <= preSum[i] <= preSum[j] - lower //这个很关键
     * 然后利用归并的思想，将其时间复杂度降低为 O(n*logn)
     * */
    public static int mergeCountChildren(int[] arr, int lower, int upper) {
        if (arr == null || arr.length < 1) return 0; //排除没有的情况
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i< sum.length;i++) {
            sum[i] = sum[i-1] + arr[i];
        }

        return process(sum, 0, sum.length - 1, lower, upper);

    }

    // 作用：在 [L..R]上 有多少个 前缀和在给定的范围上
    //原始的数组不传进来了，传入前缀和数组
    public static int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            if (sum[L] >= lower && sum[L] <= upper) return 1;
            else return 0;
        }


        //到此范围不止一个
        int mid = L + ((R - L) >> 1);
        int leftPart = process(sum, L, mid, lower, upper);
        int rightPart = process(sum, mid + 1, R, lower, upper);
        int merge = merge(sum, L, mid, R, lower, upper);
        return leftPart + rightPart + merge;


    }

    public static int merge(long[] sum, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;

        //统计个数
        for (int i = M + 1; i <= R; i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;

            while(windowL <= M && sum[windowL] < min) {
                windowL++;
            }
            while (windowR<=M && sum[windowR] <= max) {
                windowR++;
            }

            ans += windowR - windowL;

        }


        //正常合并
        long[] help = new long[R - L + 1];
        int index = 0;
        int p1 = L;
        int p2 = M + 1;

        while(p1 <= M && p2 <= R) {
            help[index++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }

        while(p1 <= M) {
            help[index++] = sum[p1++];
        }

        while(p2 <= R) {
            help[index++] = sum[p2++];
        }

        //copy回去

        for (int i = 0; i<help.length;i++) {
            sum[L + i] = help[i];
        }



        return ans;
    }

}
