package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-28 0:30
 */
public class Number111 {

    // 后序遍历
    public int minDepth(TreeNode root) {

        if(root == null)
            return 0;

        if(root.left == null && root.right == null)
            return 1;

        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);

        // 其中一个节点为空，说明 m1 和 m2 有一个必然为 0，所以可以返回 m1 + m2 + 1
        if(root.left == null || root.right == null)
            return m1 + m2 + 1;
        // 左右孩子都不为空，返回最小深度 +1 即可
        return Math.min(m1, m2) + 1;
    }
}
