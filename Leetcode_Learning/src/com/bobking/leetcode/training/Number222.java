package com.bobking.leetcode.training;

public class Number222 {

    // 参考：程序猿代码指南P176
    public int countNodes(TreeNode root) {

        if (root == null)
            return 0;

        return bs(root, 1, getHeight(root, 1));
    }

    private int bs(TreeNode node, int level, int height) {

        if (level == height)
            return 1;

        // 判断 node 节点的右孩子的最左节点能否到达最后一层节点
        if (getHeight(node.right, level + 1) == height) {
            return (1 << (height - level)) + bs(node.right, level + 1, height);
        } else {
            return (1 << (height - level - 1)) + bs(node.left, level + 1, height);
        }
    }

    private int getHeight(TreeNode node, int height) {

        while (node != null) {
            height++;
            node = node.left;
        }

        return height - 1;
    }
}
