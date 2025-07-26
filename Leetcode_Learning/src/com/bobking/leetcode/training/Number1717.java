package com.bobking.leetcode.training;

/**
 * @Date: 2025/7/26 16:07
 * @Author: BobKing
 * @Description:
 */
public class Number1717 {

    // 参考: https://leetcode.cn/problems/maximum-score-from-removing-substrings/solutions/3731003/python3javacgotypescript-yi-ti-yi-jie-ta-623j/?envType=daily-question&envId=2025-07-23
    public int maximumGain(String s, int x, int y) {
        char a = 'a';
        char b = 'b';
        if (x < y) {
            int t = x;
            x = y;
            y = t;
            char c = a;
            a = b;
            b = c;
        }
        int ans = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == a) {
                cnt1++;
            } else if (c == b) {
                if (cnt1 > 0) {
                    ans += x;
                    cnt1--;
                } else {
                    cnt2++;
                }
            } else {
                ans += Math.min(cnt1, cnt2) * y;
                cnt1 = 0;
                cnt2 = 0;
            }
        }
        ans += Math.min(cnt1, cnt2) * y;
        return ans;
    }

}
