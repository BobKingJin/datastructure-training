package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2025/5/25 13:33
 * @Author: BobKing
 * @Description:
 */
public class Number2537 {

    // 参考: https://leetcode.cn/problems/count-the-number-of-good-subarrays/solutions/2062761/shuang-zhi-zhen-by-endlesscheng-lkd9/?envType=daily-question&envId=2025-05-25
    public long countGood(int[] nums, int k) {
        long ans = 0;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int pairs = 0;
        int left = 0;
        for (int x : nums) {
            int c = cnt.getOrDefault(x, 0);
            pairs += c;
            cnt.put(x, c + 1);
            while (pairs >= k) {
                x = nums[left];
                c = cnt.get(x);
                pairs -= c - 1;
                cnt.put(x, c - 1);
                left++;
            }
            ans += left;
        }
        return ans;
    }

}
