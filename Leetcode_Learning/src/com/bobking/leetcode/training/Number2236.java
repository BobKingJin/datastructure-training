package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-20 8:35
 */
public class Number2236 {

    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }
}
