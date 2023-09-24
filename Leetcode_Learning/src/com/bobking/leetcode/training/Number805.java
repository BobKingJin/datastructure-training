package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-11-15 14:47
 */
public class Number805 {

    // 参考：https://leetcode.cn/problems/split-array-with-same-average/solution/gong-shui-san-xie-by-ac_oier-flsd/
    public boolean splitArraySameAverage(int[] nums) {

        int n = nums.length;
        int m = n / 2;
        int sum = 0;

        for (int x : nums)
            sum += x;

        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();

        for (int s = 0; s < (1 << m); s++) {
            int tot = 0;
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if (((s >> i) & 1) == 1) {
                    tot += nums[i];
                    cnt++;
                }
            }
            Set<Integer> set = map.getOrDefault(tot, new HashSet<Integer>());
            set.add(cnt);
            map.put(tot, set);
        }

        for (int s = 0; s < (1 << (n - m)); s++) {

            int tot = 0;
            int cnt = 0;

            for (int i = 0; i < (n - m); i++) {
                if (((s >> i) & 1) == 1) {
                    tot += nums[i + m];
                    cnt++;
                }
            }

            for (int k = Math.max(1, cnt); k < n; k++) {
                if (k * sum % n != 0)
                    continue;
                int t = k * sum / n;
                if (!map.containsKey(t - tot))
                    continue;
                if (!map.get(t - tot).contains(k - cnt))
                    continue;
                return true;
            }
        }
        return false;
    }
}
