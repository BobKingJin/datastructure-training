package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

public class Number529 {

    // 定义 8 个方向
    int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
    int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};

    // 参考：https://leetcode-cn.com/problems/minesweeper/solution/cong-qi-dian-kai-shi-dfs-bfs-bian-li-yi-bian-ji-ke/
    public char[][] updateBoard1(char[][] board, int[] click) {

        if (board == null || board[0] == null || board.length == 0 || board[0].length == 0 || click == null || click.length == 0)
            return board;

        int x = click[0];
        int y = click[1];
        // 1. 若起点是雷，游戏结束，直接修改 board 并返回
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            // 2. 若起点是空地，则从起点开始向 8 邻域的空地进行深度优先搜索
            dfs(board, x, y);
        }

        return board;
    }

    private void dfs(char[][] board, int i, int j) {

        // 递归终止条件：判断空地 (i, j) 周围是否有雷
        // 若有，则将该位置修改为雷数，终止该路径的搜索
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
                continue;

            if (board[x][y] == 'M')
                cnt++;
        }

        // 若有，则将该位置修改为雷数，终止该路径的搜索
        if (cnt > 0) {
            board[i][j] = (char) (cnt + '0');
            return;
        }

        // 若空地 (i, j) 周围没有雷，则将该位置修改为 'B'，向 8 邻域的空地继续搜索
        board[i][j] = 'B';
        for (int k = 0; k < 8; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E')
                continue;
            dfs(board, x, y);
        }
    }

    // 参考：https://leetcode-cn.com/problems/minesweeper/solution/cong-qi-dian-kai-shi-dfs-bfs-bian-li-yi-bian-ji-ke/
    public char[][] updateBoard2(char[][] board, int[] click) {

        if (board == null || board[0] == null || board.length == 0 || board[0].length == 0 || click == null || click.length == 0)
            return board;

        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        // 2. 若起点是空地，则将起点入队，从起点开始向 8 邻域的空地进行宽度优先搜索
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {

            int[] point = queue.poll();
            int i = point[0];
            int j = point[1];
            // 判断空地 (i, j) 周围是否有雷
            int cnt = 0;
            for (int k = 0; k < 8; k++) {
                int newX = i + dx[k];
                int newY = j + dy[k];
                if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length)
                    continue;
                if (board[newX][newY] == 'M')
                    cnt++;
            }
            // 若空地 (i, j) 周围有雷，则将该位置修改为雷数
            // 否则将该位置更新为 'B'，并将其 8 邻域中的空地入队，继续进行 bfs 搜索
            if (cnt > 0) {
                board[i][j] = (char) (cnt + '0');
            } else {
                board[i][j] = 'B';
                for (int k = 0; k < 8; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];
                    if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length
                            || board[newX][newY] != 'E' || visited[newX][newY])
                        continue;
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }

        return board;
    }


}
