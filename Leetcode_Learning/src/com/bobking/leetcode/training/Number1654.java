package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2023-06-19 8:19
 */
public class Number1654 {

    // 参考：https://leetcode.cn/problems/minimum-jumps-to-reach-home/solution/dao-jia-de-zui-shao-tiao-yue-ci-shu-zui-duan-lu-zh/
    public int minimumJumps(int[] forbidden, int a, int b, int x) {

        int bound = Math.max(Arrays.stream(forbidden).max().getAsInt() + a + b, x + b) + 1;
        boolean[][] vis = new boolean[bound][2];
        for (int i : forbidden) {
            vis[i][0] = true;
            vis[i][1] = true;
        }

        vis[0][0] = true;

        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{0, 0});
        int step = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] poll = q.poll();
                int cur = poll[0];
                if (cur == x)
                    return step;

                if (poll[1] == 0 && cur - b >= 0 && !vis[cur - b][1]) {
                    vis[cur - b][1] = true;
                    q.offer(new int[]{cur - b, 1});
                }
                if (cur + a < bound && !vis[cur + a][0]) {
                    vis[cur + a][0] = true;
                    q.offer(new int[]{cur + a, 0});
                }
            }
            step++;
        }

        return -1;
    }
}
