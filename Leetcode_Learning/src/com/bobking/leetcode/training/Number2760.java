package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-16 7:59
 */
public class Number2760 {

    public int longestAlternatingSubarray(int[] nums, int threshold) {

        int len = nums.length;
        int ans = 0;
        // cnt 表示以当前元素为结尾的子数组中, 所有元素不超过 threshold 且元素是奇偶相间的子数组长度
        int cnt = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] > threshold) {
                cnt = 0;
            } else {
                if (i == 0 || nums[i] % 2 == nums[i - 1] % 2) {
                    cnt = 1;
                } else {
                    cnt++;
                }
                // 这里检查子数组的开头是奇数还是偶数, 如果是奇数, 那么得把开头去掉
                ans = Math.max(ans, cnt - nums[i - cnt + 1] % 2);
            }
        }
        return ans;
    }
}
