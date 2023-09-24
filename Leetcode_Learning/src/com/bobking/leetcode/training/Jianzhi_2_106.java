package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2023-05-24 6:55
 */
public class Jianzhi_2_106 {

    private final int UNCOLORED = 0;
    private final int RED = 1;
    private final int GREEN = 2;
    private int[] color;
    private boolean valid;

    // 参考：https://leetcode.cn/problems/vEAB3K/solution/er-fen-tu-by-leetcode-solution-dryu/
    public boolean isBipartite1(int[][] graph) {

        int n = graph.length;
        valid = true;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);

        for (int i = 0; i < n && valid; ++i) {
            if (color[i] == UNCOLORED)
                dfs(i, RED, graph);
        }
        return valid;
    }

    private void dfs(int node, int c, int[][] graph) {

        color[node] = c;
        int cNei = c == RED ? GREEN : RED;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == UNCOLORED) {
                dfs(neighbor, cNei, graph);
                if (!valid)
                    return;
            } else if (color[neighbor] != cNei) {
                valid = false;
                return;
            }
        }
    }

    // 参考：https://leetcode.cn/problems/vEAB3K/solution/er-fen-tu-by-leetcode-solution-dryu/
    public boolean isBipartite2(int[][] graph) {

        int n = graph.length;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);

        for (int i = 0; i < n; ++i) {
            if (color[i] == UNCOLORED) {
                Queue<Integer> queue = new LinkedList<Integer>();
                queue.offer(i);
                color[i] = RED;
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    int cNei = color[node] == RED ? GREEN : RED;
                    for (int neighbor : graph[node]) {
                        if (color[neighbor] == UNCOLORED) {
                            queue.offer(neighbor);
                            color[neighbor] = cNei;
                        } else if (color[neighbor] != cNei) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
