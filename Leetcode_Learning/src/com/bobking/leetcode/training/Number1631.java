package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-09-18 12:04
 */
public class Number1631 {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // 参考：https://leetcode.cn/problems/path-with-minimum-effort/solution/zui-xiao-ti-li-xiao-hao-lu-jing-by-leetc-3q2j/
    public int minimumEffortPath1(int[][] heights) {

        // 可以将这个问题转化成一个「判定性」问题，即：是否存在一条从左上角到右下角的路径，其经过的所有边权的最大值不超过 x？

        int m = heights.length;
        int n = heights[0].length;
        int left = 0;
        int right = 999999;
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.offer(new int[]{0, 0});
            boolean[] seen = new boolean[m * n];
            seen[0] = true;

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];
                for (int i = 0; i < 4; ++i) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                        queue.offer(new int[]{nx, ny});
                        seen[nx * n + ny] = true;
                    }
                }
            }

            if (seen[m * n - 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/path-with-minimum-effort/solution/zui-xiao-ti-li-xiao-hao-lu-jing-by-leetc-3q2j/
    public int minimumEffortPath2(int[][] heights) {

        // 将这 mn 个节点放入并查集中，实时维护它们的连通性
        // 由于需要找到从左上角到右下角的最短路径，因此可以将图中的所有边按照权值从小到大进行排序，并依次加入并查集中
        // 当加入一条权值为 x 的边之后，如果左上角和右下角从非连通状态变为连通状态，那么 x 即为答案

        int m = heights.length;
        int n = heights[0].length;
        List<int[]> edges = new ArrayList<int[]>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int id = i * n + j;
                if (i > 0)
                    edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});

                if (j > 0)
                    edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
            }
        }

        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });

        UnionFind uf = new UnionFind(m * n);
        int ans = 0;

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int v = edge[2];
            uf.unite(x, y);
            // 左上角和右下角连通
            if (uf.connected(0, m * n - 1)) {
                ans = v;
                break;
            }
        }

        return ans;
    }

    // 并查集模板
    private class UnionFind {
        int[] parent;
        int[] size;
        int n;
        // 当前连通分量数目
        int setCount;

        public UnionFind(int n) {
            this.n = n;
            this.setCount = n;
            this.parent = new int[n];
            this.size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; ++i)
                parent[i] = i;
        }

        public int findset(int x) {
            return parent[x] == x ? x : (parent[x] = findset(parent[x]));
        }

        public boolean unite(int x, int y) {

            x = findset(x);
            y = findset(y);

            if (x == y)
                return false;

            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            --setCount;
            return true;
        }

        public boolean connected(int x, int y) {
            x = findset(x);
            y = findset(y);
            return x == y;
        }
    }

    // 参考：https://leetcode.cn/problems/path-with-minimum-effort/solution/zui-xiao-ti-li-xiao-hao-lu-jing-by-leetc-3q2j/
    public int minimumEffortPath3(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;
        // 小根堆
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });

        pq.offer(new int[]{0, 0, 0});

        // (0, 0) -> (m, n) 的最大体力值
        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] seen = new boolean[m * n];

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0];
            int y = edge[1];
            int d = edge[2];
            int id = x * n + y;
            if (seen[id])
                continue;

            if (x == m - 1 && y == n - 1)
                break;

            seen[id] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }

        return dist[m * n - 1];
    }
}


