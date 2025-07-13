package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Date: 2025/7/13 10:41
 * @Author: BobKing
 * @Description:
 */
public class Number2799 {

    // 参考: https://leetcode.cn/problems/count-complete-subarrays-in-an-array/solutions/2364671/on-hua-dong-chuang-kou-by-endlesscheng-9ztb/?envType=daily-question&envId=2025-07-13
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            set.add(x);
        }
        int k = set.size();
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>(k);
        int ans = 0;
        int left = 0;
        for (int x : nums) {
            // cnt[x]++
            cnt.merge(x, 1, Integer::sum);
            while (cnt.size() == k) {
                int out = nums[left];
                // --cnt[out] == 0
                if (cnt.merge(out, -1, Integer::sum) == 0) {
                    cnt.remove(out);
                }
                left++;
            }
            ans += left;
        }
        return ans;
    }

}
