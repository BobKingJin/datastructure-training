package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-28 11:41
 */
public class Number80 {

    // 参考：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/solution/gong-shui-san-xie-guan-yu-shan-chu-you-x-glnq/
    public int removeDuplicates1(int[] nums) {

        if(nums == null)
            return 0;

        if(nums.length <= 2)
            return nums.length;

        // 为了让解法更具有一般性，将原问题的「保留 2 位」修改为「保留 k 位」
        // 1、由于是保留 k 个相同数字，对于前 k 个数字，可以直接保留
        // 2、对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留
        return process(nums, 2);
    }

    private int process(int[] nums, int k) {

        int u = 0;
        for (int x : nums) {
            if (u < k || nums[u - k] != x)
                nums[u++] = x;
        }

        return u;
    }

    // 参考：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-yec2/
    public int removeDuplicates2(int[] nums) {

        int n = nums.length;
        if (n <= 2)
            return n;

        // 前两位直接保留
        int slow = 2;
        int fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }

}
