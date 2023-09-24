package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-11-18 23:56
 */
public class Number891 {

    int N = 100010;
    int MOD = (int)1e9+7;
    long[] p = new long[N];

    private void init(){
        p[0] = 1;
        for (int i = 1; i < N; i++)
            p[i] = p[i - 1] * 2 % MOD;
    }

    // 参考：https://leetcode.cn/problems/sum-of-subsequence-widths/solution/by-endlesscheng-upd1/
    // 参考：https://leetcode.cn/problems/sum-of-subsequence-widths/solution/by-ac_oier-6tyk/
    public int sumSubseqWidths(int[] nums) {

        init();
        int n = nums.length;
        long ans = 0;
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            ans += (p[i] * nums[i]) % MOD;
            ans %= MOD;
            ans -= (p[n - i - 1] * nums[i]) % MOD;
            ans %= MOD;
        }

        return (int) ans;
    }

}
