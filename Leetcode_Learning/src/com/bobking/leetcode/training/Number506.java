package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-05-24 6:41
 */
public class Number506 {

    String[] ss = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};

    public String[] findRelativeRanks(int[] score) {

        int n = score.length;
        String[] ans = new String[n];
        int[] clone = score.clone();

        Arrays.sort(clone);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = n - 1; i >= 0; i--)
            map.put(clone[i], n - 1 - i);
        for (int i = 0; i < n; i++) {
            int rank = map.get(score[i]);
            ans[i] = rank < 3 ? ss[rank] : String.valueOf(rank + 1);
        }

        return ans;
    }
}
