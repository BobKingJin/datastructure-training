package com.bobking.leetcode.training;

public class Number543 {

    private class ReturnType {
        int maxDistance;
        int height;

        public ReturnType(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    // 参考：程序猿代码指南P168
    public int diameterOfBinaryTree(TreeNode root) {

        if (root == null)
            return 0;

        return recursion(root).maxDistance;
    }

    private ReturnType recursion(TreeNode node) {

        if (node == null)
            return new ReturnType(0, 0);
        // 后序遍历  -> 由下至上
        ReturnType left = recursion(node.left);
        ReturnType right = recursion(node.right);

        // 注意这个位置要更新 height  即叶子节点的 height 为 1
        int height = Math.max(left.height, right.height) + 1;
        int maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), left.height + right.height);
        return new ReturnType(maxDistance, height);
    }
}
