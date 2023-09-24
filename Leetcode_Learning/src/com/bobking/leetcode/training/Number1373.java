package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-20 7:27
 */
public class Number1373 {

    private class Node {
        public int minVal;
        public int maxVal;
        public int sum;
        public boolean isBi;

        public Node(int minVal, int maxVal, int sum, boolean isBi) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.sum = sum;
            this.isBi = isBi;
        }
    }

    int res = Integer.MIN_VALUE;

    public int maxSumBST(TreeNode root) {
        dfs(root);
        return (res > 0 ? res : 0);
    }

    // 返回值: {min, max, sum, isBiSearchTree}
    private Node dfs(TreeNode node) {

        if (node == null)
            return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);

        Node leftVec = dfs(node.left);
        Node rightVec = dfs(node.right);
        // 更新当前子树的和
        int sum = leftVec.sum + rightVec.sum + node.val;
        boolean isBi = false;
        // 大于左子树最大值，说明比左子树所有元素都大
        // 小于右子树最小值，说明比右子树所有元素都小
        if (node.val > leftVec.maxVal && node.val < rightVec.minVal) {
            // 左子树和右子树是二叉搜索树
            if (leftVec.isBi && rightVec.isBi)
                isBi = true;
        }

        if (isBi)
            res = Math.max(res, sum);

        // 更新当前子树的最大值和最小值
        int maxVal = Math.max(Math.max(leftVec.maxVal, rightVec.maxVal), node.val);
        int minVal = Math.min(Math.min(leftVec.minVal, rightVec.minVal), node.val);
        return new Node(minVal, maxVal, sum, isBi);
    }

}
