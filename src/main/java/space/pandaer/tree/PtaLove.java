package space.pandaer.tree;

import java.util.ArrayList;
import java.util.Scanner;

public class PtaLove {

    //统计异性出现次数
    public static void count(String person,String[] persons,int[] count) {
        char sex = person.charAt(0);
        int size = person.length() == 2 ? 0 : 1;
        for (String s : persons) {
            if (s==null || s.length() == person.length()) continue;
            count[Integer.parseInt(s.substring(size))]++;
        }
    }

    //寻找最大异性
    public static boolean find(int[] count,String src,String self) {
        ArrayList<Integer> maxList = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int size = self.length() == 2 ? 0 : 1;
        char sex = src.charAt(0);
        for (int j : count) {
            if (max < j) max = j;
        }
        for (int i = 0; i<count.length;i++) {
            if (count[i] == max) maxList.add(i);
        }
        if (maxList.contains(Integer.parseInt(src.substring(size)))) {
            System.out.println(self + " " + src);
            return true;
        }else {
            for (Integer integer : maxList) {
                System.out.println(self + " " + sex + integer);
            }
            return false;
        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int personNum = sc.nextInt();
        int time = sc.nextInt();
        int[] manCount = new int[personNum];
        int[] womanCount = new int[personNum];
        String man;
        String woman;
        ArrayList<String[]> total = new ArrayList<>();

        for (int i = 0;i < time;i++) {
            int len = sc.nextInt();
            String[] persons = new String[len];
            for (int j = 0; j<len;j++) {
                persons[0] = sc.next();
            }
            total.add(persons);
        }

        woman = sc.next();
        man = sc.next();

        for (String[] strings : total) {
            count(woman, strings, womanCount);
            count(man, strings, manCount);
        }

        if (!find(womanCount,man,woman)){
            find(manCount,woman,man);
        }


    }



}
