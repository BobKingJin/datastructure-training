package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-12 11:34
 */
public class Number840 {

    // 参考：https://leetcode.cn/problems/magic-squares-in-grid/solution/fei-bao-li-nu-li-xie-chu-you-ya-de-dai-ma-shuang-b/
    public int numMagicSquaresInside(int[][] grid) {

        int rLen = grid.length;
        int cLen = grid[0].length;
        //                                    0  1  2  3  4  5  6  7
        //            0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21
        int[] nums = {1, 6, 7, 2, 9, 4, 3, 8, 1, 6, 7, 2, 9, 4, 3, 8, 1, 6, 7, 2, 9, 4};
        int[] indexes = {-1, 8, 11, 14, 13, -1, 9, 10, 7, 12};

        int[] rows = {0, 1, 2, 2, 2, 1, 0, 0};
        int[] cols = {0, 0, 0, 1, 2, 2, 2, 1};

        int count = 0;
        for (int i = 0; i <= rLen - 3; i++) {
            label:
            for (int j = 0; j <= cLen - 3; j++) {

                if (grid[i + 1][j + 1] != 5 || grid[i][j] == 0 || grid[i][j] == 5 || grid[i][j] > 9)
                    continue;

                int index1 = indexes[grid[i][j]];
                int index2 = index1;
                for (int k = 0; k < 8; k++) {
                    int num = grid[i + rows[k]][j + cols[k]];
                    // 正序 & 逆序
                    if (num != nums[index1++] & num != nums[index2--])
                        continue label;
                }
                count++;
            }
        }
        return count;
    }

}
