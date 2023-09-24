package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-10 22:58
 */
public class Number892 {

    // 参考：https://leetcode.cn/problems/surface-area-of-3d-shapes/solution/shi-li-you-tu-you-zhen-xiang-jiang-jie-yi-kan-jiu-/
    public int surfaceArea(int[][] grid) {

        int n = grid.length;
        int area = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 先把 grid[i][j] 赋值给 level，省掉了 bound check
                int level = grid[i][j];
                if (level > 0) {
                    // 一个柱体中：2个底面 + 所有的正方体都贡献了4个侧表面积
                    area += (level << 2) + 2;
                    // 减掉 i 与 i - 1 相贴的两份表面积
                    area -= i > 0 ? Math.min(level, grid[i - 1][j]) << 1 : 0;
                    // 减掉 j 与 j - 1 相贴的两份表面积
                    area -= j > 0 ? Math.min(level, grid[i][j - 1]) << 1 : 0;
                }
            }
        }
        return area;
    }
}
