package com.bobking.leetcode.training;

/**
 * @Date: 2025/9/6 14:13
 * @Author: BobKing
 * @Description:
 */
public class Number1493 {

    // 参考: https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/solutions/3702202/hua-dong-chuang-kou-qiu-zui-chang-python-fa07/?envType=daily-question&envId=2025-09-06
    public int longestSubarray(int[] nums) {
        int ans = 0;
        int cnt0 = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // 维护窗口中的 0 的个数
            cnt0 += 1 - nums[right];
            while (cnt0 > 1) {
                cnt0 -= 1 - nums[left];
                left++;
            }
            // 注意不是 right - left + 1, 因为要删掉一个数
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

}
