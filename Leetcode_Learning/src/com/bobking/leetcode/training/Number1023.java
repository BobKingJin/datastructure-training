package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-04-16 8:33
 */
public class Number1023 {

    // 参考：https://leetcode.cn/problems/camelcase-matching/solution/python3javacgotypescript-yi-ti-yi-jie-sh-vr5x/
    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> ans = new ArrayList<Boolean>();
        for (String q : queries)
            ans.add(check(q, pattern));

        return ans;
    }

    private boolean check(String s, String t) {

        // 使用双指针 i 和 j，分别指向两个字符串的首字符，然后遍历两个字符串。如果指针
        // i 和 j 指向的 字符不同，并且 s[i] 为小写字母，则指针 i 循环向后移动一位
        // 如果指针 i 已经到达字符串 s 的末尾，或者指针 i 和 j 指向的字符不同，则返回 false
        // 否则，指针 i 和 j 同时向后移动一位。当指针 j 到达字符串 t 的末尾时，需要判断字符串
        // s 中剩余的字符是否都为小写字母，若是则返回 true，否则返回 false

        int m = s.length();
        int n = t.length();
        int i = 0;
        int j = 0;
        for (; j < n; ++i, ++j) {
            while (i < m && s.charAt(i) != t.charAt(j) && Character.isLowerCase(s.charAt(i)))
                ++i;

            if (i == m || s.charAt(i) != t.charAt(j))
                return false;
        }
        while (i < m && Character.isLowerCase(s.charAt(i)))
            ++i;

        return i == m;
    }
}
