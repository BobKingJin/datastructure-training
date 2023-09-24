package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number1449 {

    // 参考：https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target/solution/gong-shui-san-xie-fen-liang-bu-kao-lu-we-uy4y/
    public String largestNumber(int[] cost, int target) {

        // 前 i 个物品选择价值为 j 的最多物品个数
        // 不选此物品  f[i][j] = f[i - 1][j]
        // 选择此物品  f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - u * k] + k)
        // 注意 f 数组的值表示的是物品个数，而不是最终结果
        int[][] f = new int[10][target + 1];
        for (int i = 0; i <= 9; i++)
            Arrays.fill(f[i], Integer.MIN_VALUE);

        for (int i = 0; i <= 9; i++)
            f[i][0] = 0;

        for (int i = 1; i <= 9; i++) {
            int u = cost[i - 1];
            for (int j = 0; j <= target; j++) {
                f[i][j] = f[i - 1][j];
                for (int k = 1; k * u <= j; k++)
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - u * k] + k);
            }
        }

        if (f[9][target] < 0)
            return "0";

        String res = "";
        for (int i = 9, j = target; i >= 1; i--) {
            int u = cost[i - 1];
            while (j >= u && f[i][j] == f[i][j - u] + 1) {
                res += String.valueOf(i);
                j -= u;
            }
        }

        return res;
    }
}
