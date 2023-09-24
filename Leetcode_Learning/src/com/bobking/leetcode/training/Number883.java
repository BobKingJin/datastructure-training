package com.bobking.leetcode.training;

public class Number883 {

    // 参考：https://leetcode-cn.com/problems/projection-area-of-3d-shapes/solution/by-ac_oier-r6hj/
    public int projectionArea(int[][] grid) {

        // ans1：统计俯视图的面积，共有 n * n 个位置需要被统计，当任意格子 g[i][j] > 0，阴影面积加一
        // ans2：统计左视图的面积，共有 n 行需要被统计，每一行对 ans2 的贡献为该行的最大高度
        // ans3：统计主视图的面积，共有 n 列需要被统计，每一列对 ans3 的贡献为该列的最大高度
        int ans1 = 0;
        int ans2 = 0;
        int ans3 = 0;
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            int a = 0;
            int b = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0)
                    ans1++;
                a = Math.max(a, grid[i][j]);
                b = Math.max(b, grid[j][i]);
            }
            ans2 += a;
            ans3 += b;
        }

        return ans1 + ans2 + ans3;
    }

}
