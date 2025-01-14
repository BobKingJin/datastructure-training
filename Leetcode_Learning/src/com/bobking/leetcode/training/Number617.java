package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2020-12-07 21:56
 */
public class Number617 {

    // 参考：https://leetcode-cn.com/problems/merge-two-binary-trees/solution/dong-hua-yan-shi-di-gui-die-dai-617he-bing-er-cha-/
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        TreeNode result = null;

        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }

        // 先序遍历
        result = new TreeNode(t1.val + t2.val);
        result.left = mergeTrees(t1.left, t2.left);
        result.right = mergeTrees(t1.right, t2.right);

        return result;
    }
}
