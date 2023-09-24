package com.bobking.leetcode.training;

public class Number700 {

    // 参考：https://leetcode-cn.com/problems/search-in-a-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-sou-suo-by-leetcode/
    // 参考：https://leetcode-cn.com/problems/same-tree/solution/xie-shu-suan-fa-de-tao-lu-kuang-jia-by-wei-lai-bu-/
    public TreeNode searchBST1(TreeNode root, int val) {

        if (root == null || val == root.val)
            return root;

        return val < root.val ? searchBST1(root.left, val) : searchBST1(root.right, val);
    }

    // 参考：https://leetcode-cn.com/problems/search-in-a-binary-search-tree/solution/er-cha-sou-suo-shu-zhong-de-sou-suo-by-leetcode/
    public TreeNode searchBST2(TreeNode root, int val) {

        while (root != null && root.val != val)
            root = val < root.val ? root.left : root.right;

        return root;
    }


}
