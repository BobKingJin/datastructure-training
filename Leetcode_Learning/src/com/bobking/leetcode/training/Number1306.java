package com.bobking.leetcode.training;

import java.util.LinkedList;

/**
 * @author BobKing
 * @create 2021-08-13 21:58
 */
public class Number1306 {

    // 参考：https://leetcode-cn.com/problems/jump-game-iii/solution/tiao-yue-you-xi-iii-by-leetcode-solution/
    // bfs
    public boolean canReach1(int[] arr, int start) {

        if (arr == null || arr.length == 0 || start < 0 || start >= arr.length)
            return false;

        if (arr[start] == 0)
            return true;

        boolean[] visited = new boolean[arr.length];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.addLast(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer u = queue.peekFirst();
            queue.removeFirst();
            if (u + arr[u] < arr.length && !visited[u + arr[u]]) {
                if (arr[u + arr[u]] == 0)
                    return true;

                queue.addLast(u + arr[u]);
                visited[u + arr[u]] = true;
            }
            if (u - arr[u] >= 0 && !visited[u - arr[u]]) {
                if (arr[u - arr[u]] == 0)
                    return true;

                queue.push(u - arr[u]);
                visited[u - arr[u]] = true;
            }
        }

        return false;
    }

    public boolean canReach2(int[] arr, int start) {

        if (arr == null || arr.length == 0 || start < 0 || start >= arr.length)
            return false;

        if (arr[start] == 0)
            return true;

        boolean[] visit = new boolean[arr.length];
        return dfs(arr, start, visit);
    }

    private boolean dfs(int[] arr, int start, boolean[] visit) {

        if (start >= arr.length || start < 0)
            return false;

        if (arr[start] == 0)
            return true;

        if (visit[start]) {
            return false;
        } else {
            visit[start] = true;
        }
        return dfs(arr, start + arr[start], visit) || dfs(arr, start - arr[start], visit);
    }
}
