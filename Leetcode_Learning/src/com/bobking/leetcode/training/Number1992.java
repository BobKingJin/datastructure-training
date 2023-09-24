package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-03-26 14:29
 */
public class Number1992 {

    public int[][] findFarmland(int[][] land) {

        List<int[]> res = new ArrayList<int[]>();
        int m = land.length;
        int n = land[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (land[i][j] == 0)
                    continue;

                int row = i;
                int col = j;

                // 注意题目提到：农场土地之间以矩形的 农场组 的形式存在
                // 即农场组一定是合法的，只需要检查矩形的最外层即可，不会出现不合法
                // 即不会出现如下情况：1   1   1
                //                 1   1   0
                while (row + 1 < m && land[row + 1][j] == 1)
                    row++;
                while (col + 1 < n && land[i][col + 1] == 1)
                    col++;

                res.add(new int[]{i, j, row, col});

                // 探索到的矩形农场组中所有块置为0，避免后续的遍历
                for (int x = i; x <= row; x++) {
                    for (int y = j; y <= col; y++)
                        land[x][y] = 0;
                }
            }
        }

        return res.toArray(new int[0][0]);
    }
}
