package space.pandaer.tree;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Comparator;

public class HaFuManTree {

    //树节点的定义
    static class TreeNode {
        Integer value;
        int weight;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(Integer val, int weight) {
            this.value = val;
            this.weight = weight;
        }


    }


    //构造哈夫曼树
    public static TreeNode buildHaFuMan(int[] weight, int[] val) {
        //生成森林
        ArrayList<TreeNode> list = new ArrayList<>();
        for (int i = 0; i < val.length; i++) {
            list.add(new TreeNode(val[i], weight[i]));
        }

        while (list.size() > 1) {
            list.sort((o1, o2) -> o1.weight - o2.weight);//排序
            TreeNode tmp1 = list.remove(0);
            TreeNode tmp2 = list.remove(0);
            TreeNode newNode = new TreeNode(null, tmp2.weight + tmp1.weight);

            newNode.left = tmp1;
            newNode.right = tmp2;
            list.add(newNode);
        }
        return list.get(0);

    }


    //输出哈夫曼树
    public static void preOutput(TreeNode root) {
        if (root == null) return;
        System.out.print(root.value + " ");
        preOutput(root.left);
        preOutput(root.right);
    }
    public static void inOutput(TreeNode root) {
        if (root == null) return;
        inOutput(root.left);
        System.out.print(root.value + " ");
        inOutput(root.right);
    }

    //Test
    public static void main(String[] args) {
        int[] testVal = {1,2,3,4,5};
        int[] weight = {6,4,3,2,1};
        TreeNode root = buildHaFuMan(weight,testVal);
        preOutput(root);
        System.out.println();
        inOutput(root);
    }


}
