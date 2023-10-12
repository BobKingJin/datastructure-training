package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-11 7:13
 */
public class Number495 {

    public int findPoisonedDuration(int[] timeSeries, int duration) {

        int ans = 0;
        int last = -1;

        for (int s : timeSeries) {
            int e = s + duration - 1;
            ans += last < s ? duration : e - last;
            last = e;
        }
        return ans;
    }
}
