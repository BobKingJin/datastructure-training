package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2024/11/13 11:19
 * @Author: BobKing
 * @Description:
 */
public class LCR186 {

    public boolean checkDynasty1(int[] places) {

        Set<Integer> repeat = new HashSet<Integer>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int place : places) {
            if (place == 0)
                continue;
            max = Math.max(max, place);
            min = Math.min(min, place);
            if (repeat.contains(place))
                return false;
            repeat.add(place);
        }
        return max - min < 5;
    }

    public boolean checkDynasty2(int[] places) {

        int unknown = 0;
        Arrays.sort(places);

        for (int i = 0; i < 4; i++) {
            if (places[i] == 0) {
                unknown++;
            } else if (places[i] == places[i + 1]) {
                return false;
            }
        }
        return places[4] - places[unknown] < 5;
    }
}
