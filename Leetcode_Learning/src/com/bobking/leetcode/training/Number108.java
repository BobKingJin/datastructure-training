package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-04-24 8:16
 */
public class Number108 {

    // 参考：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-33/
    public TreeNode sortedArrayToBST(int[] nums) {

        if (nums == null)
            return null;

        if (nums.length == 1)
            return new TreeNode(nums[0]);

        return generate(nums, 0, nums.length - 1);
    }

    private TreeNode generate(int[] nums, int start, int end) {

        // 递归结束条件
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        TreeNode head = new TreeNode(nums[mid]);
        head.left = generate(nums, start, mid - 1);
        head.right = generate(nums, mid + 1, end);

        return head;
    }
}
