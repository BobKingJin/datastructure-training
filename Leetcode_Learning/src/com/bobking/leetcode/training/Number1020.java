package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

public class Number1020 {

    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int R;
    int C;

    // 参考：https://leetcode-cn.com/problems/number-of-enclaves/solution/floodfillzhi-by-a-fei-8-tkld/
    public int numEnclaves1(int[][] A) {

        if (A == null || A[0] == null || A.length == 0 || A[0].length == 0)
            return 0;

        R = A.length;
        C = A[0].length;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 边界上的 1 作为种子来进行扩散
                // 即从边界上的 1 出发，遍历其关联的所有 1
                if (isBoard(r, c) && A[r][c] == 1)
                    bfs(A, r, c);
            }
        }
        // 统计 1 的个数（即封闭的陆地的个数）
        int cnt = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++)
                if (A[r][c] == 1)
                    cnt++;
        }

        return cnt;
    }

    private void bfs(int[][] A, int sr, int sc) {

        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{sr, sc});
        // 边缘进来的点是 1，染色为 0
        A[sr][sc] = 0;
        while (!q.isEmpty()) {
            int[] c = q.poll();
            int cr = c[0];
            int cc = c[1];
            for (int[] d : dirs) {
                int nr = cr + d[0];
                int nc = cc + d[1];
                if (!inArea(nr, nc))
                    continue;
                // 如果当前点是陆地 1，翻转为 0
                if (A[nr][nc] == 1) {
                    q.offer(new int[]{nr, nc});
                    A[nr][nc] = 0;
                }
            }
        }
    }

    // 边缘的点
    private boolean isBoard(int r, int c) {
        return r == 0 || r == R - 1 || c == 0 || c == C - 1;
    }

    // 在区域内的点
    private boolean inArea(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    // 参考：https://leetcode-cn.com/problems/number-of-enclaves/solution/floodfillzhi-by-a-fei-8-tkld/
    public int numEnclaves2(int[][] A) {

        if (A == null || A[0] == null || A.length == 0 || A[0].length == 0)
            return 0;

        R = A.length;
        C = A[0].length;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++)
                if (isBoard(r, c) && A[r][c] == 1)
                    dfs(A, r, c);
        }
        // 统计 1 的个数（即封闭的陆地的个数）
        int cnt = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (A[r][c] == 1)
                    cnt++;
            }
        }

        return cnt;
    }

    private void dfs(int[][] A, int r, int c) {

        if (!inArea(r, c) || A[r][c] != 1)
            return;

        A[r][c] = 0;
        for (int[] d : dirs)
            dfs(A, r + d[0], c + d[1]);
    }


}
