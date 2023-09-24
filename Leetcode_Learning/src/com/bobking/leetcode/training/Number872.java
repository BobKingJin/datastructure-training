package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Number872 {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        dfs(root1, l1);
        dfs(root2, l2);

        if (l1.size() == l2.size()) {
            for (int i = 0; i < l1.size(); i++) {
                if (!l1.get(i).equals(l2.get(i)))
                    return false;
            }

            return true;
        }

        return false;
    }

    // 先序遍历
    private void dfs(TreeNode root, List<Integer> list) {

        if (root == null)
            return;
        // 叶子节点
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        dfs(root.left, list);
        dfs(root.right, list);
    }

    private void preOrder(TreeNode root, List<Integer> list) {

        Deque<TreeNode> d = new ArrayDeque<TreeNode>();
        while (root != null || !d.isEmpty()) {
            while (root != null) {
                d.addLast(root);
                root = root.left;
            }
            root = d.pollLast();
            if (root.left == null && root.right == null)
                list.add(root.val);
            root = root.right;
        }
    }
}
