package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author BobKing
 * @create 2021-04-17 11:27
 */
public class Number145 {

    List<Integer> res = new ArrayList<Integer>();

    // 参考：程序猿代码指南P93
    public List<Integer> postorderTraversal1(TreeNode root) {

        if (root == null)
            return res;

        postorderTraversal1(root.left);
        postorderTraversal1(root.right);
        res.add(root.val);
        return res;
    }

    // 参考：程序猿代码指南P93
    public List<Integer> postorderTraversal2(TreeNode root) {

        if (root == null)
            return res;

        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(root);

        while (!stack1.isEmpty()) {

            root = stack1.pop();
            stack2.push(root);
            if (root.left != null)
                stack1.push(root.left);
            if (root.right != null)
                stack1.push(root.right);
        }

        while (!stack2.isEmpty())
            res.add(stack2.pop().val);

        return res;
    }

    // 参考：程序猿代码指南P93
    public List<Integer> postorderTraversal3(TreeNode root) {

        if (root == null)
            return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode c = null;

        while (!stack.isEmpty()) {
            c = stack.peek();
            if (c.left != null && root != c.left && root != c.right) {
                stack.push(c.left);
            } else if (c.right != null && root != c.right) {
                stack.push(c.right);
            } else {
                res.add(stack.pop().val);
                root = c;
            }
        }

        return res;
    }
}


