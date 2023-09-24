package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-09-10 15:29
 */
public class Number655 {

    int h;
    int m;
    int n;
    List<List<String>> ans;

    // 参考：https://leetcode.cn/problems/print-binary-tree/comments/
    // 参考：https://leetcode.cn/problems/print-binary-tree/solution/by-ac_oier-mays/
    public List<List<String>> printTree(TreeNode root) {
        dfs1(root, 0);
        m = h + 1;
        n = (1 << (h + 1)) - 1;
        ans = new ArrayList<List<String>>();

        for (int i = 0; i < m; i++) {
            List<String> cur = new ArrayList<String>();
            for (int j = 0; j < n; j++)
                cur.add("");
            ans.add(cur);
        }

        // 先找到树的最大深度即数组的长度 m, 那么树的宽度就是 n = 2^m - 1
        // 接下来就是递归了，选中间的位置放树根，这样数组分为两部分，[l, mid], [mid, r] 分别给左子树和右子树
        // 计算位置时均是在 mid = (l + r) / 2 处放树根值
        dfs2(root, 0, (n - 1) / 2);

        return ans;
    }

    // 先序遍历
    private void dfs1(TreeNode root, int depth) {
        if (root == null)
            return;
        h = Math.max(h, depth);
        dfs1(root.left, depth + 1);
        dfs1(root.right, depth + 1);
    }

    private void dfs2(TreeNode root, int x, int y) {
        if (root == null)
            return;
        ans.get(x).set(y, String.valueOf(root.val));
        dfs2(root.left, x + 1, y - (1 << (h - x - 1)));
        dfs2(root.right, x + 1, y + (1 << (h - x - 1)));
    }

}
