package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Date: 2025/3/30 11:01
 * @Author: BobKing
 * @Description:
 */
public class Number3446 {

    // 参考: https://leetcode.cn/problems/sort-matrix-by-diagonals/solutions/3068709/mo-ban-mei-ju-dui-jiao-xian-pythonjavacg-pjxp/
    public int[][] sortMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 第一排在右上, 最后一排在左下
        // 每排从左上到右下
        // 令 k = i - j + n, 那么右上角 k = 1, 左下角 k = m + n - 1
        for (int k = 1; k < m + n; k++) {
            // 核心: 计算 j 的最小值和最大值
            // i = 0 的时候, j = n - k, 但不能是负数
            int minJ = Math.max(n - k, 0);
            // i = m - 1 的时候, j = m + n - 1 - k, 但不能超过 n - 1
            int maxJ = Math.min(m + n - 1 - k, n - 1);
            // 预分配空间
            List<Integer> a = new ArrayList<Integer>(maxJ - minJ + 1);
            for (int j = minJ; j <= maxJ; j++) {
                // 根据 k 的定义得 i = k + j  - n
                a.add(grid[k + j - n][j]);
            }
            a.sort(minJ > 0 ? null : Comparator.reverseOrder());
            for (int j = minJ; j <= maxJ; j++) {
                grid[k + j - n][j] = a.get(j - minJ);
            }
        }
        return grid;
    }

}
