package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-06 17:18
 */
public class Number2331 {

    public boolean evaluateTree1(TreeNode root) {

        if (root.left == null)
            return root.val == 1;
        // 后序遍历
        // 会完整地遍历所有节点
        boolean l = evaluateTree1(root.left);
        boolean r = evaluateTree1(root.right);
        return root.val == 2 ? l || r : l && r;
    }

    public boolean evaluateTree2(TreeNode root) {

        if (root.left == null)
            return root.val == 1;
        // 利用了逻辑表达式短路的性质，不一定会遍历到所有节点
        // 比如求 &&，如果左子树是 false 了，就不需要递归右子树了
        if (root.val == 2) {
            return evaluateTree2(root.left) || evaluateTree2(root.right);
        } else {
            return evaluateTree2(root.left) && evaluateTree2(root.right);
        }
    }
}
