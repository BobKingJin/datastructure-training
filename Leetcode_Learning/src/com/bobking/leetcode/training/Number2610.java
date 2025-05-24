package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2025/5/24 11:35
 * @Author: BobKing
 * @Description:
 */
public class Number2610 {

    public List<List<Integer>> findMatrix(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int x : nums) {
            // cnt[x]++
            cnt.merge(x, 1, Integer::sum);
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        while (!cnt.isEmpty()) {
            List<Integer> row = new ArrayList<Integer>(cnt.keySet());
            ans.add(row);
            // cnt 中的每个元素的出现次数都减一
            for (Integer x : row) {
                int c = cnt.get(x) - 1;
                if (c == 0) {
                    cnt.remove(x);
                } else {
                    cnt.put(x, c);
                }
            }
        }
        return ans;
    }

}
