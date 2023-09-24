package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-06-10 23:55
 */
public class Number386 {

    List<Integer> ans = new ArrayList<Integer>();

    // 参考：https://leetcode.cn/problems/lexicographical-numbers/solution/by-ac_oier-ktn7/
    // 类似于字典树
    public List<Integer> lexicalOrder(int n) {

        for (int i = 1; i <= 9; i++)
            dfs(i, n);

        return ans;
    }

    private void dfs(int cur, int limit) {

        if (cur > limit)
            return;

        ans.add(cur);
        for (int i = 0; i <= 9; i++)
            // 注意每增加一层都得 *10
            dfs(cur * 10 + i, limit);
    }
}
