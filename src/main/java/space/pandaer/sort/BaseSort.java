package space.pandaer.sort;



/*
基数排序 -- 桶排序也叫容器排序 根据容器的有序性，使得数据也变的有序
核心思路也很简单 那是因为他也有其限制 排序的一定是非负整数
1. 求出最大的数字的位数
2. 然后根据位数从低位到高位排序
3. 最后完成排序
 */
public class BaseSort {

    public static void baseSort(int[] arr) {
        int digit = getDigit(findMax(arr));
        int[] count = new int[10];
        int[] help = new int[arr.length];
        int index = 0;
        for (int i = 0; i<digit;i++) {

            for (int j = 0; j<arr.length;j++) {
                count[getNumWithDigit(arr[j],i+1)]++;
            }
            //变成前缀和数组
            for (int j = 1;j<count.length;j++) {
                count[j] = count[j-1] + count[j];
            }
            for (int j = arr.length-1; j>=0;j--) {
                int helpIndex = --count[getNumWithDigit(arr[j],i+1)];
                help[helpIndex] = arr[j];
            }

            //倒回去
            for (int j = 0; j<arr.length;j++) {
                arr[j] = help[j];
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

    public static int getDigit(int num) {
        if (num == 0) return 1;
        int count = 0;
        while (num != 0) {
            count++;
            num /=10;
        }
        return count;
    }


    public static int getNumWithDigit(int num,int digit) {
        if (num == 0) return num;
        for (int i = 0; i<digit-1;i++) {
            num /= 10;
        }
        return num % 10;
    }



    //test
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,9,8,7,6,5,4,3,2,1};
        baseSort(arr);
        ArrayUtil.output(arr);
    }

}
