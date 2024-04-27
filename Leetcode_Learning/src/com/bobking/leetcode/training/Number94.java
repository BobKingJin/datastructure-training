package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Number94 {

    // 参考：程序猿代码指南P95
    public List<Integer> inorderTraversal1(TreeNode root) {

        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (root != null || !stack.isEmpty()) {
            // 将整个左边界压入栈中
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    // 参考：程序猿代码指南P94
    public List<Integer> inorderTraversal2(TreeNode root) {

        List<Integer> list = new ArrayList<Integer>();

        if (root == null)
            return list;

        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode node, List<Integer> list) {

        if (node == null)
            return;

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}
