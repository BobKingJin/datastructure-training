package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-28 15:39
 */
public class Number28 {

    // 参考：https://leetcode-cn.com/problems/implement-strstr/solution/shua-chuan-lc-shuang-bai-po-su-jie-fa-km-tb86/
    public int strStr(String haystack, String needle) {

        if(haystack == null || needle == null)
            return -1;

        int n = haystack.length();
        int m = needle.length();
        char[] s = haystack.toCharArray();
        char[] p = needle.toCharArray();

        for (int i = 0; i <= n - m; i++) {
            int a = i;
            int b = 0;
            while (b < m && s[a] == p[b]) {
                a++;
                b++;
            }
            if (b == m)
                return i;
        }
        return -1;
    }
}
