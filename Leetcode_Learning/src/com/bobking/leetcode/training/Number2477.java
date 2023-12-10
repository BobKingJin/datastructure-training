package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2023/12/5 7:47
 * @Author: BobKing
 * @Description:
 */
public class Number2477 {

    private long ans;

    // 参考: https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital/solutions/1981361/kao-lu-mei-tiao-bian-shang-zhi-shao-xu-y-uamv/?envType=daily-question&envId=2023-12-05
    public long minimumFuelCost(int[][] roads, int seats) {

        int n = roads.length + 1;

        List<Integer>[] g = new ArrayList[n];

        Arrays.setAll(g, e -> new ArrayList<>());

        for (int[] e : roads) {
            int x = e[0];
            int y = e[1];
            // 记录每个点的邻居
            g[x].add(y);
            g[y].add(x);
        }

        dfs(0, -1, g, seats);
        return ans;
    }

    private int dfs(int x, int fa, List<Integer>[] g, int seats) {

        int size = 1;

        for (int y : g[x]) {
            // 递归子节点，不能递归父节点
            if (y != fa)
                // 统计子树大小
                size += dfs(y, x, g, seats);
        }

        // x 不是根节点
        if (x > 0)
            // ceil(size/seats)
            ans += (size - 1) / seats + 1;

        return size;
    }
}
