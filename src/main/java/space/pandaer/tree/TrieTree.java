package space.pandaer.tree;

/*
    前缀树
    什么是前缀树？前缀树是一棵多叉树，主要目的是为了方便字符串的操作
    核心操作：
    1. 加入数据
    2. 寻找数据
    3. 判断是否存在前缀
    4. 删除数据
 */

//简化版本 只有26个小写的英文字母
//扩展如果不是 26个英文字母怎么写呢？
//主要解决的问题是什么呢？
/*
其实就是节点的路径问题，如果是26个英文字母的话，用数组装就可以了，
但是本质是什么，其实是映射 index ==> trieNode 通过 字符的编码来找到 对应的节点
所以本质既然是映射，那么就可以用hashMap来改进，进行增强。
 */
public class TrieTree {
    TrieNode root = new TrieNode();

    static class TrieNode {
        int pass;
        int end;
        TrieNode[] paths;

        public TrieNode() {
            pass = 0;
            end = 0;
            paths = new TrieNode[26];
        }
    }

    //加入操作
    public void insert(String word) {
        if (word == null) return;
        TrieNode tmp = root;
        char[] words = word.toCharArray();
        tmp.pass++;
        for (int i = 0; i < words.length; i++) {
            int index = words[i] - 'a';
            if (tmp.paths[index] == null) {
                tmp.paths[index] = new TrieNode();
            }
            tmp = tmp.paths[index];
            tmp.pass++;
        }
        tmp.end++;
    }

    //查找操作
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        TrieNode tmp = root;
        char[] words = word.toCharArray();

        for (int i = 0; i<words.length;i++) {
            int index = words[i] - 'a';
            if (tmp.paths[index] == null) {
                return 0;
            }
            tmp = tmp.paths[index];
        }
        return tmp.end;
    }

    //查找前缀
    public int preSearch(String word) {
        if (word == null) {
            return 0;
        }
        TrieNode tmp = root;
        char[] words = word.toCharArray();

        for (int i = 0; i<words.length;i++) {
            int index = words[i] - 'a';
            if (tmp.paths[index] == null) {
                return 0;
            }
            tmp = tmp.paths[index];
        }
        return tmp.pass;
    }


    //删除操作
    public void delete(String word) {
        //判断是否存在
        if (word == null || search(word) == 0) return;

        //到此就是存在
        TrieNode tmp = root;
        char[] words = word.toCharArray();
        tmp.pass--;
        for (int i = 0; i < words.length; i++) {
            int index = words[i] - 'a';
            tmp.paths[index].pass--;
            if (tmp.paths[index].pass == 0) {
                tmp.paths[index] = null;
                return;
            }
            tmp = tmp.paths[index];
        }
        tmp.end--;
    }


    //test
    public static void main(String[] args) {
        String[] words = {"pandaer","liwenhao","luoxi","abc","ab"};
        TrieTree tree = new TrieTree();
        for (int i = 0; i<words.length;i++) {
            tree.insert(words[i]);
        }
        System.out.println(tree.search("pandaer"));
        System.out.println(tree.search("li"));
        System.out.println(tree.preSearch("ab"));
        tree.delete("ab");
        System.out.println(tree.preSearch("ab"));
    }

}
