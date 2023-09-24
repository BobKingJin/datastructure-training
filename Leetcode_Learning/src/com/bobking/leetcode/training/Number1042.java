package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Number1042 {

    // 参考：https://leetcode.cn/problems/flower-planting-with-no-adjacent/solution/clin-jie-biao-fa-ran-se-wen-ti-dian-jin-lai-kan-ke/
    public int[] gardenNoAdj(int n, int[][] paths) {

        // 首先，使用邻接矩阵的话，会堆栈溢出，遂改为邻接表法
        // 1、根据 paths 建立邻接表
        // 2、默认所有的花园先不染色，即染 0
        // 3、从第一个花园开始走，把与它邻接的花园的颜色从 color{1, 2, 3, 4} 这个颜色集中删除
        // 4、删完了所有与它相邻的颜色，就可以把集合中剩下的颜色随机选一个给它了，为了简单，将集合中的第一个颜色赋给当前花园

        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<Integer>());

        for (int i = 0; i < paths.length; i++) {
            graph.get(paths[i][0] - 1).add(paths[i][1] - 1);
            graph.get(paths[i][1] - 1).add(paths[i][0] - 1);
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {

            Set<Integer> color = new HashSet<Integer>() {{
                add(1);
                add(2);
                add(3);
                add(4);
            }};

            for (int j = 0; j < graph.get(i).size(); j++)
                // 删除已经使用过的颜色
                color.remove(ans[graph.get(i).get(j)]);

            ans[i] = color.iterator().next();
        }

        return ans;
    }
}
