package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-04-08 8:44
 */
public class Number1125 {

    private long all;
    private int[] mask;
    private long[][] memo;

    // 参考：https://leetcode.cn/problems/smallest-sufficient-team/solution/zhuang-ya-0-1-bei-bao-cha-biao-fa-vs-shu-qode/
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

        HashMap<String, Integer> sid = new HashMap<String, Integer>();
        int m = req_skills.length;
        for (int i = 0; i < m; ++i)
            // 字符串映射到下标
            sid.put(req_skills[i], i);

        int n = people.size();
        mask = new int[n];
        for (int i = 0; i < n; ++i)
            // 把 people[i] 压缩成一个二进制数 mask[i]
            for (String s : people.get(i))
                mask[i] |= 1 << sid.get(s);

        int u = 1 << m;
        memo = new long[n][u];
        for (int i = 0; i < n; i++)
            Arrays.fill(memo[i], -1);
        all = (1L << n) - 1;
        long res = dfs(n - 1, u - 1);

        int[] ans = new int[Long.bitCount(res)];
        for (int i = 0, j = 0; i < n; ++i) {
            if (((res >> i) & 1) > 0)
                ans[j++] = i;
        }

        return ans;
    }

    // 定义 dfs(i, j) 表示从前 i 个集合中选择一些集合，并集等于 j，至少需要选择的集合个数
    private long dfs(int i, int j) {
        // 背包已装满
        if (j == 0)
            return 0;
        // 没法装满背包，返回全集，这样下面比较集合大小会取更小的
        if (i < 0)
            return all;
        if (memo[i][j] != -1)
            return memo[i][j];
        // 不选 mask[i]
        long res1 = dfs(i - 1, j);
        // 选 mask[i]
        long res2 = dfs(i - 1, j & ~mask[i]) | (1L << i);
        return memo[i][j] = Long.bitCount(res1) < Long.bitCount(res2) ? res1 : res2;
    }
}
