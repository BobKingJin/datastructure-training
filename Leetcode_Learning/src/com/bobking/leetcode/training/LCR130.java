package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2023-11-05 8:13
 */
public class LCR130 {

    private int m;
    private int n;
    private int cnt;
    private boolean[][] visited;

    // 参考: https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solutions/110056/mian-shi-ti-13-ji-qi-ren-de-yun-dong-fan-wei-dfs-b/
    public int wardrobeFinishing1(int m, int n, int cnt) {
        this.m = m;
        this.n = n;
        this.cnt = cnt;
        this.visited = new boolean[m][n];
        return dfs(0, 0, 0, 0);
    }

    private int dfs(int i, int j, int si, int sj) {

        if (i >= m || j >= n || cnt < si + sj || visited[i][j])
            return 0;

        visited[i][j] = true;
        return 1 + dfs(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj) + dfs(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
    }

    // 参考: https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solutions/110056/mian-shi-ti-13-ji-qi-ren-de-yun-dong-fan-wei-dfs-b/
    public int wardrobeFinishing2(int m, int n, int cnt) {

        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0, 0, 0, 0});

        while (queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0];
            int j = x[1];
            int si = x[2];
            int sj = x[3];
            if (i >= m || j >= n || cnt < si + sj || visited[i][j])
                continue;
            visited[i][j] = true;
            res++;
            queue.add(new int[]{i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj});
            queue.add(new int[]{i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8});
        }

        return res;
    }
}
