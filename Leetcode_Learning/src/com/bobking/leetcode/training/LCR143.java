package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/1 10:33
 * @Author: BobKing
 * @Description:
 */
public class LCR143 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null)
                    && (recursion(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean recursion(TreeNode A, TreeNode B) {
        if (B == null)
            return true;
        if (A == null || A.val != B.val)
            return false;
        return recursion(A.left, B.left) && recursion(A.right, B.right);
    }


}
