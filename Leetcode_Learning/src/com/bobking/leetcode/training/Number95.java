package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.List;

public class Number95 {

    // 参考：程序猿代码指南P174
    public List<TreeNode> generateTrees(int n) {

        if (n < 1)
            return new LinkedList<TreeNode>();

        return generate(1, n);
    }

    private List<TreeNode> generate(int start, int end) {

        List<TreeNode> res = new LinkedList<TreeNode>();

        // 递归结束条件
        if (start > end)
            res.add(null);
        // 以每一个节点为头节点
        for (int i = start; i <= end; i++) {
            TreeNode head = new TreeNode(i);
            // 注意这个位置是递归 即 left 中的返回 已经是 start ~ (i - 1) 中的每个搜索二叉树中的头节点
            // 特别注意这个位置的头节点都是已经排好了的，即例如： n = 3，当以 1 为头节点时
            // left 为 null，right中有 3 和 2，同时 3 的左节点是 2，2的右节点是 3
            List<TreeNode> left = generate(start, i - 1);
            List<TreeNode> right = generate(i + 1, end);

            for (TreeNode lNode : left) {
                for (TreeNode rNode : right) {
                    head.left = lNode;
                    head.right = rNode;
                    // 为了不影响之前的节点, 每次都是重新复制一个新的记录
                    res.add(cloneTree(head));
                }
            }
        }

        return res;
    }

    private TreeNode cloneTree(TreeNode head) {

        if (head == null)
            return null;

        TreeNode res = new TreeNode(head.val);
        res.left = cloneTree(head.left);
        res.right = cloneTree(head.right);
        return res;
    }
}
