package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number119 {

    // 参考：https://leetcode-cn.com/problems/pascals-triangle-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--28/
    public List<Integer> getRow(int rowIndex) {

        if(rowIndex < 0)
            return new ArrayList<Integer>();

        List<Integer> pre = new ArrayList<Integer>();
        List<Integer> cur = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }

            pre = cur;
        }

        return cur;
    }
}
