package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2024/1/17 8:10
 * @Author: BobKing
 * @Description:
 */
public class Number2719 {

    private static final int MOD = 1000000007;

    // 参考: https://leetcode.cn/problems/count-of-integers/solutions/2296043/shu-wei-dp-tong-yong-mo-ban-pythonjavacg-9tuc/?envType=daily-question&envId=2024-01-16
    public int count(String num1, String num2, int min_sum, int max_sum) {

        // 避免负数
        int ans = calc(num2, min_sum, max_sum) - calc(num1, min_sum, max_sum) + MOD;

        int sum = 0;

        for (char c : num1.toCharArray())
            sum += c - '0';

        if (min_sum <= sum && sum <= max_sum)
            // num1 是合法的, 补回来
            ans++;

        return ans % MOD;
    }

    private int calc(String s, int minSum, int maxSum) {

        int n = s.length();

        int[][] memo = new int[n][Math.min(9 * n, maxSum) + 1];

        for (int[] row : memo)
            Arrays.fill(row, -1);

        return dfs(0, 0, true, s.toCharArray(), minSum, maxSum, memo);
    }

    private int dfs(int i, int sum, boolean isLimit, char[] s, int minSum, int maxSum, int[][] memo) {

        // 非法
        if (sum > maxSum)
            return 0;

        if (i == s.length)
            return sum >= minSum ? 1 : 0;

        if (!isLimit && memo[i][sum] != -1)
            return memo[i][sum];

        int up = isLimit ? s[i] - '0' : 9;

        int res = 0;

        // 枚举当前数位填 d
        for (int d = 0; d <= up; d++)
            res = (res + dfs(i + 1, sum + d, isLimit && (d == up), s, minSum, maxSum, memo)) % MOD;

        if (!isLimit)
            memo[i][sum] = res;

        return res;
    }

}
