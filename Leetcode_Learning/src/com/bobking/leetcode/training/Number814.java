package com.bobking.leetcode.training;

public class Number814 {

    // 参考：
    public TreeNode pruneTree(TreeNode root) {

        if (root == null)
            return null;

        // 后续遍历
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.left != null || root.right != null)
            return root;

        return root.val == 0 ? null : root;
    }
}
