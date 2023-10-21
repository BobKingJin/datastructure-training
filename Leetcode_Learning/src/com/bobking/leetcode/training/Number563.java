package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-20 7:35
 */
public class Number563 {

    public int findTilt1(TreeNode root) {
        if (root == null)
            return 0;
        return findTilt1(root.left) + findTilt1(root.right) + Math.abs(getSum(root.left) - getSum(root.right));
    }

    private int getSum(TreeNode root) {
        if (root == null)
            return 0;
        return getSum(root.left) + getSum(root.right) + root.val;
    }

    int ans;

    public int findTilt2(TreeNode root) {
        dfs(root);
        return ans;
    }

    // 后序遍历
    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int l = dfs(root.left);
        int r = dfs(root.right);
        ans += Math.abs(l - r);
        return l + r + root.val;
    }
}
