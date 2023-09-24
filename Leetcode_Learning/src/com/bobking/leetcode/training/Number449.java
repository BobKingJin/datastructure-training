package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-09-17 12:15
 */
public class Number449 {

    // 参考：https://leetcode.cn/problems/serialize-and-deserialize-bst/solution/by-ac_oier-ncwn/
    private class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {

            if (root == null)
                return null;

            List<String> list = new ArrayList<String>();
            dfs1(root, list);
            int n = list.size();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < n; i++) {
                sb.append(list.get(i));
                if (i != n - 1)
                    sb.append(",");
            }
            return sb.toString();
        }

        private void dfs1(TreeNode root, List<String> list) {

            if (root == null)
                return ;
            // 先序遍历
            list.add(String.valueOf(root.val));
            dfs1(root.left, list);
            dfs1(root.right, list);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null)
                return null;

            String[] ss = data.split(",");
            return dfs2(0, ss.length - 1, ss);
        }

        private TreeNode dfs2(int l, int r, String[] ss) {

            if (l > r)
                return null;

            int j = l + 1;
            int t = Integer.parseInt(ss[l]);
            TreeNode ans = new TreeNode(t);

            while (j <= r && Integer.parseInt(ss[j]) <= t)
                j++;

            ans.left = dfs2(l + 1, j - 1, ss);
            ans.right = dfs2(j, r, ss);

            return ans;
        }

    }
}
