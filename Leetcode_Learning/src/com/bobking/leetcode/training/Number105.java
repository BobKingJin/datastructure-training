package com.bobking.leetcode.training;

import java.util.HashMap;

public class Number105 {

    // 参考：程序猿代码指南P172
    // 参考：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--22/
    public TreeNode buildTree1(int[] preorder, int[] inorder) {

        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0
            || preorder.length != inorder.length) {
            return null;
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart,
        int inEnd) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == preorder[preStart]) {
                root.left = buildTree(preorder, preStart + 1, preStart + (i - inStart), inorder,
                    inStart, i - 1);
                root.right = buildTree(preorder, preStart + (i - inStart) + 1, preEnd, inorder,
                    i + 1, inEnd);
                break;
            }
        }
        return root;
    }

    // 利用 hashmap 加速找到根节点 root 在中序数组中的位置
    // 参考：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--22/
    public TreeNode buildTree2(int[] preorder, int[] inorder) {

        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0
            || preorder.length != inorder.length) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart,
        int inEnd,
        HashMap<Integer, Integer> map) {
        if (preStart == preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = map.get(preorder[preStart]);
        int leftNum = rootIndex - inStart;
        root.left = buildTree(preorder, preStart + 1, preStart + leftNum + 1, inorder, inStart,
            rootIndex, map);
        root.right = buildTree(preorder, preStart + leftNum + 1, preEnd, inorder, rootIndex + 1,
            inEnd, map);
        return root;
    }
}
