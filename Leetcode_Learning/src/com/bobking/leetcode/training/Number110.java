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
    public boolean isBalanced(TreeNode root) {

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
}
