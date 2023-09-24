package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-09 21:38
 */
public class Number2437 {

    public int countTime(String time) {
        return count(time.substring(0, 2), 24) * count(time.substring(3), 60);
    }

    private int count(String time, int period) {

        char[] t = time.toCharArray();
        int ans = 0;

        for (int i = 0; i < period; i++) {
            if ((t[0] == '?' || i / 10 == t[0] - '0') &&
                    (t[1] == '?' || i % 10 == t[1] - '0'))
                ans++;
        }
        return ans;
    }
}
