package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-11-15 8:13
 */
public class Number1267 {

    public int countServers(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        Map<Integer, Integer> rows = new HashMap<Integer, Integer>();
        Map<Integer, Integer> cols = new HashMap<Integer, Integer>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    rows.put(i, rows.getOrDefault(i, 0) + 1);
                    cols.put(j, cols.getOrDefault(j, 0) + 1);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && (rows.get(i) > 1 || cols.get(j) > 1))
                    ++ans;
            }
        }
        return ans;
    }
}
