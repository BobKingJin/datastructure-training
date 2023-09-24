package com.bobking.leetcode.training;

public class Number879 {

    int mod = (int) 1e9 + 7;

    // 参考：http://leetcode-cn.com/problems/profitable-schemes/solution/gong-shui-san-xie-te-shu-duo-wei-fei-yon-7su9/
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {

        if (n < 0 || minProfit < 0 || group == null || group.length == 0 || profit == null || profit.length == 0)
            return 0;

        int m = group.length;
        // f[i][j][k] 为考虑前 i 件工作，使用人数不超过 j，所得利润至少为 k 的方案数
        long[][][] f = new long[m + 1][n + 1][minProfit + 1];

        // 当不存在任何工作时，所得利用利润必然为 0（满足至少为 0），同时对人数限制没有要求
        for (int i = 0; i <= n; i++)
            f[0][i][0] = 1;

        for (int i = 1; i <= m; i++) {
            int a = group[i - 1];
            int b = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= a) {
                        int u = Math.max(k - b, 0);
                        f[i][j][k] += f[i - 1][j - a][u];
                        if (f[i][j][k] >= mod)
                            f[i][j][k] -= mod;
                    }
                }
            }
        }

        return (int) f[m][n][minProfit];
    }
}
