package com.bobking.leetcode.training;

import java.util.*;

public class Number144 {

    // 参考：程序猿代码指南P93
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<Integer>();

        if (root == null)
            return res;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return res;
    }
}
