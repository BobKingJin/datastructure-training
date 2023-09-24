package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-05-15 15:45
 */
public class Number463 {

    // 参考：https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
    // 参考：https://leetcode-cn.com/problems/island-perimeter/solution/tu-jie-jian-ji-er-qiao-miao-de-dfs-fang-fa-java-by/
    public int islandPerimeter1(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 因为只会存在一个大陆地，即只有一块多个 1 相连接的陆地，所以只需要一遇到第一个陆地，进行递归即可
                if (grid[i][j] == 1)
                    return dfs(grid, i, j);
            }
        }

        return 0;
    }

    // 岛屿的周长就是岛屿方格和非岛屿方格相邻的边的数量
    private int dfs(int[][] grid, int i, int j) {

        // 从一个岛屿方格走向网格边界，周长加 1
        if (!inIsland(grid, i, j))
            return 1;
        // 从一个岛屿方格走向一个非岛屿方格，就将周长加 1
        if (grid[i][j] == 0)
            return 1;
        // 已经遍历过
        if (grid[i][j] != 1)
            return 0;

        grid[i][j] = 2;
        return dfs(grid, i + 1, j)
                + dfs(grid, i - 1, j)
                + dfs(grid, i, j + 1)
                + dfs(grid, i, j - 1);
    }

    private boolean inIsland(int[][] grid, int i, int j) {

        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length)
            return true;
        return false;
    }

    // 参考：https://leetcode-cn.com/problems/island-perimeter/solution/dao-yu-de-zhou-chang-by-leetcode-solution/
    public int islandPerimeter2(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int rowLen = grid.length;
        int colLen = grid[0].length;
        int res = 0;

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 1) {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= rowLen || ty < 0 || ty >= colLen || grid[tx][ty] == 0)
                            count += 1;
                    }
                    res += count;
                }
            }
        }

        return res;
    }

}
