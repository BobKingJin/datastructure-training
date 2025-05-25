package com.bobking.leetcode.training;

import java.util.List;

/**
 * @Date: 2025/5/25 10:18
 * @Author: BobKing
 * @Description:
 */
public class Number2845 {

    // 参考: https://leetcode.cn/problems/count-of-interesting-subarrays/solutions/2424063/qian-zhui-he-ha-xi-biao-fu-ti-dan-by-end-74bb/?envType=daily-question&envId=2025-05-25
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + (nums.get(i) % modulo == k ? 1 : 0);
        }

        int[] cnt = new int[Math.min(n + 1, modulo)];
        long ans = 0;
        for (int s : sum) {
            if (s >= k) {
                ans += cnt[(s - k) % modulo];
            }
            cnt[s % modulo]++;
        }
        return ans;
    }

}
