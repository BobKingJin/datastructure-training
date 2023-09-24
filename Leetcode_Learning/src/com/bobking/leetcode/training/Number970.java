package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-03-30 22:40
 */
public class Number970 {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {

        Set<Integer> res = new HashSet<Integer>();
        boolean over = false;
        int up_i = x == 1 ? 0 : (int) (Math.log(bound) / Math.log(x));
        int up_j = y == 1 ? 0 : (int) (Math.log(bound) / Math.log(y));
        for (int i = 0; i <= up_i; i++) {
            for (int j = 0; j <= up_j; j++) {
                int temp = (int) (Math.pow(x, i) + Math.pow(y, j));
                if (temp <= bound) {
                    res.add(temp);
                } else if (j > 0) {
                    break;
                } else {
                    over = true;
                    break;
                }
            }
            if (over)
                break;
        }
        return new ArrayList<Integer>(res);
    }
}
