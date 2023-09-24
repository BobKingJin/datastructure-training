package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

public class Number938 {

    private int low;
    private int high;
    int ans;

    public int rangeSumBST1(TreeNode root, int low, int high) {

        this.low = low;
        this.high = high;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {

        if (root == null)
            return;

        dfs(root.left);

        if (root.val >= low && root.val <= high)
            ans += root.val;

        dfs(root.right);
    }

    public int rangeSumBST2(TreeNode root, int low, int high) {

        int ans = 0;
        Deque<TreeNode> d = new ArrayDeque<TreeNode>();

        // 中序遍历  左 -> 中 -> 右
        while (root != null || !d.isEmpty()) {

            while (root != null) {
                d.addLast(root);
                root = root.left;
            }

            root = d.pollLast();

            if (low <= root.val && root.val <= high)
                ans += root.val;

            root = root.right;
        }

        return ans;
    }

}
