package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/8 22:37
 * @Author: BobKing
 * @Description:
 */
public class Number3240 {

    // 参考: https://leetcode.cn/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-ii/solutions/2868238/fen-lei-tao-lun-pythonjavacgo-by-endless-jl6a/?envType=daily-question&envId=2025-03-08
    public int minFlips(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int cnt1 =
                    grid[i][j] + grid[i][n - 1 - j] + grid[m - 1 - i][j] + grid[m - 1 - i][n - 1
                        - j];
                // 全为 1 或全为 0
                ans += Math.min(cnt1, 4 - cnt1);
            }
        }

        if (m % 2 > 0 && n % 2 > 0) {
            // 正中间的数必须是 0
            ans += grid[m / 2][n / 2];
        }

        int diff = 0;
        int cnt1 = 0;
        if (m % 2 > 0) {
            // 统计正中间这一排
            for (int j = 0; j < n / 2; j++) {
                if (grid[m / 2][j] != grid[m / 2][n - 1 - j]) {
                    diff++;
                } else {
                    cnt1 += grid[m / 2][j] * 2;
                }
            }
        }
        if (n % 2 > 0) {
            // 统计正中间这一列
            for (int i = 0; i < m / 2; i++) {
                if (grid[i][n / 2] != grid[m - 1 - i][n / 2]) {
                    diff++;
                } else {
                    cnt1 += grid[i][n / 2] * 2;
                }
            }
        }
        return ans + (diff > 0 ? diff : cnt1 % 4);
    }

}
