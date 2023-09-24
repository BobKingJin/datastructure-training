package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-05-10 6:43
 */
public class Number1220 {

    private final int MOD = (int)1e9+7;

    // 参考：https://leetcode.cn/problems/count-vowels-permutation/solution/gong-shui-san-xie-yi-ti-shuang-jie-xian-n8f4o/
    public int countVowelPermutation(int n) {

        // 定义 f[i][j] 为考虑长度为 i + 1 的字符串，且结尾元素为 j 的方案数
        // （其中 j 代表数组 ['a', 'e', 'i', 'o', 'u'] 下标）
        long[][] f = new long[n][5];
        Arrays.fill(f[0], 1);

        for (int i = 0; i < n - 1; i++) {
            // 每个元音 'a' 后面都只能跟着 'e'
            f[i + 1][1] += f[i][0];
            // 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
            f[i + 1][0] += f[i][1];
            f[i + 1][2] += f[i][1];
            // 每个元音 'i' 后面 不能 再跟着另一个 'i'
            f[i + 1][0] += f[i][2];
            f[i + 1][1] += f[i][2];
            f[i + 1][3] += f[i][2];
            f[i + 1][4] += f[i][2];
            // 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
            f[i + 1][2] += f[i][3];
            f[i + 1][4] += f[i][3];
            // 每个元音 'u' 后面只能跟着 'a'
            f[i + 1][0] += f[i][4];
            for (int j = 0; j < 5; j++)
                f[i + 1][j] %= MOD;
        }

        long ans = 0;

        for (int i = 0; i < 5; i++)
            ans += f[n - 1][i];

        return (int)(ans % MOD);
    }
}
