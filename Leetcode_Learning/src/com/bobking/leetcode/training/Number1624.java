package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-17 8:25
 */
public class Number1624 {

    public int maxLengthBetweenEqualCharacters(String s) {

        // 记录每个字符最近一次出现的位置
        int[] start = new int[26];
        Arrays.fill(start, -1);

        int maxlen = -1;

        for (int i = 0; i < s.length(); ++i) {
            int idx = s.charAt(i) - 'a';
            // -1 表示还未出现过
            if (start[idx] == -1) {
                start[idx] = i;
                // 已经出现过了，做差求长度，取最大
            } else {
                maxlen = Math.max(maxlen, i - start[idx] - 1);
            }

        }

        return maxlen;
    }
}
