package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-03-14 11:31
 */
public class Number540 {

    // 参考：https://leetcode-cn.com/problems/single-element-in-a-sorted-array/solution/you-xu-shu-zu-zhong-de-dan-yi-yuan-su-by-leetcode/
    public int singleNonDuplicate1(int[] nums) {

        if (nums == null)
            return 0;
        if (nums.length == 1)
            return nums[0];

        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (low < high) {

            mid = low + (high - low) / 2;
            // 即判断右半部分的个数是否是偶数
            boolean rightPartIsEven = (high - mid) % 2 == 0;

            if (nums[mid] == nums[mid + 1]) {
                if (rightPartIsEven) {
                    low = mid + 2;
                } else {
                    high = mid - 1;
                }
            } else if (nums[mid] == nums[mid - 1]) {
                if (rightPartIsEven) {
                    high = mid - 2;
                } else {
                    low = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }

        return nums[low];
    }

    // 参考：https://leetcode-cn.com/problems/single-element-in-a-sorted-array/solution/you-xu-shu-zu-zhong-de-dan-yi-yuan-su-by-leetcode/
    public int singleNonDuplicate2(int[] nums) {

        if (nums == null)
            return 0;
        if (nums.length == 1)
            return nums[0];

        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (mid % 2 != 0)
                mid--;
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }
}
