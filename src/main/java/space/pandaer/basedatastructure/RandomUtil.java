package space.pandaer.basedatastructure;

public class RandomUtil {


    //生成随机数
    public static int randomNum(int maxNum) {
        int flag = Math.random() < 0.5 ? -1 : 1;
        return (int) (Math.random() * maxNum) * flag;
    }

    public static int randomLen(int maxLen) {
        return (int) (Math.random() * maxLen) + 1;
    }
}
