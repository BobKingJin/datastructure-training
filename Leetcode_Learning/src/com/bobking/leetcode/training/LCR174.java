package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/1 10:21
 * @Author: BobKing
 * @Description:
 */
public class LCR174 {

    // 当 cnt >= 二叉树的总节点数时为非法, 默认返回 -1
    int res = -1;
    int cnt;

    public int findTargetNode(TreeNode root, int cnt) {
        this.cnt = cnt;
        dfs(root);
        return res;
    }

    // 先右 再中 后左
    private void dfs(TreeNode root) {
        if (root == null)
            return;
        dfs(root.right);
        if (cnt == 0)
            return;
        if (--cnt == 0)
            res = root.val;
        dfs(root.left);
    }

}
