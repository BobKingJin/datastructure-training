package com.bobking.leetcode.training;

import java.util.HashMap;

public class Number106 {

    HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
    int[] post;

    // 参考：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/tu-jie-gou-zao-er-cha-shu-wei-wan-dai-xu-by-user72/
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        for (int i = 0; i < inorder.length; i++)
            memo.put(inorder[i], i);

        post = postorder;
        TreeNode root = buildTree(0, inorder.length - 1, 0, post.length - 1);
        return root;
    }

    private TreeNode buildTree(int is, int ie, int ps, int pe) {

        if (ie < is || pe < ps)
            return null;

        int root = post[pe];
        int ri = memo.get(root);

        TreeNode node = new TreeNode(root);
        node.left = buildTree(is, ri - 1, ps, ps + ri - is - 1);
        node.right = buildTree(ri + 1, ie, ps + ri - is, pe - 1);
        return node;
    }
}
