package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-25 8:11
 */
public class Interview04_06 {

    // 参考：https://leetcode.cn/problems/successor-lcci/solution/by-ac_oier-xib5/
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        if (root == null)
            return null;

        if (root.val <= p.val)
            return inorderSuccessor(root.right, p);

        TreeNode ans = inorderSuccessor(root.left, p);
        return ans == null ? root : ans;
    }
}
