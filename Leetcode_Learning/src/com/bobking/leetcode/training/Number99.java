package com.bobking.leetcode.training;

import java.util.Stack;

public class Number99 {

    // 参考：程序猿代码指南P137
    public void recoverTree(TreeNode root) {

        if (root == null)
            return;

        TreeNode[] nodes = getTwoErrorNodes(root);
        int temp = nodes[0].val;
        nodes[0].val = nodes[1].val;
        nodes[1].val = temp;
    }

    public TreeNode[] getTwoErrorNodes(TreeNode root) {

        if (root == null)
            return null;

        TreeNode[] res = new TreeNode[2];
        // 中序遍历
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 用于记录上一个节点
        TreeNode pre = null;

        while (!stack.isEmpty() || root != null) {

            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null && pre.val > root.val) {
                    res[0] = res[0] == null ? pre : res[0];
                    res[1] = root;
                }
                pre = root;
                root = root.right;
            }
        }

        return res;
    }
}
