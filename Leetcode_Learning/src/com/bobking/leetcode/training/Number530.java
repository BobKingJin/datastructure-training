package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-03 7:56
 */
public class Number530 {

    private int pre;
    private int ans;

    public int getMinimumDifference(TreeNode root) {

        ans = Integer.MAX_VALUE;
        pre = -1;
        inOrder(root);
        return ans;
    }

    private void inOrder(TreeNode root) {

        if (root == null)
            return;

        inOrder(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        inOrder(root.right);
    }
}
