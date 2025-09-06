package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/9/6 13:46
 * @Author: BobKing
 * @Description:
 */
public class Number2616 {

    // 参考: https://leetcode.cn/problems/minimize-the-maximum-difference-of-pairs/solutions/2216315/er-fen-da-an-tan-xin-by-endlesscheng-dlxv/?envType=daily-question&envId=2025-09-06
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int left = -1;
        int right = nums[nums.length - 1] - nums[0];
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(mid, nums, p)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int mx, int[] nums, int p) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 选 nums[i] 和 nums[i + 1]
            if (nums[i + 1] - nums[i] <= mx) {
                cnt++;
                i++;
            }
        }
        return cnt >= p;
    }

}
