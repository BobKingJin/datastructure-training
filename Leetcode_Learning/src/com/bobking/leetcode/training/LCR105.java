package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2023/12/10 7:26
 * @Author: BobKing
 * @Description:
 */
public class LCR105 {

    public int maxAreaOfIsland1(int[][] grid) {

        int ans = 0;

        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j)
                ans = Math.max(ans, dfs(grid, i, j));
        }
        return ans;
    }

    private int dfs(int[][] grid, int curI, int curJ) {

        if (curI < 0 || curJ < 0 || curI == grid.length || curJ == grid[0].length || grid[curI][curJ] != 1)
            return 0;

        grid[curI][curJ] = 0;
        int[] di = {0, 0, 1, -1};
        int[] dj = {1, -1, 0, 0};
        int ans = 1;

        for (int index = 0; index != 4; ++index) {
            int nextI = curI + di[index];
            int nextJ = curJ + dj[index];
            ans += dfs(grid, nextI, nextJ);
        }
        return ans;
    }

    public int maxAreaOfIsland2(int[][] grid) {

        int ans = 0;

        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                int cur = 0;
                Deque<Integer> stacki = new LinkedList<Integer>();
                Deque<Integer> stackj = new LinkedList<Integer>();
                stacki.push(i);
                stackj.push(j);
                while (!stacki.isEmpty()) {
                    int curI = stacki.pop();
                    int curJ = stackj.pop();
                    if (curI < 0 || curJ < 0 || curJ == grid.length || curJ == grid[0].length || grid[curI][curJ] != 1)
                        continue;
                    ++cur;
                    grid[curI][curJ] = 0;
                    int[] di = {0, 0, 1, -1};
                    int[] dj = {1, -1, 0, 0};
                    for (int index = 0; index != 4; ++index) {
                        int nextI = curI + di[index];
                        int nextJ = curJ + dj[index];
                        stacki.push(nextI);
                        stackj.push(nextJ);
                    }
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

    public int maxAreaOfIsland3(int[][] grid) {

        int ans = 0;

        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                int cur = 0;
                Queue<Integer> queuei = new LinkedList<Integer>();
                Queue<Integer> queuej = new LinkedList<Integer>();
                queuei.offer(i);
                queuej.offer(j);
                while (!queuei.isEmpty()) {
                    int curI = queuei.poll();
                    int curJ = queuej.poll();
                    if (curI < 0 || curJ < 0 || curI == grid.length || curJ == grid[0].length || grid[curI][curJ] != 1)
                        continue;
                    ++cur;
                    grid[curI][curJ] = 0;
                    int[] di = {0, 0, 1, -1};
                    int[] dj = {1, -1, 0, 0};
                    for (int index = 0; index != 4; ++index) {
                        int nextI = curI + di[index];
                        int nextJ = curJ + dj[index];
                        queuei.offer(nextI);
                        queuej.offer(nextJ);
                    }
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

}
