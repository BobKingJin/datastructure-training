package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-10-30 12:06
 */
public class Number783 {

    public int minDiffInBST1(TreeNode root) {

        List<Integer> list = new ArrayList<Integer>();
        Deque<TreeNode> d = new ArrayDeque<TreeNode>();
        d.addLast(root);
        while (!d.isEmpty()) {
            TreeNode poll = d.pollFirst();
            list.add(poll.val);
            if (poll.left != null)
                d.addLast(poll.left);
            if (poll.right != null)
                d.addLast(poll.right);
        }

        // dfs1(root, list);

        Collections.sort(list);
        int n = list.size();
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int cur = Math.abs(list.get(i) - list.get(i - 1));
            ans = Math.min(ans, cur);
        }
        return ans;
    }

    // 先序遍历
    private void dfs1(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null)
            dfs1(root.left, list);
        if (root.right != null)
            dfs1(root.right, list);
    }

    int ans = Integer.MAX_VALUE;
    TreeNode prev = null;

    public int minDiffInBST2(TreeNode root) {

        Deque<TreeNode> d = new ArrayDeque<TreeNode>();
        // 先序遍历
        while (root != null || !d.isEmpty()) {
            while (root != null) {
                d.addLast(root);
                root = root.left;
            }
            root = d.pollLast();
            if (prev != null)
                ans = Math.min(ans, Math.abs(prev.val - root.val));
            prev = root;
            root = root.right;
        }

        // dfs2(root);

        return ans;
    }

    private void dfs2(TreeNode root) {

        if (root == null)
            return;

        dfs2(root.left);

        if (prev != null)
            ans = Math.min(ans, Math.abs(prev.val - root.val));

        prev = root;
        dfs2(root.right);
    }
}
