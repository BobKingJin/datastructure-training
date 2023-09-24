package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-01 21:28
 */
public class Number552 {

    int MOD = 1000000007;

    public int checkRecord1(int n) {
        return dfs1(0, n, 0, 0);
    }

    private int dfs1(int day, int n, int absent, int late) {

        if (day >= n)
            return 1;

        int ans = 0;
        // 1. Present随便放
        ans += dfs1(day + 1, n, absent, 0) % MOD;
        // 2. Absent最多只能放一个
        if (absent < 1)
            ans += dfs1(day + 1, n, 1, 0) % MOD;

        // 3. Late最多连续放 2 个
        if (late < 2)
            ans += dfs1(day + 1, n, absent, late + 1) % MOD;

        return ans;
    }

    public int checkRecord2(int n) {
        int[][][] memo = new int[n][2][3];
        return dfs2(0, n, 0, 0, memo);
    }

    private int dfs2(int day, int n, int absent, int late, int[][][] memo) {

        if (day >= n)
            return 1;

        if (memo[day][absent][late] != 0)
            return memo[day][absent][late];

        int ans = 0;
        // 1. Present随便放
        ans = dfs2(day + 1, n, absent, 0, memo) % MOD;
        // 2. Absent最多只能放一个
        if (absent < 1)
            ans = dfs2(day + 1, n, 1, 0, memo) % MOD;
        // 3. Late最多连续放 2 个
        if (late < 2)
            ans = dfs2(day + 1, n, absent, late + 1, memo) % MOD;

        // 记录每一个状态的计算结果
        memo[day][absent][late] = ans;

        return ans;
    }

    public int checkRecord3(int n) {
        // dp[i][j][k]表示第 i 天、在 A 为 j 次、连续的 L 为 k 次的方案数
        long[][][] dp = new long[n][2][3];
        // 初始值
        dp[0][0][0] = 1;
        dp[0][1][0] = 1;
        dp[0][0][1] = 1;

        for (int i = 1; i < n; i++) {
            // 本次填入 P，分成前一天累计了 0 个 A 和 1 个 A两种情况
            dp[i][0][0] += dp[i - 1][0][1] + dp[i - 1][0][2] % MOD;
            dp[i][1][0] += dp[i - 1][1][1] + dp[i - 1][1][2] % MOD;
            // 本次填入A，前一天没有累计A都能转移过来
            dp[i][1][0] += dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2] % MOD;
            // 本次填入 L，前一天最多只有一个连续的 L，分成四种情况
            dp[i][0][1] = dp[i - 1][0][0];
            dp[i][0][2] = dp[i - 1][0][1];
            dp[i][1][1] = dp[i - 1][1][0];
            dp[i][1][2] = dp[i - 1][1][1];
        }

        // 计算结果，即最后一天的所有状态相加
        long ans = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++)
                ans = (ans + dp[n - 1][i][j]) % MOD;
        }

        return (int) ans;
    }

}
