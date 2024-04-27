package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number118 {

    // 参考：https://leetcode-cn.com/problems/pascals-triangle/solution/yang-hui-san-jiao-by-leetcode-solution-lew9/
    public List<List<Integer>> generate(int numRows) {

        if(numRows < 0)
            return new ArrayList<List<Integer>>();

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(row);
        }
        return res;
    }
}
