package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-30 12:12
 */
public class Number1026 {

    int res = Integer.MIN_VALUE;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null)
            return 0;
        // 如果当前节点没有子节点，则直接返回
        dfs(root, root.val, root.val);
        return res;
    }

    // 每条从根节点到叶子节点的路径中的最大值和最小值，并求出差值更新全局变量
    private void dfs(TreeNode node, int max, int min) {

        if (node == null)
            return;

        max = Math.max(node.val, max);
        min = Math.min(node.val, min);

        // 到达叶子节点，求最大差值
        if (node.left == null && node.right == null)
            res = Math.max(res, Math.abs(max - min));

        dfs(node.left, max, min);
        dfs(node.right, max, min);
    }
}
