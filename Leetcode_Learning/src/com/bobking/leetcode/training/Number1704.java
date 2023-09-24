package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-11-11 23:42
 */
public class Number1704 {

    public boolean halvesAreAlike(String s) {

        Set<Character> set = new HashSet<Character>();
        for (char c : "aeiouAEIOU".toCharArray())
            set.add(c);

        int n = s.length();
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (!set.contains(s.charAt(i)))
                continue;
            cnt += i < n / 2 ? 1 : -1;
        }

        return cnt == 0;
    }
}
