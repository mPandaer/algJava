package space.pandaer.tree;

import java.util.HashMap;

public class TrieTree2 {

    TrieNode root = new TrieNode();

    static class TrieNode {
        int pass;
        int end;
        HashMap<Integer, TrieNode> paths;

        public TrieNode() {
            pass = 0;
            end = 0;
            paths = new HashMap<>();
        }
    }


    //insert
    public void insert(String word) {
        if (word == null) return;
        TrieNode tmp = root;
        tmp.pass++;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int index = words[i]; //拿到索引
            if (!tmp.paths.containsKey(index)) {
                tmp.paths.put(index, new TrieNode());
            }
            tmp = tmp.paths.get(index);
            tmp.pass++;
        }
        tmp.end++;

    }

    //search
    public int find(String word) {
        if (word == null) return 0;
        TrieNode tmp = root;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int index = words[i]; //拿到索引
            if (!tmp.paths.containsKey(index)) {
                return 0;
            }
            tmp = tmp.paths.get(index);
        }
        return tmp.end;

    }

    //preSearch
    public int preSearch(String word) {
        if (word == null) return 0;
        TrieNode tmp = root;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int index = words[i]; //拿到索引
            if (!tmp.paths.containsKey(index)) {
                return 0;
            }
            tmp = tmp.paths.get(index);
        }
        return tmp.pass;
    }

    //delete
    public void delete(String word) {
        if (word == null || find(word) == 0) return;
        //到此就是存在
        TrieNode tmp = root;
        root.pass--;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int index = words[i];
            int count = --tmp.paths.get(index).pass;
            if (count == 0) {
                tmp.paths.remove(index);
                return;
            }
            tmp = tmp.paths.get(index);
        }
        tmp.end--;

    }

}
