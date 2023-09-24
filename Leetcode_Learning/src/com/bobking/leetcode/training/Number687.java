package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-02 22:07
 */
public class Number687 {

    private int max = 0;

    // 参考：https://leetcode.cn/problems/longest-univalue-path/solution/by-ac_oier-8ue8/
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {

        if (root == null)
            return 0;

        int ans = 0;
        int cur = 0;
        int l = dfs(root.left);
        int r = dfs(root.right);

        if (root.left != null && root.left.val == root.val) {
            ans = l + 1;
            cur += l + 1;
        }

        if (root.right != null && root.right.val == root.val) {
            ans = Math.max(ans, r + 1);
            cur += r + 1;
        }

        max = Math.max(max, cur);
        return ans;
    }
}
