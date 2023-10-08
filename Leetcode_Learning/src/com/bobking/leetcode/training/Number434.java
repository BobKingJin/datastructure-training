package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-07 9:01
 */
public class Number434 {

    public int countSegments(String s) {

        int n = s.length();
        int ans = 0;

        for (int i = 0; i < n; ) {
            // 排除首部空格
            if (s.charAt(i) == ' ' && i++ >= 0)
                continue;
            while (i < n && s.charAt(i) != ' ')
                i++;
            ans++;
        }
        return ans;
    }
}
