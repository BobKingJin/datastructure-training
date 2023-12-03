package com.bobking.leetcode.training;

import java.util.*;

/**
 * @Date: 2023/12/3 9:51
 * @Author: BobKing
 * @Description:
 */
public class Number802 {

    // 参考: https://leetcode.cn/problems/find-eventual-safe-states/solutions/916155/zhao-dao-zui-zhong-de-an-quan-zhuang-tai-yzfz/
    public List<Integer> eventualSafeNodes1(int[][] graph) {

        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<Integer>();

        for (int i = 0; i < n; ++i) {
            if (safe(graph, color, i))
                ans.add(i);
        }
        return ans;
    }

    private boolean safe(int[][] graph, int[] color, int x) {

        if (color[x] > 0)
            return color[x] == 2;

        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y))
                return false;
        }
        color[x] = 2;
        return true;
    }

    // 参考: https://leetcode.cn/problems/find-eventual-safe-states/solutions/916155/zhao-dao-zui-zhong-de-an-quan-zhuang-tai-yzfz/
    public List<Integer> eventualSafeNodes2(int[][] graph) {

        int n = graph.length;
        List<List<Integer>> rg = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; ++i)
            rg.add(new ArrayList<Integer>());

        int[] inDeg = new int[n];
        for (int x = 0; x < n; ++x) {
            for (int y : graph[x])
                rg.get(y).add(x);
            inDeg[x] = graph[x].length;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0)
                queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int y = queue.poll();
            for (int x : rg.get(y)) {
                if (--inDeg[x] == 0)
                    queue.offer(x);
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0)
                ans.add(i);
        }
        return ans;
    }
}
