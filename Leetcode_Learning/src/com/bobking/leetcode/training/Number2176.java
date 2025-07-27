package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2025/7/27 9:47
 * @Author: BobKing
 * @Description:
 */
public class Number2176 {

    private static final int MX = 101;
    private static final List<Integer>[] divisors = new ArrayList[MX];

    private void init() {
        Arrays.setAll(divisors, i -> new ArrayList<>());
        // 预处理每个数的因子
        for (int i = 1; i < MX; i++) {
            for (int j = i; j < MX; j += i) {
                divisors[j].add(i);
            }
        }
    }

    // 参考: https://leetcode.cn/problems/count-equal-and-divisible-pairs-in-an-array/solutions/1277713/mo-ni-by-endlesscheng-wegn/?envType=daily-question&envId=2025-07-27
    public int countPairs(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        init();
        // 枚举 j, 计算左边有多少个符合要求的 i
        for (int j = 0; j < nums.length; j++) {
            int x = nums[j];
            if (j > 0 && x == nums[0]) {
                // 单独统计 i = 0 的情况
                ans++;
            }
            // i 必须是 k2 的倍数
            int k2 = k / gcd(k, j);
            // 用位运算把二元组 (x, k2) 合并成一个整数
            ans += cnt.getOrDefault(k2 << 10 | x, 0);
            // j 是 d 的倍数
            for (int d : divisors[j]) {
                // cnt[d << 10 | x]++
                cnt.merge(d << 10 | x, 1, Integer::sum);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = b % a;
            b = a;
            a = tmp;
        }
        return b;
    }


}
