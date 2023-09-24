package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-05-07 8:37
 */
public class Number1799 {

    // 参考：https://leetcode.cn/problems/maximize-score-after-n-operations/solution/by-lcbin-8uxm/
    public int maxScore1(int[] nums) {

        int m = nums.length;
        // g[i][j] 表示 nums[i] 和 nums[j] 的最大公约数
        int[][] g = new int[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j)
                g[i][j] = gcd(nums[i], nums[j]);
        }
        // f[k] 表示当前操作后的状态为 k 时，可以获得的最大分数和
        int[] f = new int[1 << m];
        // 对于每个状态 k，先判断此状态的二进制位中 1 的个数
        // cnt 是否为偶数，是则进行如下操作：
        // 枚举 k 中二进制位为 1 的位置，假设为 i 和 j
        // 则 i 和 j 两个位置的元素可以进行一次操作
        for (int k = 0; k < 1 << m; ++k) {
            int cnt = Integer.bitCount(k);
            if (cnt % 2 == 0) {
                for (int i = 0; i < m; ++i) {
                    if (((k >> i) & 1) == 1) {
                        for (int j = i + 1; j < m; ++j) {
                            if (((k >> j) & 1) == 1)
                                f[k] = Math.max(f[k], f[k ^ (1 << i) ^ (1 << j)] + cnt / 2 * g[i][j]);
                        }
                    }
                }
            }
        }
        return f[(1 << m) - 1];
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int max = 0;
    private int[] n;
    private boolean[] ok;

    private int gcb(int a, int b) {
        if (b == 0)
            return a;
        return gcb(b, a % b);
    }

    public int maxScore2(int[] nums) {
        int n = nums.length;
        ok = new boolean[n];
        this.n = new int[n / 2];
        dfs(nums, 0);
        return max;
    }

    private void dfs(int[] nums, int len) {
        if (len == nums.length / 2) {
            int[] ans = new int[n.length];
            for (int i = 0; i < n.length; i++) ans[i] = n[i];//防止排序修改原数组
            Arrays.sort(ans);
            int sum = 0;
            for (int i = 1; i <= ans.length; i++) sum += i * ans[i - 1];
            max = Math.max(sum, max);
            return;
        }
        for (int i = len; i < nums.length - 1; i++) {
            if (ok[i]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (ok[j]) continue;
                ok[i] = ok[j] = true;
                n[len] = gcb(nums[i], nums[j]);
                dfs(nums, len + 1);
                ok[j] = ok[i] = false;
            }
            return;
        }
    }
}
