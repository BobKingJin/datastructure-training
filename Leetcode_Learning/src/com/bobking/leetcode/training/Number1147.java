package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-12 7:52
 */
public class Number1147 {

    // 参考：https://leetcode.cn/problems/longest-chunked-palindrome-decomposition/solution/tu-jie-tan-xin-zuo-fa-yi-tu-miao-dong-py-huik/
    public int longestDecomposition(String text) {

        if (text.isEmpty())
            return 0;

        // 枚举前后缀长度
        for (int i = 1, n = text.length(); i <= n / 2; ++i)
            if (text.substring(0, i).equals(text.substring(n - i)))
                return 2 + longestDecomposition(text.substring(i, n - i));
        // 无法分割
        return 1;
    }
}
