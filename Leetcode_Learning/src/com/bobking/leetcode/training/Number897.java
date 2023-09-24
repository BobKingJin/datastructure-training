package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Number897 {

    List<TreeNode> res1 = new ArrayList<TreeNode>();

    public TreeNode increasingBST1(TreeNode root) {

        dfs(root);
        TreeNode dummy = new TreeNode(-1);
        TreeNode cur = dummy;
        for (TreeNode node : res1) {
            cur.right = node;
            node.left = null;
            cur = node;
        }

        return dummy.right;
    }

    private void dfs(TreeNode root) {

        if (root == null)
            return;

        dfs(root.left);
        res1.add(root);
        dfs(root.right);
    }

    List<TreeNode> res2 = new ArrayList<TreeNode>();

    public TreeNode increasingBST2(TreeNode root) {

        Deque<TreeNode> d = new ArrayDeque<TreeNode>();
        while (root != null || !d.isEmpty()) {
            while (root != null) {
                d.add(root);
                root = root.left;
            }
            root = d.pollLast();
            res2.add(root);
            root = root.right;
        }

        TreeNode dummy = new TreeNode(-1);
        TreeNode cur = dummy;

        for (TreeNode node : res2) {
            cur.right = node;
            node.left = null;
            cur = node;
        }

        return dummy.right;
    }


}
