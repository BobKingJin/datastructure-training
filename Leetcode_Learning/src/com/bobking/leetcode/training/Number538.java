package com.bobking.leetcode.training;

public class Number538 {

    private int sum = 0;

    // 参考：https://leetcode-cn.com/problems/convert-bst-to-greater-tree/solution/ba-er-cha-sou-suo-shu-zhuan-huan-wei-lei-jia-sh-14/
    public TreeNode convertBST(TreeNode root) {

        // 反序中序遍历：先右孩子后左孩子
        // dfs
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }

        return root;
    }
}
