package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-02 10:30
 */
public class Number1513 {

    final int MODULO = 1000000007;

    public int numSub(String s) {

        long total = 0;
        int length = s.length();
        long consecutive = 0;

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                total += consecutive * (consecutive + 1) / 2;
                total %= MODULO;
                consecutive = 0;
            } else {
                consecutive++;
            }
        }
        total += consecutive * (consecutive + 1) / 2;
        total %= MODULO;
        return (int) total;
    }
}
