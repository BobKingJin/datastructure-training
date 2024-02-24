package com.bobking.leetcode.training;

public class Number33 {

    // 参考：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/
    // 对比Number153
    public int search(int[] nums, int target) {

        if (nums == null || nums.length < 1)
            return -1;

        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;

        // 因为在没有旋转之前 nums 是有序的，那么在旋转之后必有一部分是有序的，因为旋转之后整个数组只存在一个逆对序
        // 除了 [0, 1, 2, 4, 5, 6, 7] -> [7, 6, 5, 4, 2, 1, 0] 这种情况
        // 同时有如果 nums[mid] > nums[left] 那么左半部分有序，反之右半部分有序
        // 例如：[0, 1, 2, 4, 5, 6, 7] 当把大于一半的数移到左边  例如：[2, 4, 5, 6, 7, 0, 1] 那么左半部分有序
        // 当把小于一半的数移到左边  例如：[6, 7, 0, 1, 2, 4, 5] 那么右半部分有序，必属于情况之一，所以必有一部分有序
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;

        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            // 左半部分有序
            if (nums[mid] >= nums[l]) {
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 右半部分有序
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return -1;
    }
}
