package com.bobking.leetcode.training;

import java.util.Stack;

public class Number112 {

    // 参考：https://leetcode-cn.com/problems/path-sum/solution/lu-jing-zong-he-by-leetcode-solution/
    public boolean hasPathSum1(TreeNode root, int targetSum) {

        if (root == null)
            return false;

        if (root.left == null && root.right == null)
            return targetSum == root.val;

        return hasPathSum1(root.left, targetSum - root.val) || hasPathSum1(root.right, targetSum - root.val);
    }

    // 参考：https://leetcode-cn.com/problems/path-sum/solution/lu-jing-zong-he-by-leetcode-solution/
    public boolean hasPathSum2(TreeNode root, int targetSum) {

        if (root == null)
            return false;

        // 先序遍历
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        Stack<Integer> valueStack = new Stack<Integer>();

        nodeStack.push(root);
        valueStack.push(root.val);

        while (!nodeStack.isEmpty()) {

            TreeNode node = nodeStack.pop();
            int value = valueStack.pop();

            if (node.left == null && node.right == null) {
                if (value == targetSum)
                    return true;
                continue;
            }

            if (node.right != null) {
                nodeStack.push(node.right);
                valueStack.push(value + node.right.val);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
                valueStack.push(value + node.left.val);
            }
        }

        return false;
    }
}
