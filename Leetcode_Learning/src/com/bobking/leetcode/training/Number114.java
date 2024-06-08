package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @author BobKing
 * @create 2021-03-20 16:42
 */
public class Number114 {

    // 参考：程序猿代码指南P94 二叉树先序遍历
    public void flatten1(TreeNode root) {

        if (root == null)
            return;

        TreeNode pre = null;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (pre != null) {
                pre.left = null;
                pre.right = root;
            }
            pre = root;
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
    }

    // 参考：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
    // 同解法五
    public void flatten2(TreeNode root) {

        if (root == null)
            return;

        while (root != null) {
            // 左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找到左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null)
                    pre = pre.right;
                // 将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    private TreeNode pre = null;

    // 参考：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
    public void flatten3(TreeNode root) {

        if (root == null)
            return;
        // 先右  后左  最后根节点
        // 从 底 向 上
        flatten3(root.right);
        flatten3(root.left);

        root.right = pre;
        root.left = null;
        pre = root;
    }

    // 参考：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
    public void flatten4(TreeNode root) {

        Stack<TreeNode> toVisit = new Stack<TreeNode>();
        TreeNode cur = root;
        TreeNode pre = null;

        while (cur != null || !toVisit.isEmpty()) {
            while (cur != null) {
                // 添加根节点
                toVisit.push(cur);
                // 递归添加右节点
                cur = cur.right;
            }
            // 已经访问到最右的节点了
            cur = toVisit.peek();
            // 在不存在左节点或者右节点已经访问过的情况下，访问根节点
            if (cur.left == null || cur.left == pre) {
                toVisit.pop();
                /**************修改的地方***************/
                cur.right = pre;
                cur.left = null;
                /*************************************/
                pre = cur;
                cur = null;
            } else {
                // 左节点还没有访问过就先访问左节点
                cur = cur.left;
            }
        }
    }

    // 同解法二
    public void flatten5(TreeNode root) {

        if (root == null)
            return;

        TreeNode cur = root;
        while (cur != null) {
            // 找到当前节点的左子树的最右节点
            if (cur.left != null) {
                TreeNode next = cur.left;
                // 前继节点
                TreeNode predecessor = next;
                while (predecessor.right != null)
                    predecessor = predecessor.right;

                predecessor.right = cur.right;
                cur.left = null;
                cur.right = next;
            }
            cur = cur.right;
        }
    }
}
