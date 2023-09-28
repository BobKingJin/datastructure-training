package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Number98 {

    public boolean isValidBST1(TreeNode root) {

        if (root == null || (root.left == null && root.right == null))
            return true;
        // 用 list 记录已经遍历的数
        List<Integer> list = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 中序遍历
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (!list.isEmpty() && root.val <= list.get(list.size() - 1))
                    return false;
                list.add(root.val);
                root = root.right;
            }
        }

        return true;
    }

    // 参考：https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
    public boolean isValidBST2(TreeNode root) {

        if (root == null || (root.left == null && root.right == null))
            return true;

        // 上一个节点的值
        // Double.MIN_VALUE是一个正数，是最接近 0 的正数
        double pre = -Double.MAX_VALUE;

        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                // 不用判空
                if (root.val <= pre)
                    return false;
                pre = root.val;
                root = root.right;
            }
        }

        return true;
    }

    // 参考：https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
    public boolean isValidBST3(TreeNode root) {

        if (root == null || (root.left == null && root.right == null))
            return true;

        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // 递归
    private boolean isValidBST(TreeNode root, long small, long big) {

        if (root == null)
            return true;

        // 中序遍历
        if (root.val <= small || root.val >= big)
            return false;

        return isValidBST(root.left, small, root.val) && isValidBST(root.right, root.val, big);
    }
}
