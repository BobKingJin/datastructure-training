package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2025/4/6 13:02
 * @Author: BobKing
 * @Description:
 */
public class Number1926 {

    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] directions = {{0, 1, 0, -1}, {1, 0, -1, 0}};
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{entrance[0], entrance[1], 0});
        // 遍历到的坐标变为墙 防止重复遍历
        maze[entrance[0]][entrance[1]] = '+';

        while (!queue.isEmpty()) {
            int[] curLocal = queue.poll();
            int row = curLocal[0];
            int col = curLocal[1];
            for (int i = 0; i < 4; i++) {
                int x = row + directions[0][i];
                int y = col + directions[1][i];
                if (x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1 && maze[x][y] == '.') {
                    if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                        return curLocal[2] + 1;
                    }
                    queue.offer(new int[]{x, y, curLocal[2] + 1});
                    maze[x][y] = '+';
                }
            }
        }
        return -1;
    }

}
