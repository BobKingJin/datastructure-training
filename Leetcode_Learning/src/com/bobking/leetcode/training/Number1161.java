package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-07-31 8:17
 */
public class Number1161 {

    private List<Integer> sum = new ArrayList<Integer>();

    public int maxLevelSum1(TreeNode root) {

        dfs(root, 0);
        int ans = 0;

        for (int i = 0; i < sum.size(); ++i) {
            if (sum.get(i) > sum.get(ans))
                ans = i;
        }

        return ans + 1;
    }

    // 先序遍历
    private void dfs(TreeNode node, int level) {

        if (level == sum.size()) {
            sum.add(node.val);
        } else {
            sum.set(level, sum.get(level) + node.val);
        }

        if (node.left != null)
            dfs(node.left, level + 1);

        if (node.right != null)
            dfs(node.right, level + 1);
    }

    public int maxLevelSum2(TreeNode root) {

        int ans = 1;
        int maxSum = root.val;
        List<TreeNode> q = new ArrayList<TreeNode>();
        q.add(root);

        for (int level = 1; !q.isEmpty(); ++level) {
            List<TreeNode> nq = new ArrayList<TreeNode>();
            int sum = 0;
            for (TreeNode node : q) {
                sum += node.val;
                if (node.left != null)
                    nq.add(node.left);

                if (node.right != null)
                    nq.add(node.right);
            }
            if (sum > maxSum) {
                maxSum = sum;
                ans = level;
            }
            q = nq;
        }
        return ans;
    }
}
