package com.bobking.leetcode.training;

public class Number1895 {

    private int[][] rowSum;
    private int[][] colSum;

    // 参考：https://leetcode.cn/problems/largest-magic-square/solution/doocskai-yuan-she-qu-qian-zhui-he-bu-yao-yk38/
    public int largestMagicSquare(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        rowSum = new int[m + 1][n + 1];
        colSum = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                rowSum[i][j] = rowSum[i][j - 1] + grid[i - 1][j - 1];
                colSum[i][j] = colSum[i - 1][j] + grid[i - 1][j - 1];
            }
        }

        // 从大到小尝试
        for (int k = Math.min(m, n); k > 1; --k) {
            for (int i = 0; i + k - 1 < m; ++i) {
                for (int j = 0; j + k - 1 < n; ++j) {
                    int i2 = i + k - 1;
                    int j2 = j + k - 1;
                    if (check(grid, i, j, i2, j2))
                        return k;
                }
            }
        }

        return 1;
    }

    private boolean check(int[][] grid, int x1, int y1, int x2, int y2) {

        // 判断所有行的和是否相等
        int val = rowSum[x1 + 1][y2 + 1] - rowSum[x1 + 1][y1];

        for (int i = x1 + 1; i <= x2; ++i) {
            if (rowSum[i + 1][y2 + 1] - rowSum[i + 1][y1] != val)
                return false;
        }

        // 判断所有列的和是否相等
        for (int j = y1; j <= y2; ++j) {
            if (colSum[x2 + 1][j + 1] - colSum[x1][j + 1] != val)
                return false;
        }

        int s = 0;
        // 对角线
        for (int i = x1, j = y1; i <= x2; ++i, ++j)
            s += grid[i][j];

        if (s != val)
            return false;

        s = 0;
        // 对角线
        for (int i = x1, j = y2; i <= x2; ++i, --j)
            s += grid[i][j];

        if (s != val)
            return false;

        return true;
    }
}
