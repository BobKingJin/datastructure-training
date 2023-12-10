package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/4 8:18
 * @Author: BobKing
 * @Description:
 */
public class Number1038 {

    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {

        if (root != null) {
            bstToGst(root.right);
            sum = sum + root.val;
            root.val = sum;
            bstToGst(root.left);
        }
        return root;
    }
}
