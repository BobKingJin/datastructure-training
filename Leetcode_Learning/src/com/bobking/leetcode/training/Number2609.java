package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-08 7:40
 */
public class Number2609 {

    public int findTheLongestBalancedSubstring(String s) {

        char[] charArray = s.toCharArray();

        int ans = 0;
        // 记录上一段连续相同字符个数 pre，以及当前连续相同字符个数 cur
        int pre = 0;
        int cur = 0;
        int n = charArray.length;

        for (int i = 0; i < n; i++) {
            cur++;
            // i 是连续相同段的末尾
            if (i == charArray.length - 1 || charArray[i] != charArray[i + 1]) {
                if (charArray[i] == '1')
                    ans = Math.max(ans, Math.min(pre, cur) * 2);
                pre = cur;
                cur = 0;
            }
        }
        return ans;
    }
}
