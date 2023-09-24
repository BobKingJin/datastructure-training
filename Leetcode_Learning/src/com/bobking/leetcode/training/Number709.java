package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-25 22:19
 */
public class Number709 {

    // 参考：https://leetcode.cn/problems/to-lower-case/solution/zhuan-huan-cheng-xiao-xie-zi-mu-by-leetc-5e29/
    public String toLowerCase(String s) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch >= 65 && ch <= 90)
                ch |= 32;
            sb.append(ch);
        }
        return sb.toString();
    }
}
