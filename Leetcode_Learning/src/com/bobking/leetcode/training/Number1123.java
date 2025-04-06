package com.bobking.leetcode.training;

/**
 * @Date: 2025/4/6 12:40
 * @Author: BobKing
 * @Description:
 */
public class Number1123 {

    private TreeNode ans;
    private int maxDepth = -1;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            maxDepth = Math.max(maxDepth, depth);
            return depth;
        }
        // 左子树最深空节点的深度
        int leftMaxDepth = dfs(node.left, depth + 1);
        // 右子树最深空节点的深度
        int rightMaxDepth = dfs(node.right, depth + 1);
        // 最深的空节点左右子树都有
        if (leftMaxDepth == rightMaxDepth && leftMaxDepth == maxDepth) {
            ans = node;
        }
        // 当前子树最深空节点的深度
        return Math.max(leftMaxDepth, rightMaxDepth);
    }


}
