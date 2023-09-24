package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

public class Number930 {

    // 参考：https://leetcode.cn/problems/binary-subarrays-with-sum/solution/gong-shui-san-xie-yi-ti-shuang-jie-qian-hfoc0/
    public int numSubarraysWithSum1(int[] nums, int goal) {

        // 前缀和
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + nums[i - 1];

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int r = sum[i + 1];
            int l = r - goal;
            ans += map.getOrDefault(l, 0);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/binary-subarrays-with-sum/solution/gong-shui-san-xie-yi-ti-shuang-jie-qian-hfoc0/
    public int numSubarraysWithSum2(int[] nums, int t) {

        // nums[i] 没有负权值意味着前缀和数组必然具有（非严格）单调递增特性
        // 在给定 t 的情况下，当右端点 r 往右移动时，满足条件的左端点 l 必然往右移动
        // 实现上，可以使用两个左端点 l1 和 l2，代表在给定右端点 r 的前提下满足要求的左端点集合
        // 同时使用 s1 和 s2 分别代表两个端点到 r 这一段的和
        int n = nums.length;
        int ans = 0;
        for (int r = 0, l1 = 0, l2 = 0, s1 = 0, s2 = 0; r < n; r++) {

            s1 += nums[r];
            s2 += nums[r];

            while (l1 <= r && s1 > t)
                s1 -= nums[l1++];

            while (l2 <= r && s2 >= t)
                s2 -= nums[l2++];

            // 当同步进行时，l2 - l1 = 0
            ans += l2 - l1;
        }

        return ans;
    }


}
