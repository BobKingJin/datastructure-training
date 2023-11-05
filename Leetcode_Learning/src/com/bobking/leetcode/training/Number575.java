package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-11-03 8:24
 */
public class Number575 {

    public int distributeCandies(int[] candyType) {

        Set<Integer> set = new HashSet<Integer>();
        for (int i : candyType)
            set.add(i);
        return Math.min(candyType.length / 2, set.size());
    }
}
