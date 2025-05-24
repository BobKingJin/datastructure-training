package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2025/5/24 11:18
 * @Author: BobKing
 * @Description:
 */
public class Number3159 {

    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> pos = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                pos.add(i);
            }
        }
        for (int i = 0; i < queries.length; i++) {
            queries[i] = queries[i] > pos.size() ? -1 : pos.get(queries[i] - 1);
        }
        return queries;
    }

}
