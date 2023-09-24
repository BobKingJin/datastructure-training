package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-26 23:07
 */
public class Number14 {

    // 参考：https://leetcode-cn.com/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode-solution/
    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0)
            return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0)
                break;
        }

        return prefix;
    }

    private String longestCommonPrefix(String str1, String str2) {

        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index))
            index++;

        return str1.substring(0, index);
    }
}
