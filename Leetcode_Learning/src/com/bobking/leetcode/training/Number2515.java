package com.bobking.leetcode.training;

/**
 * @Date: 2025/4/6 12:57
 * @Author: BobKing
 * @Description:
 */
public class Number2515 {

    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        for (int i = 0; i <= n / 2; i++) {
            if (target.equals(words[(startIndex - i + n) % n]) || target.equals(
                words[(startIndex + i) % n])) {
                return i;
            }
        }
        return -1;
    }

}
