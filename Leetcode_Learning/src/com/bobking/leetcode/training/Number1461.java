package com.bobking.leetcode.training;

import java.util.HashSet;

/**
 * @author BobKing
 * @create 2023-05-27 22:41
 */
public class Number1461 {

    public boolean hasAllCodes(String s, int k) {

        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i <= s.length() - k; i++)
            set.add(s.substring(i, i + k));

        return set.size() == (int) Math.pow(2, k);
    }
}
