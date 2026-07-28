package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Number230 {

    // 参考：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/solution/er-cha-sou-suo-shu-zhong-di-kxiao-de-yuan-su-by-le/
    public int kthSmallest1(TreeNode root, int k) {
        List<Integer> numContainer = new ArrayList<Integer>();
        inorder(root, numContainer);
        return numContainer.get(k - 1);
    }

    // 中序遍历
    private void inorder(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    // 参考：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/solution/er-cha-sou-suo-shu-zhong-di-kxiao-de-yuan-su-by-le/
    public int kthSmallest2(TreeNode root, int k) {

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        // 中序遍历
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
    }


}
