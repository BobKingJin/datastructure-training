package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-02-23 23:31
 */
public class Number1238 {

    // 参考：https://leetcode.cn/problems/circular-permutation-in-binary-representation/solution/python3javacgotypescript-yi-ti-shuang-ji-zhm7/
    public List<Integer> circularPermutation(int n, int start) {

        int[] g = new int[1 << n];
        int j = 0;
        for (int i = 0; i < 1 << n; ++i) {
            g[i] = i ^ (i >> 1);
            if (g[i] == start)
                j = i;
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = j; i < j + (1 << n); ++i)
            ans.add(g[i % (1 << n)]);

        return ans;
    }

}
