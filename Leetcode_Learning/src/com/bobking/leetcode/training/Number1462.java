package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-09-19 7:55
 */
public class Number1462 {

    // 参考：https://leetcode.cn/problems/course-schedule-iv/solutions/2438246/python3javacgotypescript-yi-ti-shuang-ji-0rmo/?envType=daily-question&envId=2023-09-19
    public List<Boolean> checkIfPrerequisite1(int numCourses, int[][] prerequisites, int[][] queries) {

        // f[i][j] 表示节点 i 到节点 j 是否可达
        boolean[][] f = new boolean[numCourses][numCourses];

        for (int[] p : prerequisites)
            f[p[0]][p[1]] = true;

        // 首先枚举中间点 k，接下来枚举起点 i，最后枚举终点 j
        for (int k = 0; k < numCourses; ++k) {
            for (int i = 0; i < numCourses; ++i) {
                for (int j = 0; j < numCourses; ++j)
                    f[i][j] |= f[i][k] && f[k][j];
            }
        }

        List<Boolean> ans = new ArrayList<Boolean>();

        for (int[] q : queries)
            ans.add(f[q[0]][q[1]]);

        return ans;
    }

    // 参考：https://leetcode.cn/problems/course-schedule-iv/solutions/2438246/python3javacgotypescript-yi-ti-shuang-ji-0rmo/?envType=daily-question&envId=2023-09-19
    public List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {

        // f[i][j] 表示节点 i 到节点 j 是否可达
        boolean[][] f = new boolean[numCourses][numCourses];
        // 创建一个邻接表 g，其中 g[i] 表示节点 i 的所有后继节点
        List<Integer>[] g = new List[numCourses];
        // indeg[i] 表示节点 i 的入度
        int[] indeg = new int[numCourses];

        Arrays.setAll(g, i -> new ArrayList<Integer>());

        for (int[] p : prerequisites) {
            g[p[0]].add(p[1]);
            ++indeg[p[1]];
        }

        Deque<Integer> q = new ArrayDeque<Integer>();

        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0)
                q.offer(i);
        }

        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : g[i]) {
                f[i][j] = true;
                for (int h = 0; h < numCourses; ++h)
                    f[h][j] |= f[h][i];
                if (--indeg[j] == 0)
                    q.offer(j);
            }
        }

        List<Boolean> ans = new ArrayList<Boolean>();
        for (int[] qry : queries)
            ans.add(f[qry[0]][qry[1]]);

        return ans;
    }
}
