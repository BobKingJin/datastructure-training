package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-31 14:46
 */
public class Number2257 {

    int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {

        int ans = 0;
        // 用于存储当前格子的状态(围墙W/守卫G/守卫守护的范围I)
        char[][] grid = new char[m][n];

        for (int[] guard : guards)
            grid[guard[0]][guard[1]] = 'G';

        for (int[] wall : walls)
            grid[wall[0]][wall[1]] = 'W';

        // 对于每一个守卫，会有三种情况
        // 1；前方什么都没有，可以一直看下去
        // 2；前方有一堵墙，会阻碍视线
        // 3；前方又有一个守卫，这时候视线方向已经被前方的守卫所看到
        for (int[] guard : guards) {
            int x = guard[0];
            int y = guard[1];
            for (int i = 0; i < 4; i++) {
                int nx = x +  direction[i][0];
                int ny = y + direction[i][1];
                while (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] != 'G' && grid[nx][ny] != 'W') {
                    // 前方不是守卫也不是墙，继续沿着这个方向看过去，并且将视野所及标为 I
                    grid[nx][ny] = 'I';
                    nx += direction[i][0];
                    ny += direction[i][1];
                }
            }
        }

        // 不是墙，守卫，视野所及区域都为答案
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 'G' && grid[i][j] != 'W' && grid[i][j] != 'I')
                    ans++;
            }
        }
        return ans;
    }
}
