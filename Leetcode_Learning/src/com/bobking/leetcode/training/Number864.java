package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2022-11-10 10:26
 */
public class Number864 {

    int N = 35;
    int K = 10;
    int INF = 0x3f3f3f3f;
    int[][][] dist = new int[N][N][1 << K];
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // 参考：https://leetcode.cn/problems/shortest-path-to-get-all-keys/solution/by-ac_oier-5gxc/
    public int shortestPathAllKeys(String[] grid) {

        // 利用「钥匙数量不超过 6，并按字母顺序排列」，可以使用一个 int 类型二进制数 state 来代指当前收集到钥匙情况
        // 若 state 的二进制中的第 k 位为 1，代表当前种类编号为 k 的钥匙 已被收集，后续移动若遇到对应的锁则 能通过
        // 若 state 的二进制中的第 k 位为 0，代表当前种类编号为 k 的钥匙 未被收集，后续移动若遇到对应的锁则 无法通过

        // 起始遍历一次棋盘，找到起点位置，并将其进行入队，队列维护的是 (x, y, state) 三元组状态
        // （其中 (x, y) 代表当前所在的棋盘位置，state 代表当前的钥匙收集情况）
        // 同时统计整个棋盘所包含的钥匙数量 cnt，并使用 数组/哈希表 记录到达每个状态所需要消耗的最小步数 step
        // 进行四联通方向的 BFS，转移过程中需要注意「遇到锁时，必须有对应钥匙才能通过」&「遇到钥匙时，需要更新对应的 state 再进行入队」
        // 当 BFS 过程中遇到 state = (1 << cnt) - 1 时，代表所有钥匙均被收集完成，可结束搜索

        int n = grid.length;
        int m = grid[0].length();
        int cnt = 0;
        Deque<int[]> d = new ArrayDeque<int[]>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], INF);
                char c = grid[i].charAt(j);
                if (c == '@') {
                    d.addLast(new int[]{i, j, 0});
                    dist[i][j][0] = 0;
                } else if (c >= 'a' && c <= 'z') {
                    cnt++;
                }
            }
        }

        while (!d.isEmpty()) {

            int[] info = d.pollFirst();
            int x = info[0];
            int y = info[1];
            int cur = info[2];
            int step = dist[x][y][cur];

            for (int[] di : dirs) {
                int nx = x + di[0];
                int ny = y + di[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                char c = grid[nx].charAt(ny);
                if (c == '#')
                    continue;
                if ((c >= 'A' && c <= 'Z') && (cur >> (c - 'A') & 1) == 0)
                    continue;
                int ncur = cur;
                if (c >= 'a' && c <= 'z')
                    ncur |= 1 << (c - 'a');
                if (ncur == (1 << cnt) - 1)
                    return step + 1;
                // 说明有从其它方向到 [nx, ny] 位置的步数更小
                if (step + 1 >= dist[nx][ny][ncur])
                    continue;
                dist[nx][ny][ncur] = step + 1;
                d.addLast(new int[]{nx, ny, ncur});
            }
        }
        return -1;
    }
}
