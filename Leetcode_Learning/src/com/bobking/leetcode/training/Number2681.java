package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2023/12/26 8:03
 * @Author: BobKing
 * @Description:
 */
public class Number2681 {

    private final long MOD = (long) 1e9 + 7;

    // 参考: https://leetcode.cn/problems/power-of-heroes/solutions/2268792/gong-xian-fa-pythonjavacgo-by-endlessche-d4jx/?envType=daily-question&envId=2023-12-26
    public int sumOfPower(int[] nums) {

        Arrays.sort(nums);

        long ans = 0;
        long s = 0;

        // x 作为最大值
        for (long x : nums) {
            // 中间模一次防止溢出
            ans = (ans + x * x % MOD * (x + s)) % MOD;
            // 递推计算下一个 s
            s = (s * 2 + x) % MOD;
        }

        return (int) ans;
    }
}
