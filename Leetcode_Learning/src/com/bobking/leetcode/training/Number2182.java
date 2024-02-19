package com.bobking.leetcode.training;

/**
 * @Date: 2024/2/19 23:24
 * @Author: BobKing
 * @Description:
 */
public class Number2182 {

    private static final int N = 26;

    // 参考: https://leetcode.cn/problems/construct-string-with-repeat-limit/solutions/1300982/gou-zao-xian-zhi-zhong-fu-de-zi-fu-chuan-v02s/?envType=daily-question&envId=2024-02-19
    public String repeatLimitedString(String s, int repeatLimit) {

        int[] count = new int[N];
        for (int i = 0; i < s.length(); i++)
            count[s.charAt(i) - 'a']++;

        StringBuilder res = new StringBuilder();
        int m = 0;
        for (int i = N - 1, j = N - 2; i >= 0 && j >= 0; ) {
            // 当前字符已经填完，填入后面的字符，重置 m
            if (count[i] == 0) {
                m = 0;
                i--;
            } else if (m < repeatLimit) { // 当前字符未超过限制
                count[i]--;
                res.append((char) ('a' + i));
                m++;
            } else if (j >= i || count[j] == 0) { // 当前字符已经超过限制，查找可填入的其他字符
                j--;
            } else { // 当前字符已经超过限制，填入其他字符，并且重置 m
                count[j]--;
                res.append((char) ('a' + j));
                m = 0;
            }
        }
        return res.toString();
    }
}
