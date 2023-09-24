package com.bobking.leetcode.training;

public class Number572 {

    // 参考：程序猿代码指南P142
    // 其它解法可参考：https://leetcode-cn.com/problems/subtree-of-another-tree/solution/ling-yi-ge-shu-de-zi-shu-by-leetcode-solution/
    public boolean isSubtree(TreeNode s, TreeNode t) {

        if (t == null)
            return true;

        if (s == null)
            return false;

        return check(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean check(TreeNode node1, TreeNode node2) {

        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        if (node1.val != node2.val)
            return false;

        return check(node1.left, node2.left) && check(node1.right, node2.right);
    }
}
