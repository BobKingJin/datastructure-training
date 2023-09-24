package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-08-12 22:01
 */
public class Number81 {

    // 参考：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/solution/zai-javazhong-ji-bai-liao-100de-yong-hu-by-reedfan/
    public boolean search(int[] nums, int target) {

        if (nums == null || nums.length == 0)
            return false;

        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {

            mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return true;
            // 10111 和 11101 此种情况下 nums[start] == nums[mid]
            // 分不清到底是前面有序还是后面有序，此时 start++ 即可，相当于去掉一个重复的干扰项
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            // 前半部分有序
            if (nums[start] < nums[mid]) {
                // target 在前半部分
                if (nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {
                    // 否则，去后半部分找
                    start = mid + 1;
                }
            } else {
                // 后半部分有序
                // target 在后半部分
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {
                    // 否则，去后半部分找
                    end = mid - 1;
                }
            }
        }

        return false;
    }
}
