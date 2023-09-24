package com.bobking.leetcode.training;

public class Number1080 {

    public TreeNode sufficientSubset(TreeNode root, int limit) {

        boolean rootDeleted = dfs(root, 0, limit);

        if (rootDeleted)
            return null;

        return root;
    }

    private Boolean dfs(TreeNode node, int s, int limit) {

        if (node.left == null && node.right == null)
            return s + node.val < limit;

        // 本节点是否删除，依赖于它的左右孩子节点是否删除，所以采用先序遍历

        // 注意：如果 dfs 的返回值的意义是这个节点是否被删除，它们的默认值应该设置为 true
        boolean lTreeDeleted = true;
        boolean rTreeDeleted = true;

        // 如果有左子树，就先递归处理左子树
        if (node.left != null)
            lTreeDeleted = dfs(node.left, s + node.val, limit);

        // 如果有右子树，就先递归处理右子树
        if (node.right != null)
            rTreeDeleted = dfs(node.right, s + node.val, limit);

        if (lTreeDeleted)
            node.left = null;

        if (rTreeDeleted)
            node.right = null;

        // 只有左右子树都被删除了，自己才没有必要保留
        return lTreeDeleted && rTreeDeleted;
    }
}
