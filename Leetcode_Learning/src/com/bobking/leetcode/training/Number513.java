package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

public class Number513 {

    public int findBottomLeftValue1(TreeNode root) {

        Deque<TreeNode> d = new ArrayDeque<TreeNode>();
        d.addLast(root);
        int ans = 0;
        while (!d.isEmpty()) {
            int sz = d.size();
            ans = d.peek().val;
            while (sz-- > 0) {
                TreeNode poll = d.pollFirst();
                if (poll.left != null)
                    d.addLast(poll.left);
                if (poll.right != null)
                    d.addLast(poll.right);
            }
        }

        return ans;
    }

    private int max;
    private int ans;

    public int findBottomLeftValue2(TreeNode root) {
        dfs(root, 1);
        return ans;
    }

    private void dfs(TreeNode root, int depth) {

        if (root == null)
            return;

        if (depth > max){
            max = depth;
            ans = root.val;
        }

        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }

}
