package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-07-09 18:31
 */
public class Number283 {

    // 参考：https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
    public void moveZeroes1(int[] nums) {

        if (nums == null || nums.length <= 1)
            return;

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                nums[j++] = nums[i];
        }

        for (int i = j; i < nums.length; i++)
            nums[i] = 0;
    }

    // 参考：https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
    // 同解法三
    public void moveZeroes2(int[] nums) {

        if (nums == null || nums.length <= 1)
            return;

        int index = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                swap(nums, i, ++index);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 参考：https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
    public void moveZeroes3(int[] nums) {

        if (nums == null || nums.length <= 1)
            return;

        // 两个指针 i 和 j
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // 当前元素不等于 0，就把其交换到左边，等于 0 的交换到右边
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }
}
