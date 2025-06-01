package com.bobking.leetcode.training;

/**
 * @Date: 2025/5/26 8:42
 * @Author: BobKing
 * @Description:
 */
public class Number2140 {

    // 参考: https://leetcode.cn/problems/solving-questions-with-brainpower/solutions/1213919/dao-xu-dp-by-endlesscheng-2qkc/?envType=daily-question&envId=2025-05-25
    public long mostPoints1(int[][] questions) {
        long[] memo = new long[questions.length];
        return dfs(0, questions, memo);
    }

    private long dfs(int i, int[][] questions, long[] memo) {
        if (i >= memo.length) {
            return 0;
        }
        // 之前计算过
        if (memo[i] > 0) {
            return memo[i];
        }
        long notChoose = dfs(i + 1, questions, memo);
        long choose = dfs(i + questions[i][1] + 1, questions, memo) + questions[i][0];
        return memo[i] = Math.max(notChoose, choose);
    }

    // 参考: https://leetcode.cn/problems/solving-questions-with-brainpower/solutions/1213919/dao-xu-dp-by-endlesscheng-2qkc/?envType=daily-question&envId=2025-05-25
    public long mostPoints2(int[][] questions) {
        int n = questions.length;
        long[] f = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int j = Math.min(i + questions[i][1] + 1, n);
            f[i] = Math.max(f[i + 1], f[j] + questions[i][0]);
        }
        return f[0];
    }

}
