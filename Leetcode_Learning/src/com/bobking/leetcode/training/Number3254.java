package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/2/9 11:33
 * @Author: BobKing
 * @Description:
 */
public class Number3254 {

    // 参考: https://leetcode.cn/problems/find-the-power-of-k-size-subarrays-ii/solutions/2884189/on-jian-ji-xie-fa-pythonjavacgo-by-endle-gtek/
    public int[] resultsArray(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Arrays.fill(ans, -1);
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt = (i == 0 || nums[i] == nums[i - 1] + 1) ? cnt + 1 : 1;
            if (cnt >= k) {
                ans[i - k + 1] = nums[i];
            }
        }
        return ans;
    }

}
