package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Number841 {

    boolean[] vis;
    int num;

    // 参考：https://leetcode.cn/problems/keys-and-rooms/solution/yao-chi-he-fang-jian-by-leetcode-solution/
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        int n = rooms.size();
        num = 0;
        vis = new boolean[n];
        dfs(rooms, 0);
        return num == n;
    }

    private void dfs(List<List<Integer>> rooms, int x) {

        vis[x] = true;
        num++;
        for (int it : rooms.get(x)) {
            if (!vis[it])
                dfs(rooms, it);
        }
    }

    // 参考：https://leetcode.cn/problems/keys-and-rooms/solution/yao-chi-he-fang-jian-by-leetcode-solution/
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {

        int n = rooms.size();
        int num = 0;
        boolean[] vis = new boolean[n];
        Queue<Integer> que = new LinkedList<Integer>();
        vis[0] = true;
        que.offer(0);
        while (!que.isEmpty()) {
            int x = que.poll();
            num++;
            for (int it : rooms.get(x)) {
                if (!vis[it]) {
                    vis[it] = true;
                    que.offer(it);
                }
            }
        }

        return num == n;
    }
}
