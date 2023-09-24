package com.bobking.leetcode.training;

public class Number333 {

    private class ReturnType {

        // 左子树最大值
        int max;
        // 右子树最小值
        int min;
        // 最大搜索二叉树头节点
        TreeNode maxBSTHead;
        int maxBSTSize;

        public ReturnType(TreeNode maxBSTHead, int maxBSTSize, int max, int min) {
            this.maxBSTHead = maxBSTHead;
            this.maxBSTSize = maxBSTSize;
            this.max = max;
            this.min = min;
        }
    }

    // 参考：程序猿代码指南P121
    public int largestBSTSubtree(TreeNode root) {

        if (root == null)
            return 0;

        return process(root).maxBSTSize;
    }

    private ReturnType process(TreeNode node) {

        if (node == null)
            return new ReturnType(null, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // 后序遍历
        // dfs
        ReturnType left = process(node.left);
        ReturnType right = process(node.right);

        int min = Math.min(node.val, Math.min(left.min, right.min));
        int max = Math.max(node.val, Math.max(left.max, right.max));

        int maxBSTSize = Math.max(left.maxBSTSize, right.maxBSTSize);
        TreeNode maxBSTHead = left.maxBSTSize >= right.maxBSTSize ? left.maxBSTHead : right.maxBSTHead;

        if (left.maxBSTHead == node.left && right.maxBSTHead == node.right && node.val > left.max && node.val < right.min) {
            maxBSTSize = left.maxBSTSize + right.maxBSTSize + 1;
            maxBSTHead = node;
        }

        return new ReturnType(maxBSTHead, maxBSTSize, max, min);
    }
}
