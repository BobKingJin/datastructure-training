package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-02-24 21:47
 */
public class Interview04_01 {

    // 参考：https://leetcode.cn/problems/route-between-nodes-lcci/solution/javalin-jie-biao-yan-du-bian-li-by-wonderzlf/
    public boolean findWhetherExistsPath1(int n, int[][] graph, int start, int target) {

        // 将矩阵转为邻接表
        List<Integer>[] adj = new ArrayList[n];
        for (int[] edge : graph) {
            int from = edge[0];
            int to = edge[1];
            if (adj[from] == null)
                adj[from] = new ArrayList<Integer>();
            adj[from].add(to);
        }
        // 广度遍历
        return hasPath(n, adj, start, target);
    }

    private boolean hasPath(int n, List<Integer>[] adj, int start, int target){

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.offer(start);
        // 维护一个数组：对已经入列的节点，不再重复处理
        boolean[] visited = new boolean[n];
        visited[start] = true;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int node = queue.poll();
                List<Integer> nextList = adj[node];

                if (nextList == null)
                    continue;

                for (Integer next : nextList) {
                    if (next == target)
                        return true;

                    if (visited[next])
                        continue;

                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return false;
    }

    private boolean[] visited = null;

    // 参考：https://leetcode.cn/problems/route-between-nodes-lcci/solution/huan-bu-dong-lai-chui-wo-xi-lie-jing-jia-opwd/
    public boolean findWhetherExistsPath2(int n, int[][] graph, int start, int target) {
        this.visited = new boolean[graph.length];
        return helper(graph, start, target);
    }

    private boolean helper(int[][] graph, int start, int target) {
        for (int i = 0; i < graph.length; ++i) {
            if (!visited[i]) {
                if (graph[i][0] == start && graph[i][1] == target)
                    return true;
                visited[i] = true;
                if (graph[i][1] == target && helper(graph, start, graph[i][0]))
                    return true;

                visited[i] = false;
            }
        }
        return false;
    }
}
