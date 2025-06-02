package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/6/2 11:09
 * @Author: BobKing
 * @Description:
 */
public class Number2563 {

    // 参考: https://leetcode.cn/problems/count-the-number-of-fair-pairs/solutions/2107079/er-fen-cha-zhao-de-ling-huo-yun-yong-by-wplbj/?envType=daily-question&envId=2025-06-02
    public long countFairPairs1(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;
        for (int j = 0; j < nums.length; j++) {
            // 注意要在 [0, j - 1] 中二分, 因为题目要求两个下标 i < j
            int r = lowerBound(nums, j, upper - nums[j] + 1);
            int l = lowerBound(nums, j, lower - nums[j]);
            ans += r - l;
        }
        return ans;
    }

    private int lowerBound(int[] nums, int right, int target) {
        int left = -1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    // 参考: https://leetcode.cn/problems/count-the-number-of-fair-pairs/solutions/2107079/er-fen-cha-zhao-de-ling-huo-yun-yong-by-wplbj/?envType=daily-question&envId=2025-06-02
    public long countFairPairs2(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;
        int l = nums.length;
        int r = nums.length;
        for (int j = 0; j < nums.length; j++) {
            while (r > 0 && nums[r - 1] > upper - nums[j]) {
                r--;
            }
            while (l > 0 && nums[l - 1] >= lower - nums[j]) {
                l--;
            }
            // 在方法一中，二分的结果必须 <= j，方法二同理
            ans += Math.min(r, j) - Math.min(l, j);
        }
        return ans;
    }


}
