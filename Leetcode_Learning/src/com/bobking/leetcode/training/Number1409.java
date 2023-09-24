package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-04-24 8:35
 */
public class Number1409 {

    // 参考：https://leetcode.cn/problems/queries-on-a-permutation-with-key/solution/cha-xun-dai-jian-de-pai-lie-by-leetcode-solution/
    public int[] processQueries(int[] queries, int m) {

        List<Integer> p = new ArrayList<Integer>();
        for (int i = 1; i <= m; ++i)
            p.add(i);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int query = queries[i];
            int pos = -1;
            for (int j = 0; j < m; ++j) {
                if (p.get(j) == query) {
                    pos = j;
                    break;
                }
            }
            ans[i] = pos;
            p.remove(pos);
            p.add(0, query);
        }
        return ans;
    }
}
