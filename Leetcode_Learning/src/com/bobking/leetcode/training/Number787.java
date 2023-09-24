package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-05-18 8:00
 */
public class Number787 {

    int N = 110;
    int INF = 0x3f3f3f3f;
    int[][] g = new int[N][N];
    int[] dist = new int[N];
    int n;
    int src;
    int dst;
    int k;

    // 参考：https://leetcode.cn/problems/cheapest-flights-within-k-stops/solution/gong-shui-san-xie-xiang-jie-bellman-ford-dc94/
    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {

        this.n = n;
        this.src = src;
        this.dst = dst;
        this.k = k + 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                g[i][j] = (i == j) ? 0 : INF;
        }

        for (int[] f : flights)
            g[f[0]][f[1]] = f[2];

        int ans = bf1();
        return ans > INF / 2 ? -1 : ans;
    }

    private int bf1() {

        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int limit = 0; limit < k; limit++) {
            int[] clone = dist.clone();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    dist[j] = Math.min(dist[j], clone[i] + g[i][j]);
            }
        }
        return dist[dst];
    }

    class Edge {
        int x;
        int y;
        int w;
        Edge(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    List<Edge> list = new ArrayList<Edge>();
    int m;

    // 参考：https://leetcode.cn/problems/cheapest-flights-within-k-stops/solution/gong-shui-san-xie-xiang-jie-bellman-ford-dc94/
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {

        this.n = n;
        this.src = src;
        this.dst = dst;
        this.k = k + 1;

        for (int[] f : flights)
            list.add(new Edge(f[0], f[1], f[2]));

        m = list.size();
        int ans = bf2();
        return ans > INF / 2 ? -1 : ans;
    }

    private int bf2() {
        Arrays.fill(dist, INF);
        dist[src] = 0;
        for (int i = 0; i < k; i++) {
            int[] clone = dist.clone();
            for (Edge e : list) {
                int x = e.x;
                int y = e.y;
                int w = e.w;
                dist[y] = Math.min(dist[y], clone[x] + w);
            }
        }
        return dist[dst];
    }

    // 参考：https://leetcode.cn/problems/cheapest-flights-within-k-stops/solution/gong-shui-san-xie-xiang-jie-bellman-ford-dc94/
    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int k) {

        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int limit = 0; limit < k + 1; limit++) {
            int[] clone = dist.clone();
            for (int[] f : flights) {
                int x = f[0];
                int y = f[1];
                int w = f[2];
                dist[y] = Math.min(dist[y], clone[x] + w);
            }
        }

        return dist[dst] > INF / 2 ? -1 : dist[dst];
    }

}
