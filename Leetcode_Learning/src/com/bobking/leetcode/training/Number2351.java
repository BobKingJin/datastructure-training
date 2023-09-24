package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-05 14:33
 */
public class Number2351 {

    // 参考：https://leetcode.cn/problems/first-letter-to-appear-twice/solution/di-yi-ge-chu-xian-liang-ci-de-zi-mu-by-l-oqu1/
    public char repeatedCharacter(String s) {

        int seen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int x = ch - 'a';
            if ((seen & (1 << x)) != 0)
                return ch;
            seen |= (1 << x);
        }

        return ' ';
    }
}
