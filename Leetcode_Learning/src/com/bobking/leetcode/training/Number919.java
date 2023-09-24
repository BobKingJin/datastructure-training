package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Queue;

public class Number919 {

    private class CBTInserter {

        Queue<TreeNode> candidate;
        TreeNode root;

        public CBTInserter(TreeNode root) {

            this.candidate = new ArrayDeque<TreeNode>();
            this.root = root;

            Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
            queue.offer(root);
            // 按层遍历
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                if (node.left != null)
                    queue.offer(node.left);

                if (node.right != null)
                    queue.offer(node.right);

                // 孩子节点有一个为 null
                if (!(node.left != null && node.right != null))
                    candidate.offer(node);
            }
        }

        public int insert(int val) {

            TreeNode child = new TreeNode(val);
            TreeNode node = candidate.peek();
            int res = node.val;
            if (node.left == null) {
                node.left = child;
            } else {
                node.right = child;
                // 原本左孩子节点不为 null，现在补上了右孩子节点，孩子节点双全
                // 弹出
                candidate.poll();
            }
            candidate.offer(child);
            return res;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}
