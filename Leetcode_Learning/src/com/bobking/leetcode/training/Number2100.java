package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-03-06 20:07
 */
public class Number2100 {

    // 参考：https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank/solution/gong-shui-san-xie-qian-zhui-he-yun-yong-gf604/
    public List<Integer> goodDaysToRobBank(int[] security, int time) {

        List<Integer> res = new ArrayList<Integer>();

        if(security == null || security.length == 0 || time < 0 || security.length < time)
            return res;

        // g 数组，g[i] 代表当前时间 security[i] 与前一时间 security[i - 1] 的大小关系
        // 当 security[i] > security[i - 1] 时有 g[i] = 1
        // 当 security[i] < security[i - 1] 时有 g[i] = −1
        // 否则 g[i] = 0
        // 特别的有 g[0] = 0 的边界情况
        // 对 g 应用「前缀和」思想：使用 a 数组记录前缀 1 的数量，使用 b 数组记录前缀 -1 的数量
        // 如果 i 为「适合打劫银行的日子」，那么满足 time <= i < n - timetime
        // 且满足 (i - time, i] 范围前缀 1 数量为 0，(i, i + time] 范围前缀 −1 数量为 0
        int n = security.length;
        int[] g = new int[n];

        for (int i = 1; i < n; i++) {
            if (security[i] == security[i - 1])
                continue;
            g[i] = security[i] > security[i - 1] ? 1 : -1;
        }

        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++)
            a[i] = a[i - 1] + (g[i - 1] == 1 ? 1 : 0);
        for (int i = 1; i <= n; i++)
            b[i] = b[i - 1] + (g[i - 1] == -1 ? 1 : 0);

        for (int i = time; i < n - time; i++) {
            int c1 = a[i + 1] - a[i + 1 - time];
            int c2 = b[i + 1 + time] - b[i + 1];
            if (c1 == 0 && c2 == 0)
                res.add(i);
        }

        return res;
    }
}
