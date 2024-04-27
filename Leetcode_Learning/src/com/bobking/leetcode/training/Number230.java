package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;

public class Number230 {

    // 参考：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/solution/er-cha-sou-suo-shu-zhong-di-kxiao-de-yuan-su-by-le/
    public int kthSmallest1(TreeNode root, int k) {

        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);
    }

    // 中序遍历
    private ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> list) {

        if (root == null)
            return list;

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
        return list;
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
            if (--k == 0)
                return root.val;
            root = root.right;
        }
    }


}
