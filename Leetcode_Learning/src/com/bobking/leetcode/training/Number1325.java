package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/3 12:20
 * @Author: BobKing
 * @Description:
 */
public class Number1325 {

    public TreeNode removeLeafNodes(TreeNode root, int target) {

        if (root == null)
            return root;

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target)
            return null;

        return root;
    }
}
