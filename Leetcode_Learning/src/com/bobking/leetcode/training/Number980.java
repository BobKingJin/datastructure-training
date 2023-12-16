package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2023/12/17 6:11
 * @Author: BobKing
 * @Description:
 */
public class Number980 {

    // 参考: https://leetcode.cn/problems/unique-paths-iii/solutions/2372252/liang-chong-fang-fa-hui-su-zhuang-tai-ya-26py/?envType=daily-question&envId=2023-12-17
    public int uniquePathsIII1(int[][] grid) {

        int cnt0 = 0;
        int sx = -1;
        int sy = -1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    cnt0++;
                } else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        return dfs1(grid, sx, sy, cnt0 + 1); // +1 把起点也算上
    }

    private int dfs1(int[][] grid, int x, int y, int left) {

        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] < 0)
            return 0;

        if (grid[x][y] == 2)
            // 必须访问所有的无障碍方格
            return left == 0 ? 1 : 0;

        grid[x][y] = -1;
        int ans = dfs1(grid, x - 1, y, left - 1) + dfs1(grid, x, y - 1, left - 1) +
                dfs1(grid, x + 1, y, left - 1) + dfs1(grid, x, y + 1, left - 1);
        grid[x][y] = 0;
        return ans;
    }

    private Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    // 参考: https://leetcode.cn/problems/unique-paths-iii/solutions/2372252/liang-chong-fang-fa-hui-su-zhuang-tai-ya-26py/?envType=daily-question&envId=2023-12-17
    public int uniquePathsIII2(int[][] grid) {

        int n = grid[0].length;
        int vis = 0;
        int sx = -1;
        int sy = -1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] < 0) { // 把障碍方格算上
                    vis |= 1 << (i * n + j);
                } else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        return dfs2(grid, sx, sy, vis);
    }

    private int dfs2(int[][] grid, int x, int y, int vis) {

        int m = grid.length;
        int n = grid[0].length;
        int p = x * n + y;

        if (x < 0 || x >= m || y < 0 || y >= n || (vis >> p & 1) > 0)
            return 0;

        vis |= 1 << p;

        if (grid[x][y] == 2)
            return vis == (1 << m * n) - 1 ? 1 : 0;

        int key = (p << m * n) | vis;

        if (memo.containsKey(key))
            return memo.get(key);

        int ans = dfs2(grid, x - 1, y, vis) + dfs2(grid, x, y - 1, vis) +
                dfs2(grid, x + 1, y, vis) + dfs2(grid, x, y + 1, vis);
        memo.put(key, ans);
        return ans;
    }




}
