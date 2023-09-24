package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-29 22:45
 */
public class Number1641 {

    private Integer[][] f;
    private int n;

    // 参考：https://leetcode.cn/problems/count-sorted-vowel-strings/solution/python3javacgo-yi-ti-shuang-jie-ji-yi-hu-vh2j/
    public int countVowelStrings1(int n) {
        this.n = n;
        f = new Integer[n][5];
        return dfs(0, 0);
    }

    // dfs(i,j)，表示当前已经选了 i 个元音字母，且最后一个元音字母是 j 的方案数。那么答案就是 dfs(0, 0)
    private int dfs(int i, int j) {

        if (i >= n)
            return 1;

        if (f[i][j] != null)
            return f[i][j];

        int ans = 0;
        // 注意：这个位置是从 j 开始，而不是从 0 开始
        for (int k = j; k < 5; ++k)
            ans += dfs(i + 1, k);
        return f[i][j] = ans;
    }
}
