package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-08 7:07
 */
public class Number459 {

    // 参考: https://leetcode.cn/problems/repeated-substring-pattern/solutions/114572/jian-dan-ming-liao-guan-yu-javaliang-xing-dai-ma-s/
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}
