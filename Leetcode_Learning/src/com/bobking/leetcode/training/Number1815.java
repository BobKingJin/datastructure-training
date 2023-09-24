package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-02-15 11:22
 */
public class Number1815 {

    private int m;
    private int[] val;
    private final Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

    // 参考：https://leetcode.cn/problems/maximum-number-of-groups-getting-fresh-donuts/solution/by-endlesscheng-r5ve/
    public int maxHappyGroups(int batchSize, int[] groups) {
        m = batchSize;
        int ans = 0;
        int[] cnt = new int[m];
        for (int x : groups) {
            x %= m;
            if (x == 0) {
                // 直接排在最前面
                ++ans;
            } else if (cnt[m - x] > 0) {
                // 配对
                --cnt[m - x];
                ++ans;
            } else {
                ++cnt[x];
            }
        }

        int mask = 0;
        int n = 0;
        for (int c : cnt) {
            if (c > 0)
                ++n;
        }
        val = new int[n];
        for (int x = 1; x < m; ++x)
            if (cnt[x] > 0) {
                val[--n] = x; // val 越靠后的，在 mask 中的比特位越高
                mask = mask << 5 | cnt[x];
            }

        return ans + dfs(mask);
    }

    private int dfs(int mask) {
        if (cache.containsKey(mask))
            return cache.get(mask);
        int res = 0;
        int left = mask >> 20;
        int msk = mask & ((1 << 20) - 1);
        for (int i = 0, j = 0; i < val.length; ++i, j += 5) {
            // 枚举顾客
            if ((msk >> j & 31) > 0) // cnt[val[i]] > 0
                res = Math.max(res, (left == 0 ? 1 : 0) + dfs((left + val[i]) % m << 20 | msk - (1 << j)));
        }
        cache.put(mask, res);
        return res;
    }

}
