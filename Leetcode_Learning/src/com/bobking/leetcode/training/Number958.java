package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2021-04-23 22:23
 */
public class Number958 {

    // 参考：程序猿代码指南P150
    public boolean isCompleteTree1(TreeNode root) {

        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        // 叶节点开关
        boolean leaf = false;
        TreeNode left = null;
        TreeNode right = null;

        while (!queue.isEmpty()) {

            root = queue.poll();
            left = root.left;
            right = root.right;

            if ((left == null && right != null) || (leaf && (left != null || right != null)))
                return false;

            if (left != null)
                queue.offer(left);

            if (right != null) {
                queue.offer(right);
            } else {
                leaf = true;
            }
        }

        return true;
    }

    // 二叉树中的节点个数
    int count = 0;
    // 二叉树中最后一个节点的编号
    int lastNum = 0;

    public boolean isCompleteTree2(TreeNode root) {

        if (root == null)
            return true;
        // 第一个节点编号为 1
        recursion(root, 1);
        return count == lastNum;
    }

    // num表示节点编号
    private void recursion(TreeNode node, int num) {

        if (node == null)
            return;

        count++;
        lastNum = Math.max(lastNum, num);

        recursion(node.left, 2 * num);
        recursion(node.right, 2 * num + 1);
    }
}
