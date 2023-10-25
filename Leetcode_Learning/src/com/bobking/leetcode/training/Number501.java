package com.bobking.leetcode.training;

import java.util.ArrayList;

/**
 * @author BobKing
 * @create 2023-10-24 7:35
 */
public class Number501 {

    ArrayList<Integer> res = new ArrayList<Integer>();
    int count = 0;
    int maxCount = Integer.MIN_VALUE;
    TreeNode prev = null;

    public int[] findMode(TreeNode root) {

        dfs(root);
        return res.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void dfs(TreeNode root) {

        if (root == null)
            return;

        // 中序遍历
        dfs(root.left);

        if (prev != null && root.val == prev.val) {
            count++;
        } else {
            count = 1;
        }

        if (count == maxCount)
            res.add(root.val);

        if (count > maxCount) {
            maxCount = count;
            res.clear();
            res.add(root.val);
        }

        prev = root;
        dfs(root.right);
    }
}
