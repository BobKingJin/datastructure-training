package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-06-19 11:25
 */
public class Number243 {

    // 参考：程序猿代码指南P279
    public int minDistance(String[] strings, String str1, String str2) {

        if (str1 == null || str2 == null)
            return -1;

        if (str1.equals(str2))
            return 0;

        int last1 = -1;
        int last2 = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < strings.length; i++) {

            if (str1.equals(strings[i])) {
                min = Math.min(min, last2 == -1 ? min : i - last2);
                last1 = i;
            }
            if (str2.equals(strings[i])) {
                min = Math.min(min, last1 == -1 ? min : i - last1);
                last2 = i;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

}
