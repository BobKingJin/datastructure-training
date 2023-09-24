package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2022-06-11 1:15
 */
public class Number404 {

    public int sumOfLeftLeaves1(TreeNode root) {
        return root != null ? dfs(root) : 0;
    }

    private int dfs(TreeNode node) {

        int ans = 0;

        if (node.left != null)
            ans += isLeafNode(node.left) ? node.left.val : dfs(node.left);

        if (node.right != null && !isLeafNode(node.right))
            ans += dfs(node.right);

        return ans;
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public int sumOfLeftLeaves2(TreeNode root) {

        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (isLeafNode(node.left)) {
                    ans += node.left.val;
                } else {
                    queue.offer(node.left);
                }
            }

            if (node.right != null)
                if (!isLeafNode(node.right))
                    queue.offer(node.right);
        }
        return ans;
    }

}
