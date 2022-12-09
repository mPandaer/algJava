package space.pandaer.partition;

public class PartitionNum {
    //分区问题（荷兰国旗问题）

    /*
    * 要求：根据target的值 将数组分为两个区域 <= 区 和 > 区 要求 == 的最后
    *
    * 核心思路 ： 建立两个区 < 区 和 > 区，当这个区的数都找到后，==区的就完成了
    *
    * */
    public static void partitionNum(int[] arr,int target) {
        int lessR = -1;
        int moreL = arr.length;
        int index = 0;

        while (index < moreL) { //不撞区
            if (arr[index] == target) {
                index++;
            }else if (arr[index] > target) {
                swap(arr,index,--moreL);
            }else {
                swap(arr,index++,++lessR);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
