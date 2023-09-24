package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-17 0:24
 */
public class Number1221 {

    // 参考：https://leetcode.cn/problems/split-a-string-in-balanced-strings/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-wumnk/
    public int balancedStringSplit(String s) {

        char[] cs = s.toCharArray();
        int n = cs.length;
        int ans = 0;

        for (int i = 0; i < n; ) {
            int j = i + 1;
            int score = cs[i] == 'L' ? 1 : -1;
            while (j < n && score != 0)
                score += cs[j++] == 'L' ? 1 : -1;
            i = j;
            ans++;
        }

        return ans;
    }
}
