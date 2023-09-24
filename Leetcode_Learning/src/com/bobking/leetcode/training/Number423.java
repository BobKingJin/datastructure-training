package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-23 21:14
 */
public class Number423 {

    String[] ss = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    int[] priority = new int[]{0, 8, 6, 3, 2, 7, 5, 9, 4, 1};

    // 参考：https://leetcode.cn/problems/reconstruct-original-digits-from-english/solution/gong-shui-san-xie-nao-jin-ji-zhuan-wan-m-vg7a/
    public String originalDigits(String s) {

        int n = s.length();
        int[] cnts = new int[26];
        for (int i = 0; i < n; i++)
            cnts[s.charAt(i) - 'a']++;

        StringBuilder sb = new StringBuilder();

        for (int i : priority) {

            // k 记录一个次数出现几次，例如 zero 出现 2两次，那么 k = 2
            int k = Integer.MAX_VALUE;

            for (char c : ss[i].toCharArray())
                k = Math.min(k, cnts[c - 'a']);

            for (char c : ss[i].toCharArray())
                cnts[c - 'a'] -= k;

            while (k-- > 0)
                sb.append(i);
        }

        char[] cs = sb.toString().toCharArray();
        Arrays.sort(cs);
        return String.valueOf(cs);
    }
}
