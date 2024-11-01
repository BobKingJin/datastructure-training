package com.bobking.leetcode.training;

public class Number110 {

    private class ReturnType {

        boolean isBalanced;
        int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    // 参考：程序猿代码指南P146
    public boolean isBalanced1(TreeNode root) {

        if (root == null)
            return true;

        return process(root).isBalanced;
    }

    public ReturnType process(TreeNode node) {

        if (node == null)
            return new ReturnType(true, 0);
        // 后序遍历
        // 先判断左子树和右子树
        ReturnType left = process(node.left);
        ReturnType right = process(node.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) < 2;

        return new ReturnType(isBalanced, height);
    }

    public boolean isBalanced2(TreeNode root) {
        return recursion(root) != -1;
    }

    private int recursion(TreeNode root) {
        if (root == null)
            return 0;
        int left = recursion(root.left);
        if (left == -1)
            return -1;
        int right = recursion(root.right);
        if (right == -1)
            return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    public boolean isBalanced3(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced3(root.left) && isBalanced3(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

}
