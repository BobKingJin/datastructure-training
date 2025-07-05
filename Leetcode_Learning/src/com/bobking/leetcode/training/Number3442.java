package com.bobking.leetcode.training;

/**
 * @Date: 2025/7/5 17:22
 * @Author: BobKing
 * @Description:
 */
public class Number3442 {

    public int maxDifference(String s) {
        int[] cnt = new int[26];
        for (char b : s.toCharArray()) {
            cnt[b - 'a']++;
        }

        int max1 = 0;
        int min0 = Integer.MAX_VALUE;
        for (int c : cnt) {
            if (c % 2 > 0) {
                max1 = Math.max(max1, c);
            } else if (c > 0) {
                min0 = Math.min(min0, c);
            }
        }
        return max1 - min0;
    }

}
