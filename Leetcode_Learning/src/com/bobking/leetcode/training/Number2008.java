package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2023/12/18 7:52
 * @Author: BobKing
 * @Description:
 */
public class Number2008 {

    // 参考: https://leetcode.cn/problems/maximum-earnings-from-taxi/solutions/2558504/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-k15a/?envType=daily-question&envId=2023-12-17
    public long maxTaxiEarnings1(int n, int[][] rides) {

        List<int[]>[] groups = new ArrayList[n + 1];

        for (int[] r : rides) {
            int start = r[0];
            int end = r[1];
            int tip = r[2];
            if (groups[end] == null)
                groups[end] = new ArrayList<int[]>();

            groups[end].add(new int[]{start, end - start + tip});
        }
        return dfs1(n, groups);
    }

    // dfs(i) 表示从 1 开到 i 最多可以赚多少钱
    private long dfs1(int i, List<int[]>[] groups) {

        if (i == 1)
            return 0;

        long res = dfs1(i - 1, groups);

        if (groups[i] != null) {
            for (int[] p : groups[i])
                res = Math.max(res, dfs1(p[0], groups) + p[1]);
        }
        return res;
    }

    // 参考: https://leetcode.cn/problems/maximum-earnings-from-taxi/solutions/2558504/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-k15a/?envType=daily-question&envId=2023-12-17
    public long maxTaxiEarnings2(int n, int[][] rides) {

        List<int[]>[] groups = new ArrayList[n + 1];

        for (int[] r : rides) {
            int start = r[0];
            int end = r[1];
            int tip = r[2];
            if (groups[end] == null)
                groups[end] = new ArrayList<int[]>();

            groups[end].add(new int[]{start, end - start + tip});
        }

        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);
        return dfs2(n, memo, groups);
    }

    private long dfs2(int i, long[] memo, List<int[]>[] groups) {

        if (i == 1)
            return 0;

        if (memo[i] != -1)
            return memo[i];

        long res = dfs2(i - 1, memo, groups);
        if (groups[i] != null) {
            for (int[] p : groups[i])
                res = Math.max(res, dfs2(p[0], memo, groups) + p[1]);
        }
        return memo[i] = res;
    }

    // 参考: https://leetcode.cn/problems/maximum-earnings-from-taxi/solutions/2558504/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-k15a/?envType=daily-question&envId=2023-12-17
    public long maxTaxiEarnings3(int n, int[][] rides) {

        List<int[]>[] groups = new ArrayList[n + 1];

        for (int[] r : rides) {
            int start = r[0];
            int end = r[1];
            int tip = r[2];
            if (groups[end] == null)
                groups[end] = new ArrayList<int[]>();

            groups[end].add(new int[]{start, end - start + tip});
        }

        long[] f = new long[n + 1];

        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1];
            if (groups[i] != null) {
                for (int[] p : groups[i])
                    f[i] = Math.max(f[i], f[p[0]] + p[1]);
            }
        }
        return f[n];
    }

}
