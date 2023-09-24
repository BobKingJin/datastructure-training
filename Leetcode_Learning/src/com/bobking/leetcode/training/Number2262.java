package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-04-25 7:38
 */
public class Number2262 {

    // 参考：https://leetcode.cn/problems/total-appeal-of-a-string/solution/by-endlesscheng-g405/
    public long appealSum(String s) {

        long ans = 0L;
        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0, sumG = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            sumG += i - last[c];
            ans += sumG;
            last[c] = i;
        }

        return ans;
    }
}
