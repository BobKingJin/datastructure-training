package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-06 12:04
 */
public class Number654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    private TreeNode construct(int[] nums, int l, int r) {

        if (l == r)
            return null;

        int maxIndex = max(nums, l, r);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = construct(nums, l, maxIndex);
        root.right = construct(nums, maxIndex + 1, r);
        return root;
    }

    // 返回 l - r 范围内的最大数角标
    private int max(int[] nums, int l, int r) {

        int maxIndex = l;
        for (int i = l; i < r; i++) {
            if (nums[maxIndex] < nums[i])
                maxIndex = i;
        }

        return maxIndex;
    }
}
