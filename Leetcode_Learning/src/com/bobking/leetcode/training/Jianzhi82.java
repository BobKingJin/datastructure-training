package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/20 15:58
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi82 {

    // 参考: Number113
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum, 0);
    }

    public boolean dfs(TreeNode root, int targetSum, int toal) {

        if (root == null) {
            return false;
        }

        toal += root.val;
        if (root.left == null && root.right == null && toal == targetSum) {
            return true;
        }

        return dfs(root.left, targetSum, toal) || dfs(root.right, targetSum, toal);
    }

}
