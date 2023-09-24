package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-21 10:07
 */
public class Number743 {

    int N = 110;
    int M = 6010;
    // 邻接矩阵数组：w[a][b] = c 代表从 a 到 b 有权重为 c 的边
    int[][] w = new int[N][N];
    int INF = 0x3f3f3f3f;
    int n;
    int k;

    // 参考：https://leetcode.cn/problems/network-delay-time/solution/gong-shui-san-xie-yi-ti-wu-jie-wu-chong-oghpz/
    public int networkDelayTime(int[][] times, int n, int k) {

        this.n = n;
        this.k = k;
        // 初始化邻接矩阵
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                w[i][j] = w[j][i] = i == j ? 0 : INF;
        }
        // 存图
        for (int[] t : times) {
            int u = t[0];
            int v = t[1];
            int c = t[2];
            w[u][v] = c;
        }
        // 最短路
        floyd();
        // 遍历答案
        int ans = 0;
        for (int i = 1; i <= n; i++)
            ans = Math.max(ans, w[k][i]);

        return ans >= INF / 2 ? -1 : ans;
    }

    private void floyd() {
        // floyd 基本流程为三层循环：
        // 枚举中转点 - 枚举起点 - 枚举终点 - 松弛操作
        for (int p = 1; p <= n; p++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++)
                    w[i][j] = Math.min(w[i][j], w[i][p] + w[p][j]);
            }
        }
    }
}
