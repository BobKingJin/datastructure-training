package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-02 22:16
 */
public class Number1706 {

    int m;
    int n;
    int[][] g;

    // 参考：https://leetcode.cn/problems/where-will-the-ball-fall/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-jz6f/
    public int[] findBall(int[][] grid) {

        // 使用 r 和 c 表示小球当前所处的位置
        // c 的变化，则是取决于当前挡板 grid[r][c] 的方向
        // 若 grid[r][c] 为 1，则小球的下一个位置为 (r + 1, c + 1)
        // 若 grid[r][c] 为 -1，则下一位置为 (r + 1, c - 1)
        // 即可以统一表示为 (r + 1, c + grid[r][c])
        // 当且仅当小球在本次移动过程中没被卡住，才能继续移动
        // 即只有 c + grid[r][c] 没有超过矩阵的左右边界（没有被边界卡住），或者 grid[r][c] 和 grid[r][c + grid[r][c]] 同向（不形成夹角），小球方能继续移动

        g = grid;
        m = g.length;
        n = g[0].length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++)
            ans[i] = getVal(i);

        return ans;
    }

    private int getVal(int x) {

        int r = 0;
        int c = x;

        while (r < m) {
            int ne = c + g[r][c];
            if (ne < 0 || ne >= n)
                return -1;
            if (g[r][c] != g[r][ne])
                return -1;
            r++;
            c = ne;
        }

        return c;
    }
}
