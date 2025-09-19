package com.bobking.leetcode.training;

public class Number647 {

    // 参考：https://leetcode-cn.com/problems/palindromic-substrings/solution/liang-dao-hui-wen-zi-chuan-de-jie-fa-xiang-jie-zho/
    public int countSubstrings1(String s) {

        if (s == null || s.length() == 0 || "".equals(s)) {
            return 0;
        }

        // dp[i][j] 表示字符串 s 在 [i, j] 区间的子串是否是一个回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/palindromic-substrings/solution/liang-dao-hui-wen-zi-chuan-de-jie-fa-xiang-jie-zho/
    public int countSubstrings2(String s) {

        if (s == null || s.length() == 0 || "".equals(s)) {
            return 0;
        }

        // 中心扩展法
        int res = 0;
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            // left 和 right 指针和中心点的关系是怎么样的?
            // left: 有一个很明显的 2 倍关系的存在
            // right: 可能和 left 指向同一个(偶数时),也可能往后移动一个(奇数)
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                res++;
                left--;
                right++;
            }
        }

        return res;
    }

}
