package com.bobking.leetcode.training;

public class Number100 {

    // 参考：https://leetcode-cn.com/problems/same-tree/solution/xie-shu-suan-fa-de-tao-lu-kuang-jia-by-wei-lai-bu-/
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // 都为空
        if (p == null && q == null)
            return true;
        // 一个为空，一个非空
        if (p == null || q == null)
            return false;

        if (p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


}
