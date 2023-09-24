package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-12 10:23
 */
public class Number375 {

    private int N = 210;
    private int[][] cache = new int[N][N];

    // 参考：https://leetcode.cn/problems/guess-number-higher-or-lower-ii/solution/gong-shui-san-xie-yi-ti-shuang-jie-ji-yi-92e5/
    public int getMoneyAmount1(int n) {
        return dfs(1, n);
    }

    // l 和 r 代表在范围 [l, r] 内进行猜数，返回值为在 [l, r] 内猜中数字至少需要多少钱
    // 可决策的部分为「选择猜哪个数 x」，而不可决策的是「选择某个数 x 之后（假设没有猜中），真实值会落在哪边」
    // 因此为求得「最坏情况下最好」的结果，所以应当取所有的 x 中的最小值
    private int dfs(int l, int r) {

        if (l >= r)
            return 0;

        if (cache[l][r] != 0)
            return cache[l][r];

        int ans = 0x3f3f3f3f;
        for (int x = l; x <= r; x++) {
            // 当选择的数位 x 时，至少需要 cur 才能猜中数字
            int cur = Math.max(dfs(l, x - 1), dfs(x + 1, r)) + x;
            // 在所有可以决策的数值之间取最优
            ans = Math.min(ans, cur);
        }

        cache[l][r] = ans;
        return ans;
    }

    // 参考：https://leetcode.cn/problems/guess-number-higher-or-lower-ii/solution/gong-shui-san-xie-yi-ti-shuang-jie-ji-yi-92e5/
    public int getMoneyAmount2(int n) {

        // 定义 f[l][r] 为考虑在 [l, r] 范围内进行猜数的最小成本
        int[][] f = new int[n + 10][n + 10];
        for (int len = 2; len <= n; len++) {
            // 注意 l 是从 1 开始的
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                f[l][r] = 0x3f3f3f3f;
                for (int x = l; x <= r; x++) {
                    int cur = Math.max(f[l][x - 1], f[x + 1][r]) + x;
                    f[l][r] = Math.min(f[l][r], cur);
                }
            }
        }

        return f[1][n];
    }

    private int[][] f = new int[N][N];

    private void init(){
        for (int len = 2; len <= N; len++) {
            for (int l = 1; l + len - 1 <= N; l++) {
                int r = l + len - 1;
                f[l][r] = 0x3f3f3f3f;
                for (int x = l; x <= r; x++) {
                    int cur = Math.max(f[l][x - 1], f[x + 1][r]) + x;
                    f[l][r] = Math.min(f[l][r], cur);
                }
            }
        }
    }

    // 参考：https://leetcode.cn/problems/guess-number-higher-or-lower-ii/solution/gong-shui-san-xie-yi-ti-shuang-jie-ji-yi-92e5/
    public int getMoneyAmount3(int n) {
        init();
        return f[1][n];
    }
}
