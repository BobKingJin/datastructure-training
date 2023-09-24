package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-08-12 21:00
 */
public class Number35 {

    // 参考：https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
    public int searchInsert(int[] nums, int target) {

        if (nums[nums.length - 1] < target)
            return nums.length;
        if (nums[0] > target)
            return 0;

        // 走到这里一定有 nums[len - 1] >= target
        int left = 0;
        int right = nums.length - 1;
        // 在区间 nums[left...right] 里查找第 1 个大于等于 target 的元素的下标
        while (left < right) {

            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 下一轮搜索的区间是 [mid + 1..right]
                left = mid + 1;
            } else {
                // 下一轮搜索的区间是 [left..mid]
                right = mid;
            }
        }

        return left;
    }

}
