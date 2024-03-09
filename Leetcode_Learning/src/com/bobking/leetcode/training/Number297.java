package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

public class Number297 {

    // 参考：程序猿代码指南P107
    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {

        if (root == null)
            return "#!";
        // 按先序序列化
        String res = root.val + "!";
        res += serialize1(root.left);
        res += serialize1(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {

        String[] values = data.split("!");

        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i < values.length; i++)
            queue.offer(values[i]);

        return reconByPreOrder1(queue);
    }

    private TreeNode reconByPreOrder1(Queue<String> queue) {

        String value = queue.poll();
        if ("#".equals(value))
            return null;

        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = reconByPreOrder1(queue);
        head.right = reconByPreOrder1(queue);
        return head;
    }

    // 参考：程序猿代码指南P107
    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {

        if (root == null)
            return "#!";
        // 按层序列化
        String res = root.val + "!";

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                res += root.val + "!";
                queue.offer(root.left);
            } else {
                res += "#!";
            }
            if (root.right != null) {
                res += root.right + "!";
                queue.offer(root.right);
            } else {
                res += "#!";
            }
        }

        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {

        String[] values = data.split("!");
        int index = 0;
        TreeNode head = generateTreeNodeByString(values[index++]);

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(head);
        TreeNode node = null;

        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateTreeNodeByString(values[index++]);
            node.right = generateTreeNodeByString(values[index++]);

            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }

        return head;
    }

    private TreeNode generateTreeNodeByString(String value) {

        if ("#".equals(value))
            return null;

        return new TreeNode(Integer.valueOf(value));
    }
}
