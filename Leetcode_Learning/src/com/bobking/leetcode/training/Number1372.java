package com.bobking.leetcode.training;

public class Number1372 {

    int ans = 0;

    public int longestZigZag(TreeNode root) {
        // 0：左节点
        dfs(root.left, 0, 1);
        // 1：右节点
        dfs(root.right, 1, 1);
        return ans;
    }

    private void dfs(TreeNode root, int dir, int dis) {

        if (root == null)
            return;

        ans = Math.max(ans, dis);

        // 如果当前节点是其父节点的右孩子
        if (dir == 1) {
            dfs(root.left, 0, dis + 1);
            dfs(root.right, 1, 1);
        } else {    // 如果当前节点是其父节点的左孩子
            dfs(root.left, 0, 1);
            dfs(root.right, 1, dis + 1);
        }
    }
}
