package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Number1302 {

    public int deepestLeavesSum1(TreeNode root) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Deque<TreeNode> d = new ArrayDeque<TreeNode>();
        d.addLast(root);
        int depth = 0;

        // 层次遍历
        while (!d.isEmpty()) {

            int sz = d.size();
            while (sz-- > 0) {
                TreeNode node = d.pollFirst();
                map.put(depth, map.getOrDefault(depth, 0) + node.val);
                if (node.left != null)
                    d.addLast(node.left);
                if (node.right != null)
                    d.addLast(node.right);
            }
            depth++;
        }

        return map.get(depth - 1);
    }

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    int max;

    public int deepestLeavesSum2(TreeNode root) {
        dfs(root, 0);
        return map.get(max);
    }

    private void dfs(TreeNode root, int depth) {

        if (root == null)
            return ;
        // 先序遍历
        max = Math.max(max, depth);
        map.put(depth, map.getOrDefault(depth, 0) + root.val);
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}
