package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-30 10:57
 */
public class Number1025 {

    // 参考：https://leetcode.cn/problems/divisor-game/solution/ji-yi-hua-di-gui-dong-tai-gui-hua-shu-xue-fang-fa-/
    public boolean divisorGame1(int n) {

        if (n == 1)
            return false;

        Boolean[] memo = new Boolean[n + 1];
        return dfs(n, memo);
    }

    private boolean dfs(int n, Boolean[] memo) {

        if (n == 1)
            return false;

        if (n == 2)
            return true;

        if (memo[n] != null)
            return memo[n];

        boolean canWin = false;
        // n / 2 是下取整，所以可以取等号
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0 && !dfs(n - i, memo)) {
                // 当前先手，如果对手的下一个状态值有 false，就说明可以赢
                canWin = true;
                // 由于假设两边都足够聪明，因此赢了以后，就可以退出循环了
                break;
            }
        }
        memo[n] = canWin;
        return canWin;
    }

    // 参考：https://leetcode.cn/problems/divisor-game/solution/ji-yi-hua-di-gui-dong-tai-gui-hua-shu-xue-fang-fa-/
    public boolean divisorGame2(int n) {

        if (n == 1)
            return false;

        // 为了不处理下标偏移，多设置 1 格
        // dp[i]：黑板上的数字为 i 时，当前做出选择的人是否会赢
        boolean[] dp = new boolean[n + 1];
        dp[1] = false;
        dp[2] = true;

        // 以线性的方式逐步递推得到结果
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // 只要做出的选择的其中之一，能让对方输，在当前这一步就可以赢
                if ((i % j == 0) && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
