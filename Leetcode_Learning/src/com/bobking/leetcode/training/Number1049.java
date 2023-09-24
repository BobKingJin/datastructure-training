package com.bobking.leetcode.training;

public class Number1049 {

    // 参考：https://leetcode-cn.com/problems/last-stone-weight-ii/solution/gong-shui-san-xie-xiang-jie-wei-he-neng-jgxik/
    public int lastStoneWeightII(int[] stones) {

        // 要使最后'所剩的石头'重量最小，即需要将这一堆石头划分为两堆并使这两堆的重量之差的绝对值最小
        // 可以设其中一堆的权重为 +1，另一堆的权重为 -1，这样两堆的重量变量分别为 pos_sum 与 neg_sum ，好辨认一些
        // 限制条件: 1. neg_sum +  pos_sum = sum
        //          2. neg_sum <= pos_sum
        // 最好情况为 neg_sum == pos_sum，这时候最终剩下的石头重量就为 0
        // 联立 1, 2两式:  neg_sum <= sum - neg_sum
        //               neg_sum <= sum / 2
        // 所以题目变成在石头中去一堆，让它在重量不超过 sum / 2 的情况下尽可能大
        // 因为石头的重量是离散的，所以可能无法取到 sum / 2
        int n = stones.length;
        int sum = 0;

        for (int i : stones)
            sum += i;

        int t = sum / 2;
        // f[i][j] 代表考虑前 i 个石头（数值），凑成总和不超过 j 的最大价值
        int[][] f = new int[n + 1][t + 1];
        for (int i = 1; i <= n; i++) {
            int x = stones[i - 1];
            for (int j = 0; j <= t; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= x)
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - x] + x);
            }
        }

        return Math.abs(sum - f[n][t] - f[n][t]);
    }
}
