package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2023-06-23 8:37
 */
public class LCP41 {

    private int m;
    private int n;
    private String[] chessboard;

    // 参考：https://leetcode.cn/problems/fHi6rV/solution/python3javacgo-yi-ti-yi-jie-bfs-by-lcbin-e4vo/
    public int flipChess(String[] chessboard) {
        m = chessboard.length;
        n = chessboard[0].length();
        this.chessboard = chessboard;
        int ans = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (chessboard[i].charAt(j) == '.')
                    ans = Math.max(ans, bfs(i, j));
            }
        }
        return ans;
    }

    private int bfs(int i, int j) {

        Deque<int[]> q = new ArrayDeque<int[]>();
        q.offer(new int[] {i, j});
        // 不在原数组上面操作，将原数组拷贝一份到 g
        // 注意这个位置列长度可以先初始化为 0
        char[][] g = new char[m][0];
        for (int k = 0; k < m; ++k)
            g[k] = chessboard[k].toCharArray();

        g[i][j] = 'X';
        int cnt = 0;

        while (!q.isEmpty()) {

            int[] p = q.poll();
            i = p[0];
            j = p[1];
            for (int a = -1; a <= 1; ++a) {
                for (int b = -1; b <= 1; ++b) {
                    if (a == 0 && b == 0)
                        continue;
                    int x = i + a;
                    int y = j + b;
                    while (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'O') {
                        x += a;
                        y += b;
                    }
                    if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == 'X') {
                        x -= a;
                        y -= b;
                        cnt += Math.max(Math.abs(x - i), Math.abs(y - j));
                        while (x != i || y != j) {
                            g[x][y] = 'X';
                            q.offer(new int[] {x, y});
                            x -= a;
                            y -= b;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
