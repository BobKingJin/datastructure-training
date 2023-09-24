package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-20 18:14
 */
public class Number650 {

    int INF = 0x3f3f3f3f;

    // 参考：https://leetcode.cn/problems/2-keys-keyboard/solution/gong-shui-san-xie-yi-ti-san-jie-dong-tai-f035/
    public int minSteps(int n) {

        // 定义 f[i][j] 为经过最后一次操作后，当前记事本上有 i 个字符，粘贴板上有 j 个字符的最小操作次数
        // 由于粘贴板的字符必然是经过 Copy All 操作而来，因此对于一个合法的 f[i][j] 而言，必然有 j <= i
        // 不失一般性地考虑 f[i][j] 该如何转移：
        // 最后一次操作是 Paste 操作：此时粘贴板的字符数量不会发生变化，即有 f[i][j] = f[i - j][j] + 1
        // 最后一次操作是 Copy All 操作：那么此时的粘贴板的字符数与记事本上的字符数相等（满足 i = j），此时的 f[i][j] = min(f[i][x] + 1) 0 ≤ x < i
        // 发现最后一个合法的 f[i][j]（满足 i = j）依赖与前面 f[i][j]（满足 j < i）
        // 因此实现上，可以使用一个变量 min 保存前面转移的最小值，用来更新最后的 f[i][j]
        // 再进一步，发现如果 f[i][j] 的最后一次操作是由 Paste 而来，原来粘贴板的字符数不会超过 i / 2
        // 因此在转移 f[i][j]（满足 j < i）时，其实只需要枚举 [0, i/2] 即可

        int[][] f = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++)
                f[i][j] = INF;
        }

        f[1][0] = 0;
        f[1][1] = 1;

        for (int i = 2; i <= n; i++) {
            int min = INF;
            for (int j = 0; j <= i / 2; j++) {
                f[i][j] = f[i - j][j] + 1;
                min = Math.min(min, f[i][j]);
            }
            f[i][i] = min + 1;
        }

        int ans = INF;

        for (int i = 0; i <= n; i++)
            ans = Math.min(ans, f[n][i]);

        return ans;
    }
}
