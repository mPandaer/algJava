package space.pandaer.tree;

//利用数组存储哈夫曼树
public class ArrayHaFuManTree {
    static class TreeNode {
        int weight; //节点的权重
        int parent; //父节点索引
        int left; //左孩子索引
        int right;//右孩子索引

        public TreeNode(int weight, int parent, int left, int right) {
            this.weight = weight;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("weight:%d parent:%d left:%d right:%d", weight, parent, left, right);
        }
    }

    //初始化权重
    public static void initWeight(TreeNode[] tree, int[] weight) {
        for (int i = 0; i < weight.length; i++) {
            TreeNode node = new TreeNode(weight[i], 0, 0, 0);
            tree[i + 1] = node; //将索引为零的空出来
        }
    }

    //寻找tree中两个最小的权重的索引
    public static int[] findTwoMinIndex(TreeNode[] tree, int offset, int weightLen) {
        //要满足条件parent中为0的两个小值
        int[] ans = new int[2];
        for (int i = 0; i < 2; i++) {
            int minIndex = -1;
            for (int j = 1; j <= weightLen + offset; j++) {
                if (tree[j].parent != 0) continue; //不是根节点跳过
                if (i > 0 && j == ans[i - 1]) continue; //是寻找到的最小值跳过
                if (minIndex != -1 && tree[minIndex].weight > tree[j].weight) {
                    minIndex = j;
                } else if (minIndex == -1) {
                    minIndex = j;
                }
            }
            ans[i] = minIndex;
        }
        return ans;
    }

    //构建哈夫曼树
    public static TreeNode[] buildArrayHaFuManTree(int[] weight) {
        TreeNode[] tree = new TreeNode[weight.length * 2];
        //初始化权重
        initWeight(tree, weight);
        int offset = 0;
        for (int i = weight.length + 1; i < weight.length * 2; i++) {
            int[] indexs = findTwoMinIndex(tree, offset, weight.length);
            tree[indexs[0]].parent = i;
            tree[indexs[1]].parent = i;
            tree[i] = new TreeNode(tree[indexs[0]].weight + tree[indexs[1]].weight, 0, indexs[0], indexs[1]);
            offset++;
        }
        return tree;
    }

    //输出哈夫曼树
    public static void output(TreeNode[] tree) {
        for (int i = 1; i < tree.length; i++) {
            System.out.println(tree[i]);
        }
    }


    //test
    public static void main(String[] args) {
        int[] weight = {5, 4, 3, 2, 1};
        TreeNode[] tree = buildArrayHaFuManTree(weight);
        output(tree);
    }

}
