package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-06-25 22:41
 */
public class Number675 {

    private int N = 50;
    private int[][] g = new int[N][N];
    private int n;
    private int m;

    // 参考：https://leetcode.cn/problems/cut-off-trees-for-golf-event/solution/by-ac_oier-ksth/
    public int cutOffTree(List<List<Integer>> forest) {

        // 题目限定了只能按照「从低到高」的顺序进行砍树，并且图中不存在高度相等的两棵树，这意味着整个砍树的顺序唯一确定
        // 就是对所有有树的地方进行「高度」排升序，即是完整的砍树路线
        // 另外一个更为重要的性质是：点与点之间的最短路径，不会随着砍树过程的进行而发生变化（某个树点被砍掉，只会变为平地，不会变为阻碍点，仍可通过）
        // 综上，砍树的路线唯一确定，当求出每两个相邻的砍树点最短路径，并进行累加即是答案（整条砍树路径的最少步数）

        // 因此，再结合数据范围只有 50，并且点与点之间边权为 1（每次移动算一步），可以直接进行 BFS 进行求解
        // 先对整张图进行一次遍历，预处理出所有的树点（以三元组 (height, x, y) 的形式进行存储），并对其以 height 排升序，得到唯一确定的砍树路径

        n = forest.size();
        m = forest.get(0).size();
        List<int[]> list = new ArrayList<int[]>();
        // 预处理出所有的树点（以三元组 (height, x, y) 的形式进行存储）
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = forest.get(i).get(j);
                if (g[i][j] > 1)
                    list.add(new int[]{g[i][j], i, j});
            }
        }

        // 按高度进行排序，高度从小到大
        Collections.sort(list, (a, b) -> a[0] - b[0]);
        int x = 0;
        int y = 0;
        int ans = 0;
        for (int[] ne : list) {
            int nx = ne[1];
            int ny = ne[2];
            int d = bfs(x, y, nx, ny);
            if (d == -1)
                return -1;
            ans += d;
            x = nx;
            y = ny;
        }
        return ans;
    }

    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int bfs(int X, int Y, int P, int Q) {

        if (X == P && Y == Q)
            return 0;

        boolean[][] vis = new boolean[n][m];
        Deque<int[]> d = new ArrayDeque<int[]>();
        d.addLast(new int[]{X, Y});
        vis[X][Y] = true;
        int ans = 0;
        while (!d.isEmpty()) {
            int size = d.size();
            while (size-- > 0) {
                int[] info = d.pollFirst();
                int x = info[0];
                int y = info[1];
                for (int[] di : dirs) {
                    int nx = x + di[0];
                    int ny = y + di[1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                        continue;
                    if (g[nx][ny] == 0 || vis[nx][ny])
                        continue;
                    if (nx == P && ny == Q)
                        return ans + 1;
                    d.addLast(new int[]{nx, ny});
                    vis[nx][ny] = true;
                }
            }
            ans++;
        }
        return -1;
    }
}
