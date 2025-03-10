package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/1 11:29
 * @Author: BobKing
 * @Description:
 */
public class LCR176 {

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
