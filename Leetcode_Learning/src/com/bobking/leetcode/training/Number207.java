package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Number207 {

    List<List<Integer>> list;
    int[] visited;
    boolean res;

    // 参考：https://leetcode-cn.com/problems/course-schedule/solution/ke-cheng-biao-by-leetcode-solution/
    public boolean canFinish1(int numCourses, int[][] prerequisites) {

        if (numCourses < 1)
            return false;

        list = new ArrayList<List<Integer>>();
        // 每个节点三种状态：0：未搜索  1：搜索中  2：已完成
        visited = new int[numCourses];
        res = true;

        for (int i = 0; i < numCourses; i++)
            list.add(new ArrayList<>());

        for (int[] i : prerequisites)
            list.get(i[0]).add(i[1]);

        for (int i = 0; i < numCourses && res; i++) {
            if (visited[i] == 0)
                dfs(i);
        }

        return res;
    }

    private void dfs(int i) {

        // 搜索中
        visited[i] = 1;
        // 遍历每一个相邻的节点
        for (int index : list.get(i)) {
            if (visited[index] == 0) {
                dfs(index);
                if (!res)
                    return;
                // 成环
            } else if (visited[index] == 1) {
                res = false;
                return;
            }
        }
        // 已完成
        visited[i] = 2;
    }

    // 代码参考：https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
    // 思路参考：https://leetcode-cn.com/problems/course-schedule/solution/ke-cheng-biao-by-leetcode-solution/
    public boolean canFinish2(int numCourses, int[][] prerequisites) {

        if (numCourses < 1)
            return false;

        // 入度表 indegrees：每个节点的入度
        int[] indegrees = new int[numCourses];
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < numCourses; i++)
            list.add(new ArrayList<Integer>());

        for (int[] i : prerequisites) {
            list.get(i[1]).add(i[0]);
            indegrees[i[0]]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0)
                queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int pre = queue.poll();
            for (int i : list.get(pre)) {
                indegrees[i]--;
                if (indegrees[i] == 0)
                    queue.offer(i);
            }
            numCourses--;
        }

        return numCourses == 0;
    }
}
