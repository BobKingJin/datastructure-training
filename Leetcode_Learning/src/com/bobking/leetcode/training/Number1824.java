package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-02-17 15:15
 */
public class Number1824 {

    // 参考：https://leetcode.cn/problems/minimum-sideway-jumps/solution/cong-0-dao-1-de-0-1-bfspythonjavacgo-by-1m8z4/
    public int minSideJumps(int[] obstacles) {

        int n = obstacles.length;
        int[][] dis = new int[n][3];
        for (int i = 0; i < n; ++i)
            Arrays.fill(dis[i], n);
        dis[0][1] = 0;
        ArrayDeque<int[]> q = new ArrayDeque<int[]>();
        q.add(new int[]{0, 1});

        for (;;) {
            int[] p = q.pollFirst();
            int i = p[0];
            int j = p[1];
            int d = dis[i][j];
            if (i == n - 1)
                return d;
            // 向右
            if (obstacles[i + 1] != j + 1 && d < dis[i + 1][j]) {
                dis[i + 1][j] = d;
                q.addFirst(new int[]{i + 1, j});
            }
            // 枚举另外两条跑道（向上/向下）
            for (int k : new int[]{(j + 1) % 3, (j + 2) % 3}) {
                if (obstacles[i] != k + 1 && d + 1 < dis[i][k]) {
                    dis[i][k] = d + 1;
                    q.addLast(new int[]{i, k});
                }
            }
        }
    }
}
