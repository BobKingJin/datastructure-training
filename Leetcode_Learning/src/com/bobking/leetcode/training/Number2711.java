package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2025/3/30 10:45
 * @Author: BobKing
 * @Description:
 */
public class Number2711 {

    public int[][] differenceOfDistinctValues1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];
        Set<Integer> st = new HashSet<Integer>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 计算 topLeft[i][j]
                st.clear();
                for (int x = i - 1, y = j - 1; x >= 0 && y >= 0; x--, y--) {
                    st.add(grid[x][y]);
                }
                int topLeft = st.size();
                // 计算 bottomRight[i][j]
                st.clear();
                for (int x = i + 1, y = j + 1; x < m && y < n; x++, y++) {
                    st.add(grid[x][y]);
                }
                int bottomRight = st.size();
                ans[i][j] = Math.abs(topLeft - bottomRight);
            }
        }
        return ans;
    }

    public int[][] differenceOfDistinctValues2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];
        Set<Integer> set = new HashSet<Integer>();

        // 第一排在右上, 最后一排在左下
        // 每排从左上到右下
        // 令 k = i - j + n, 那么右上角 k = 1, 左下角 k = m + n - 1
        for (int k = 1; k < m + n; k++) {
            // 核心: 计算 j 的最小值和最大值
            // i = 0 的时候, j = n - k, 但不能是负数
            int minJ = Math.max(n - k, 0);
            // i = m - 1 的时候, j = m + n - 1 - k, 但不能超过 n - 1
            int maxJ = Math.min(m + n - 1 - k, n - 1);
            set.clear();
            for (int j = minJ; j <= maxJ; j++) {
                int i = k + j - n;
                // topLeft[i][j] == set.size()
                ans[i][j] = set.size();
                set.add(grid[i][j]);
            }
            set.clear();
            for (int j = maxJ; j >= minJ; j--) {
                int i = k + j - n;
                // bottomRight[i][j] == set.size()
                ans[i][j] = Math.abs(ans[i][j] - set.size());
                set.add(grid[i][j]);
            }
        }
        return ans;
    }


}
