package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-10-21 10:57
 */
public class Number1260 {

    // 参考：https://leetcode.cn/problems/shift-2d-grid/solution/by-ac_oier-1blt/
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int n = grid.length;
        int m = grid[0].length;
        int[][] mat = new int[n][m];

        for (int i = 0; i < m; i++) {
            int tcol = (i + k) % m;
            int trow = ((i + k) / m) % n;
            int idx = 0;
            while (idx != n)
                mat[(trow++) % n][tcol] = grid[idx++][i];
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        for (int i = 0; i < n; i++) {
            List<Integer> alist = new ArrayList<Integer>();
            for (int j = 0; j < m; j++)
                alist.add(mat[i][j]);
            ans.add(alist);
        }

        return ans;
    }
}
