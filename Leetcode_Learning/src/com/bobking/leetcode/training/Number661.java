package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-20 10:53
 */
public class Number661 {

    // 参考：https://leetcode.cn/problems/image-smoother/solution/by-ac_oier-nn3v/
    public int[][] imageSmoother1(int[][] img) {

        int m = img.length;
        int n = img[0].length;
        int[][] ans = new int[m][n];
        int[][] dirs = new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tot = 0;
                int cnt = 0;
                for (int[] di : dirs) {
                    int nx = i + di[0];
                    int ny = j + di[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                        continue;
                    tot += img[nx][ny];
                    cnt++;
                }
                ans[i][j] = tot / cnt;
            }
        }
        return ans;
    }

    // 参考：https://leetcode.cn/problems/image-smoother/solution/by-ac_oier-nn3v/
    public int[][] imageSmoother2(int[][] img) {

        int m = img.length;
        int n = img[0].length;
        int[][] sum = new int[m + 10][n + 10];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++)
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + img[i - 1][j - 1];
        }

        int[][] ans = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 左上角
                int a = Math.max(0, i - 1);
                int b = Math.max(0, j - 1);
                // 右下角
                int c = Math.min(m - 1, i + 1);
                int d = Math.min(n - 1, j + 1);

                int cnt = (c - a + 1) * (d - b + 1);
                int tot = sum[c + 1][d + 1] - sum[a][d + 1] - sum[c + 1][b] + sum[a][b];
                ans[i][j] = tot / cnt;
            }
        }
        return ans;
    }
}
