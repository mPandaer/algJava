package space.pandaer.heap;

/*
线段重合问题，可以利用堆来解决。
问题描述：给定一些线段[start,end]找出这些线段中有最多多少线段重合了
核心思路：
1. 将线段按照 start排序
2. 以当前start为界限，不能超过这个界限的end淘汰。
疑问：为什么这个时候不看start了呢？
解释：因为按照start升序排好后，当前的start肯定比之前的大，所以只需要看之前的end超过当前的start就知道，之前的线段和现在的线段有么有重合。
于是我们就能求出，和当前线段重合的线段数，当把整个线段集合都这样求完之后，选择其中最大的，就是我们需要的结果。
总体思路：获取到当前线段和之前的线段的重合数，按照这个算法求出每个线段的重合数 取其最大的就是题目要求的答案了
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class HeapSegment {

    static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int searchLineSegment(int[][] lines) {

        //将数组转换成我们需要的Line O(n)
        Line[] lineList = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            lineList[i] = new Line(lines[i][0], lines[i][1]);
        }

        //排序 O(nlogn)
        Arrays.sort(lineList, Comparator.comparingInt(a -> a.start));

        //建立小跟堆，弹出不符合条件的end O(nlogn)?
        // 为什么呢？我们抛开循环，关注heap来分析，进入heap中的每一个数，然后堆化的时间复杂度是 O(logn)
        //heap中的数最多一次进，最多一次出，所以进出的总时间复杂度 2O(logn)
        //然后一共有，n个数所以 总的时间负责度 2O(nlogn) == O(nlogn)
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lineList.length; i++) {
            Line line = lineList[i];
            while (!heap.isEmpty() && line.start >= heap.peek()) {
                heap.poll();
            }
            heap.add(line.end);
            max = Math.max(max, heap.size());
        }

        return max;

    }


    //暴力办法
    /*
    根据题目的条件：重合的长度>=1 于是我们可以确定如果重合 肯定包含 x.5这个数，于是我们转换思路，
    去求x.5在包含在线段中的个数。次数最大的就是我们需要的答案
     */
    public static int searchSegment(int[][] lines) {
        //将数组转换成我们需要的Line O(n)
        Line[] lineList = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            lineList[i] = new Line(lines[i][0], lines[i][1]);
        }


        int start = findNum(lineList, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                //推理过程：
                //因为要找到start的最小值  ==> comparator.compare(ans,lines[i]) < 0 == ture && ans = lines[i];
                // ans 不是最小值 要重新赋值
                // ans.start > lines[i].start //就说明ans不是最小值
//                if (o1.start > o2.start) return -1;
//                else return 1;

                return o2.start - o1.start;

            }
        }).start;
        int end = findNum(lineList, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                // ans不是最大值，需要重新赋值
//                if (o1.end < o2.end) return -1;
//                else return 1;

                return o1.end - o2.end;
            }
        }).end;

        int ans = 0;
        for (int i = start; i < end; i++) {
            double num = i + 0.5;
            int count = 0;
            for (int j = 0; j < lineList.length; j++) {
                if (num >= lineList[j].start && num <= lineList[j].end) {
                    count++;
                }
            }
            ans = Math.max(ans, count);
        }


        return ans;


    }

    public static Line findNum(Line[] lines, Comparator<Line> comparator) {
        Line ans = lines[0];
        for (int i = 1; i < lines.length; i++) {
            if (comparator.compare(ans, lines[i]) < 0) {
                ans = lines[i];
            }
        }
        return ans;
    }


    public static int[][] getRandomLines(int maxLen, int maxNum) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[][] lines = new int[len][2];
        for (int i = 0; i < lines.length; i++) {
            lines[i][0] = (int) (Math.random() * maxNum);
            do {
                lines[i][1] = (int) (Math.random() * maxNum) + (int) (Math.random() * maxNum);
            } while (lines[i][1] <= lines[i][0]);
        }

        return lines;
    }


    public static void outputLines(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + ", " + arr[i][1]);
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int maxLen = 500;
        int maxNum = 1000;

        for (int i = 0; i < testTime; i++) {
            int[][] lines = getRandomLines(maxLen, maxNum);
            int ans1 = searchLineSegment(lines);
            int ans2 = searchSegment(lines);
            if (ans2 != ans1) {
                outputLines(lines);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("失败了");
                return;
            }

        }
        System.out.println("成功了");

    }

}
