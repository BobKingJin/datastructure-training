package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-10 23:20
 */
public class LCP67 {

    // 参考：https://leetcode.cn/problems/KnLfVT/solution/jijian-by-endlesscheng-4oqo/
    public TreeNode expandBinaryTree(TreeNode root) {

        // 先序遍历
        if (root == null || (root.left == null && root.right == null))
            return root;
        if (root.left != null)
            root.left = new TreeNode(-1, expandBinaryTree(root.left), null);
        if (root.right != null)
            root.right = new TreeNode(-1, null, expandBinaryTree(root.right));
        return root;
    }
}
