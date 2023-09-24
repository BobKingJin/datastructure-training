package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2022-08-06 12:42
 */
public class Number542 {

    // 参考：https://leetcode.cn/problems/01-mat/solution/2chong-bfs-xiang-jie-dp-bi-xu-miao-dong-by-sweetie/
    public int[][] updatemat1(int[][] mat) {

        // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是 未被访问过的 1
        Queue<int[]> queue = new LinkedList<int[]>();
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                // 如果四邻域的点是 -1，表示这个点是未被访问过的 1
                // 所以这个点到 0 的距离就可以更新成 mat[x][y] + 1
                if (newX >= 0 && newX < m && newY >= 0 && newY < n
                        && mat[newX][newY] == -1) {
                    mat[newX][newY] = mat[x][y] + 1;
                    queue.offer(new int[] {newX, newY});
                }
            }
        }

        return mat;
    }

    // 参考：https://leetcode.cn/problems/01-mat/solution/2chong-bfs-xiang-jie-dp-bi-xu-miao-dong-by-sweetie/
    public int[][] updatemat2(int[][] mat) {

        // 首先将 0 边上的 1 入队
        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        Queue<int[]> queue = new LinkedList<int[]>();
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < m && y >= 0 && y < n
                                && mat[x][y] == 1 && res[x][y] == 0) {
                            // 这是在 0 边上的 1，需要加上 res[x][y] == 0 的判断防止重复入队
                            res[x][y] = 1;
                            queue.offer(new int[] {x, y});
                        }
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= 0 && newX < m && newY >= 0 && newY < n
                        && mat[newX][newY] == 1 && res[newX][newY] == 0) {
                    res[newX][newY] = res[x][y] + 1;
                    queue.offer(new int[] {newX, newY});
                }
            }
        }

        return res;
    }

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 参考：https://leetcode.cn/problems/01-mat/solution/01ju-zhen-by-leetcode-solution/
    public int[][] updatemat3(int[][] mat) {

        // 从一个固定的 1 走到任意一个 0，在距离最短的前提下可能有四种方法：
        // 只有 水平向左移动 和 竖直向上移动
        // 只有 水平向左移动 和 竖直向下移动
        // 只有 水平向右移动 和 竖直向上移动
        // 只有 水平向右移动 和 竖直向下移动

        int m = mat.length;
        int n = mat[0].length;

        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i)
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);

        // 如果 (i, j) 的元素为 0，那么距离为 0
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 0)
                    dist[i][j] = 0;
            }
        }
        // 只有 水平向左移动 和 竖直向上移动，注意动态规划的计算顺序
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i - 1 >= 0)
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                if (j - 1 >= 0)
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
            }
        }
        // 只有 水平向左移动 和 竖直向下移动，注意动态规划的计算顺序
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                if (i + 1 < m)
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                if (j - 1 >= 0)
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
            }
        }
        // 只有 水平向右移动 和 竖直向上移动，注意动态规划的计算顺序
        for (int i = 0; i < m; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i - 1 >= 0)
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                if (j + 1 < n)
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
            }
        }
        // 只有 水平向右移动 和 竖直向下移动，注意动态规划的计算顺序
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (i + 1 < m)
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                if (j + 1 < n)
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
            }
        }
        return dist;
    }

    // 参考：https://leetcode.cn/problems/01-mat/solution/2chong-bfs-xiang-jie-dp-bi-xu-miao-dong-by-sweetie/
    public int[][] updatemat4(int[][] mat) {

        // 对于任一点 (i, j)，距离 0 的距离为：
        // f(i, j) = 1 + min(f(i − 1, j), f(i, j − 1), f(i + 1, j), f(i, j + 1)) if mat[i][j] == 1
        // f(i, j) = 0 if mat[i][j] == 0
        // 用 dp[i][j] 来表示该位置距离最近的 0 的距离
        // 发现 dp[i][j] 是由其上下左右四个状态来决定，无法从一个方向开始递推
        // 于是尝试将问题分解：
        // 距离 (i, j) 最近的 0 的位置，是在其 「左上，右上，左下，右下」4个方向之一
        // 因此分别从四个角开始递推，就分别得到了位于「左上方、右上方、左下方、右下方」距离 (i, j) 的最近的 0 的距离，取 min 即可
        // 通过上两步思路，可以很容易的写出 4 个双重 for 循环
        // 如果第三步还不满足的话，从四个角开始的 4 次递推，其实还可以优化成从任一组对角开始的 2 次递推
        // 比如只写从左上角、右下角开始递推就行了
        // 首先从左上角开始递推 dp[i][j] 是由其 「左方」和 「左上方」的最优子状态决定的
        // 然后从右下角开始递推 dp[i][j] 是由其 「右方」和 「右下方」的最优子状态决定的
        // 看起来第一次递推的时候，把「右上方」的最优子状态给漏掉了，其实不是的，因为第二次递推的时候「右方」的状态在第一次递推时已经包含了「右上方」的最优子状态了
        // 看起来第二次递推的时候，把「左下方」的最优子状态给漏掉了，其实不是的，因为第二次递推的时候「右下方」的状态在第一次递推时已经包含了「左下方」的最优子状态了

        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                dp[i][j] = mat[i][j] == 0 ? 0 : 10000;
        }

        // 从左上角开始
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                if (j - 1 >= 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
            }
        }
        // 从右下角开始
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i + 1 < m)
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                if (j + 1 < n)
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
            }
        }
        return dp;
    }
}
