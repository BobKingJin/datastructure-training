package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-05 14:49
 */
public class Number2042 {

    public boolean areNumbersAscending(String s) {

        int pre = 0;

        for (String t : s.split(" ")) {
            if (t.charAt(0) <= '9') {
                int cur = Integer.parseInt(t);
                if (pre >= cur)
                    return false;
                pre = cur;
            }
        }
        return true;
    }

}
