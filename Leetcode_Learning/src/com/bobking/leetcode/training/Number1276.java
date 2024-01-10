package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/12/25 7:54
 * @Author: BobKing
 * @Description:
 */
public class Number1276 {

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {

        List<Integer> res = new ArrayList<Integer>();

        if (0 == (tomatoSlices - 2 * cheeseSlices) % 2) {
            int j = (tomatoSlices - 2 * cheeseSlices) / 2;
            int s = cheeseSlices- j;
            if (j >= 0 && s >= 0) {
                res.add(j);
                res.add(s);
            }
        }
        return res;
    }
}
