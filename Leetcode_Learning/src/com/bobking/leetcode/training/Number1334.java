package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number1334 {

    // 参考：https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/solution/yu-zhi-ju-chi-nei-lin-ju-zui-shao-de-cheng-shi-flo/
    public int findTheCity1(int n, int[][] edges, int distanceThreshold) {


        // Floyd 算法，又称插点法，其中算法的核心思想是动态规划
        // 算法步骤
        // 1.通过已知条件初始化距离矩阵 D[n][n] ，其中 D[i][j] 表示，顶点 i 到顶点 j 的距离
        // 2.n 个顶点依次作为插入点，例如，k 为其中一个顶点，D[i][k] + D[k][j] < D[i][j]
        // 那说明顶点 i 经过顶点 k 再到达 j ，比直接到达 j 要近。所以更新 D[i][j]
        // 3.可以归纳得到状态转移方程：D[i][j] = min(D[i ,k] + D[k, j], D[i, j])

        int[][] map = new int[n][n];

        // init
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // get map
        for (int[] e : edges)
            map[e[0]][e[1]] = map[e[1]][e[0]] = e[2];

        // 为什么遍历插入点 k 是放在第一层循环？
        // 这个源自 FloydFloyd 的核心思想--动态规划
        // 代码中的二维状态转移方程 D[i][j] = min(D[i, k] + D[k, j], D[i, j])，其实是从三维简化得到的
        // 不妨从最初的三维说起：
        // 1.首先定义状态数组（也就是距离矩阵）D[n][n][n]，其中 D[k][i][j] 表示顶点 i, 顶点 j 通过前 k 个顶点得到的最短距离
        // 2.D[k][i][j] 是从 D[k - 1][i][j] 和 D[k - 1][i][k] + D[k - 1][k][j] 两者中值较小的一个转移得到的
        // 也就是说要在前 k - 1 个顶点已经插入，更新距离矩阵状态之后，第 k 个顶点才能作为插入顶点
        // 3.归纳得到状态转移方程：D[k][i][j] = min(D[k - 1][i][j], D[k - 1][i][k] + D[k - 1][k][j])
        // 其中 k 的作用是标志到达了第几个插入点，也就是状态数组到达了哪个状态，不用刻意记录，于是减去第一维就变成了二维
        // 明白了 FloydFloyd 的三维 dp 思想，根据状态转移方程在编码时就很自然的会将 k 放在第一层循环，而将 k 放在最后一层则是错误的编码

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 当 map[i][k] == Integer.MAX_VALUE 时说明 i 无法到达 k
                    // 当 map[k][j] == Integer.MAX_VALUE 时说明 k 无法到达 j
                    if (i != k && j != k && map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE)
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        // get res
        int min = n + 1;
        int res = -1;
        for (int i = 0; i < n; i++) {

            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && map[i][j] <= distanceThreshold)
                    count++;
            }

            if (min >= count) {
                min = count;
                res = i;
            }
        }

        return res;
    }

    // dijkstra算法
    // 算法主要特点是从起始点开始，采用贪心算法的策略，每次遍历到始点距离最近且未访问过的顶点的邻接节点，直到扩展到终点为止
    // 参考：https://baike.baidu.com/item/迪克斯特拉算法/23665989
    public int findTheCity2(int n, int[][] edges, int distanceThreshold) {

        int[][] map = new int[n][n];
        final int INF = 0x3f3f3f3f;
        // init map
        for (int[] ints : map)
            Arrays.fill(ints, INF);

        for (int i = 0; i < n; i++)
            map[i][i] = 0;

        for (int[] e : edges)
            map[e[0]][e[1]] = map[e[1]][e[0]] = e[2];

        int res = 0;
        int MIN = n + 1;

        // 以每一个节点为起始点
        for (int i = 0; i < n; i++) {

            int[] dist = new int[n];
            boolean[] set = new boolean[n];
            // 初始化起始点到另外每个节点的距离
            for (int v = 0; v < n; v++)
                dist[v] = map[i][v];

            dist[i] = 0;
            set[i] = true;
            for (int j = 0; j < n - 1; j++) {
                int min = INF;
                int start = i;
                // 找到 起始点 到相邻其它节点的最小值
                for (int k = 0; k < n; k++) {
                    if (!set[k] && dist[k] < min) {
                        min = dist[k];
                        start = k;
                    }
                }

                set[start] = true;
                for (int k = 0; k < n; k++) {
                    if (!set[k] && dist[k] > dist[start] + map[start][k])
                        dist[k] = dist[start] + map[start][k];
                }
            }

            int temp = 0;
            for (int j = 0; j < dist.length; j++) {
                if (dist[j] <= distanceThreshold)
                    temp++;
            }

            if (temp <= MIN) {
                MIN = temp;
                res = i;
            }
        }

        return res;
    }

}
