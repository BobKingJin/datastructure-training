package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-02-08 10:38
 */
public class Number1233 {

    // 参考：https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/solution/python3javacgo-yi-ti-shuang-jie-pai-xu-z-dha2/
    public List<String> removeSubfolders1(String[] folder) {

        // 先将数组 folder 按照字典序排序，然后遍历数组
        // 对于当前遍历到的文件夹 f，如果它的长度大于等于答案数组中最后一个文件夹的长度
        // 并且它的前缀包含答案数组的最后一个文件夹再加上一个 /，则说明 f 是答案数组中最后一个文件夹的子文件夹，不需要将其加入答案数组中
        // 否则将 f 加入答案数组中

        Arrays.sort(folder);
        List<String> ans = new ArrayList<String>();
        ans.add(folder[0]);

        for (int i = 1; i < folder.length; ++i) {
            int m = ans.get(ans.size() - 1).length();
            int n = folder[i].length();
            if (m >= n || !(ans.get(ans.size() - 1).equals(folder[i].substring(0, m)) && folder[i].charAt(m) == '/'))
                ans.add(folder[i]);
        }
        return ans;
    }

    private class Trie {

        private Map<String, Trie> children = new HashMap<String, Trie>();
        private int fid = -1;

        public void insert(int fid, String f) {
            Trie node = this;
            String[] ps = f.split("/");
            for (int i = 1; i < ps.length; ++i) {
                String p = ps[i];
                if (!node.children.containsKey(p))
                    node.children.put(p, new Trie());
                node = node.children.get(p);
            }
            node.fid = fid;
        }

        public List<Integer> search() {
            List<Integer> ans = new ArrayList<Integer>();
            dfs(this, ans);
            return ans;
        }

        private void dfs(Trie root, List<Integer> ans) {
            if (root.fid != -1) {
                ans.add(root.fid);
                return;
            }
            for (Trie child : root.children.values())
                dfs(child, ans);
        }
    }

    // 参考：https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/solution/python3javacgo-yi-ti-shuang-jie-pai-xu-z-dha2/
    public List<String> removeSubfolders2(String[] folder) {

        Trie trie = new Trie();
        for (int i = 0; i < folder.length; ++i)
            trie.insert(i, folder[i]);

        List<String> ans = new ArrayList<String>();
        for (int i : trie.search())
            ans.add(folder[i]);

        return ans;
    }
}
