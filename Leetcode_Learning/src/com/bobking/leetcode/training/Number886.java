package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-10-16 9:45
 */
public class Number886 {

    // 参考：https://leetcode.cn/problems/possible-bipartition/solution/ran-se-fa-er-fen-tu-886-ke-neng-de-er-fen-fa-by-fe/
    public boolean possibleBipartition1(int n, int[][] dislikes) {

        boolean[][] disLike = new boolean[n + 1][n + 1];
        for (int[] arr : dislikes) {
            disLike[arr[0]][arr[1]] = true;
            disLike[arr[1]][arr[0]] = true;
        }
        // 存储每个人的分组情况  0：没有分组，1：分组1(或者叫分组a)，-1：分组2(或者叫分组b)
        // 初始化 都是 0
        int unAlloc = 0;
        int aGroup = 1;
        int bGroup = -1;
        int[] color = new int[n + 1];
        // 遍历每个人 尝试给分组
        for (int index = 1; index <= n; index++) {
            // 如果没分组 且分组失败
            if (color[index] == unAlloc && !alloc(disLike, color, index, aGroup, n))
                return false;
        }
        return true;
    }

    private boolean alloc(boolean[][] disLike, int[] color, int index, int group, int n) {

        color[index] = group;
        for (int i = 1; i <= n; i++) {

            if (i == index)
                continue;

            // 如果不喜欢
            if (disLike[index][i]) {
                if (color[i] == group)
                    return false;
                if (color[i] == 0 && !alloc(disLike, color, i, group * -1, n))
                    return false;
            }
        }
        return true;
    }

    // 参考：https://leetcode.cn/problems/possible-bipartition/solution/ke-neng-de-er-fen-fa-by-leetcode-solutio-guo7/
    public boolean possibleBipartition2(int n, int[][] dislikes) {

        int[] color = new int[n + 1];
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; ++i)
            g[i] = new ArrayList<Integer>();

        for (int[] p : dislikes) {
            g[p[0]].add(p[1]);
            g[p[1]].add(p[0]);
        }

        for (int i = 1; i <= n; ++i) {
            if (color[i] == 0 && !dfs(i, 1, color, g))
                return false;
        }

        return true;
    }

    private boolean dfs(int curnode, int nowcolor, int[] color, List<Integer>[] g) {
        color[curnode] = nowcolor;
        for (int nextnode : g[curnode]) {
            if (color[nextnode] != 0 && color[nextnode] == color[curnode])
                return false;
            if (color[nextnode] == 0 && !dfs(nextnode, 3 ^ nowcolor, color, g))
                return false;
        }
        return true;
    }

    // 参考：https://leetcode.cn/problems/possible-bipartition/solution/ke-neng-de-er-fen-fa-by-leetcode-solutio-guo7/
    public boolean possibleBipartition3(int n, int[][] dislikes) {

        int[] color = new int[n + 1];
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; ++i)
            g[i] = new ArrayList<Integer>();

        for (int[] p : dislikes) {
            g[p[0]].add(p[1]);
            g[p[1]].add(p[0]);
        }

        for (int i = 1; i <= n; ++i) {
            if (color[i] == 0) {
                Queue<Integer> queue = new ArrayDeque<Integer>();
                queue.offer(i);
                color[i] = 1;
                while (!queue.isEmpty()) {
                    int t = queue.poll();
                    for (int next : g[t]) {
                        if (color[next] > 0 && color[next] == color[t])
                            return false;
                        if (color[next] == 0) {
                            color[next] = 3 ^ color[t];
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return true;
    }

    // 参考：https://leetcode.cn/problems/possible-bipartition/solution/ke-neng-de-er-fen-fa-by-leetcode-solutio-guo7/
    public boolean possibleBipartition4(int n, int[][] dislikes) {

        int[] fa = new int[n + 1];
        Arrays.fill(fa, -1);
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; ++i)
            g[i] = new ArrayList<Integer>();

        for (int[] p : dislikes) {
            g[p[0]].add(p[1]);
            g[p[1]].add(p[0]);
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < g[i].size(); ++j) {
                unit(g[i].get(0), g[i].get(j), fa);
                if (isconnect(i, g[i].get(j), fa))
                    return false;
            }
        }
        return true;
    }

    public void unit(int x, int y, int[] fa) {
        x = findFa(x, fa);
        y = findFa(y, fa);
        if (x == y)
            return ;

        if (fa[x] < fa[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        fa[x] += fa[y];
        fa[y] = x;
    }

    public boolean isconnect(int x, int y, int[] fa) {
        x = findFa(x, fa);
        y = findFa(y, fa);
        return x == y;
    }

    public int findFa(int x, int[] fa) {
        return fa[x] < 0 ? x : (fa[x] = findFa(fa[x], fa));
    }
}
