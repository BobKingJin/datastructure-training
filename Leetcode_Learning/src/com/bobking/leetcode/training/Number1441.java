package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-09-11 22:30
 */
public class Number1441 {

    public List<String> buildArray(int[] target, int n) {

        List<String> res = new ArrayList<String>();
        int i = 0;

        for (int j = 1; j <= n; j++) {

            if (i == target.length)
                break;

            if (target[i] == j) {
                res.add("Push");
                i++;
            } else {
                res.add("Push");
                res.add("Pop");
            }
        }

        return res;
    }
}
