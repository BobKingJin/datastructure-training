package com.bobking.leetcode.training;

public class Interview08_01 {

    // 对比：Interview08_06
    // 超时
    public int waysToStep1(int n) {

        if (n <= 0)
            return 0;

        if (n == 1 || n == 2)
            return n;

        if (n == 3)
            return 4;

        return waysToStep1(n - 1) + waysToStep1(n - 2) + waysToStep1(n - 3);
    }

    // 参考：https://leetcode-cn.com/problems/three-steps-problem-lcci/solution/mei-ri-suan-fa-day-80-suo-you-ren-du-hui-zuo-de-ru/
    public int waysToStep2(int n) {

        if (n <= 0)
            return 0;

        if (n == 1 || n == 2)
            return n;

        if (n == 3)
            return 4;

        // 用 long 型
        // dp[i]：从 1 级台阶跳到 i 级台阶有多少种方式
        long dp[] = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++)
            dp[i] = (dp[i - 1] % 1000000007 + dp[i - 2] % 1000000007 + dp[i - 3] % 1000000007) % 1000000007;

        return (int) dp[n];
    }

    // 参考：https://leetcode-cn.com/problems/three-steps-problem-lcci/solution/mei-ri-suan-fa-day-80-suo-you-ren-du-hui-zuo-de-ru/
    public int waysToStep3(int n) {

        if (n <= 0)
            return 0;

        if (n == 1 || n == 2)
            return n;

        if (n == 3)
            return 4;

        long dp0 = 1;
        long dp1 = 1;
        long dp2 = 2;
        for (int i = 3; i <= n; i++) {
            long temp = (dp0 + dp1 + dp2) % 1000000007;
            dp0 = dp1 % 1000000007;
            dp1 = dp2 % 1000000007;
            dp2 = temp;
        }

        return (int) dp2;
    }
}
