package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-06-12 11:11
 */
public class Number397 {

    Map<Long, Integer> map = new HashMap<Long, Integer>();

    // 参考：https://leetcode.cn/problems/integer-replacement/solution/gong-shui-san-xie-yi-ti-san-jie-dfsbfs-t-373h/
    public int integerReplacement1(int n) {
        return dfs(n * 1L);
    }

    private int dfs(long n) {

        if (n == 1)
            return 0;

        if (map.containsKey(n))
            return map.get(n);

        int ans = n % 2 == 0 ? dfs(n / 2) : Math.min(dfs(n + 1), dfs(n - 1));
        // 注意这个位置是 ++ans
        map.put(n, ++ans);
        return ans;
    }

    // 参考：https://leetcode.cn/problems/integer-replacement/solution/gong-shui-san-xie-yi-ti-san-jie-dfsbfs-t-373h/
    public int integerReplacement2(int n) {

        if (n == 1)
            return 0;

        Map<Long, Integer> map = new HashMap<Long, Integer>();
        Deque<Long> d = new ArrayDeque<Long>();
        d.addLast(n * 1L);
        map.put(n * 1L, 0);

        while (!d.isEmpty()) {

            long t = d.pollFirst();
            int step = map.get(t);
            long[] ns = t % 2 == 0 ? new long[]{t / 2} : new long[]{t + 1, t - 1};

            for (long x : ns) {

                if (x == 1)
                    return step + 1;

                if (!map.containsKey(x)) {
                    map.put(x, step + 1);
                    d.addLast(x);
                }
            }
        }

        return -1;
    }

}
