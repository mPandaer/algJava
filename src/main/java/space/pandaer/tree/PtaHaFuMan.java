package space.pandaer.tree;


//通过哈夫曼算法确定每个字母的编码长度，进行判断。
//1.生成哈夫曼树（实际是一张表）
//2.根据这张表确定每个字母的编码长度
//3.将生成的编码长度和提供的编码长度进行对比即可


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class PtaHaFuMan {
    //定义树节点
    static class TreeNode {
        String value;
        int weight; //权重
        int parent;//父节点索引
        int left;//左孩子节点索引
        int right;//右孩子节点索引

        public TreeNode() {
        }

        public TreeNode(String value, int weight, int parent, int left, int right) {
            this.value = value;
            this.weight = weight;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("value:%s weight:%d parent:%d left:%d right:%d", value, weight, parent, left, right);
        }
    }


    //1.生成哈夫曼树（实际是一张表）
    public static TreeNode[] buildTree(int[] weight, String[] values) {
        //初始化森林
        TreeNode[] tree = new TreeNode[2 * weight.length]; //根据哈夫曼的定义节点数为 2n-1 方便起见0不用
        initTree(tree, weight, values);
        //寻找根节点中权重最小的两个索引
        int offset = 0;//每次融合都会多一个

        for (int i = weight.length + 1; i < tree.length; i++) {
            int[] minIndexes = findTwoMinIndex(tree, weight.length, offset);
            TreeNode node1 = tree[minIndexes[0]];
            TreeNode node2 = tree[minIndexes[1]];
            tree[i] = new TreeNode(null, node1.weight + node2.weight, 0, minIndexes[0], minIndexes[1]);
            node1.parent = i;
            node2.parent = i;
            offset++;
        }

        return tree;
    }

    private static int[] findTwoMinIndex(TreeNode[] tree, int length, int offset) {
        int[] ans = new int[2];
        for (int i = 0; i < 2; i++) {
            int minIndex = -1;
            for (int j = 1; j <= length + offset; j++) {
                if (tree[j].parent != 0) continue; //不是根节点
                if (i > 0 && j == ans[i - 1]) continue; //已经记录过的最小值索引
                if (minIndex == -1) {
                    minIndex = j;
                } else {
                    if (tree[minIndex].weight > tree[j].weight) {
                        minIndex = j;
                    }
                }
            }
            ans[i] = minIndex;
        }

        return ans;
    }


    private static void initTree(TreeNode[] tree, int[] weight, String[] values) {
        //索引0不用
        for (int i = 0; i < weight.length; i++) {
            TreeNode node = new TreeNode(values[i], weight[i], 0, 0, 0);
            tree[i + 1] = node;
        }
    }

    //output
    public static void output(TreeNode[] tree) {
        for (int i = 1; i < tree.length; i++) {
            System.out.println(i + " " + tree[i]);
        }
    }


    //2.根据这张表确定每个字母的编码长度
    public static HashMap<String, Integer> haFuManCode(TreeNode[] tree, int len) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i <= len; i++) {
            int codeLen = 0;
            int index = i;
            while (tree[index].parent != 0) {
                codeLen++;
                index = tree[index].parent;
            }
            map.put(tree[i].value, codeLen);
        }
        return map;
    }

    //3.将生成的编码长度和提供的编码长度进行对比即可
    public static boolean isTrue(HashMap<String, Integer> std, String[] values, int[] weight, String[] code) {
        int srcLen = 0;
        int stdLen = 0;
        for (int i = 0; i < values.length; i++) {
            srcLen += weight[i] * code[i].length();
            stdLen += std.get(values[i]) * weight[i];
        }
        return srcLen == stdLen;
    }

    //判断是否前缀编码
    public static boolean isPrefix(String[] code) {
        Arrays.sort(code, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        for (int i = 0; i < code.length - 1; i++) {
            for (int j = i + 1; j < code.length; j++) {
                if ((code[j]).startsWith(code[i])) return false;
            }
        }

        return true;
    }


    //test
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt(); //读取字符长度
        String[] values = new String[len];
        int[] weight = new int[len];
        String[] code = new String[len];
        for (int i = 0; i < len; i++) {
            values[i] = sc.next();
            weight[i] = sc.nextInt();
        }
        int testTime = sc.nextInt();
        TreeNode[] tree = buildTree(weight, values);
        HashMap<String, Integer> map = haFuManCode(tree, weight.length);
        for (int i = 0; i < testTime; i++) {
            int index = 0;
            for (int j = 0; j < values.length; j++) {
                sc.next();
                String tmpCode = sc.next();
                code[index++] = tmpCode;

            }

            boolean ans = isTrue(map, values, weight, code) && isPrefix(code);
            if (ans) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }


        }


    }


}
