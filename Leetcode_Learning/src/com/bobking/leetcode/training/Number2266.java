package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-13 11:23
 */
public class Number2266 {

    final int MOD = (int) (1e9 + 7);

    // 参考：https://leetcode.cn/problems/count-number-of-texts/solution/by-endlesscheng-gj8f/
    public int countTexts(String pressedKeys) {

        long ans = 1;
        int n = pressedKeys.length();

        for (int i = 0; i < n; ) {

            char ch = pressedKeys.charAt(i);

            if (ch == '7' || ch == '9') {
                // dp4
                int cnt = 1;
                while (++i < n && pressedKeys.charAt(i) == ch)
                    cnt++;
                ans = ans * count4(cnt) % MOD;
            } else {
                // dp3
                int cnt = 1;
                while (++i < n && pressedKeys.charAt(i) == ch)
                    cnt++;
                ans = ans * count3(cnt) % MOD;
            }
        }
        return (int) ans;
    }

    private int count3(int cnt) {

        long[] dp = new long[cnt + 1];

        if (cnt == 1) {
            return 1;
        } else if (cnt == 2) {
            return 2;
        } else if (cnt == 3) {
            return 4;
        }
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= cnt; i++)
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;

        return (int) dp[cnt];
    }


    private int count4(int cnt) {

        long[] dp = new long[cnt + 1];

        if (cnt == 1) {
            return 1;
        } else if (cnt == 2) {
            return 2;
        } else if (cnt == 3) {
            return 4;
        } else if (cnt == 4) {
            return 8;
        }
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        dp[4] = 8;

        for (int i = 5; i <= cnt; i++)
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4]) % MOD;

        return (int) dp[cnt];
    }
}
