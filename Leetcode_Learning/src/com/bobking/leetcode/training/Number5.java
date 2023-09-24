package com.bobking.leetcode.training;

public class Number5 {

    public String longestPalindrome1(String s) {

        if (s == null || s.equals(""))
            return s;

        String result = "";
        char[] ch = s.toCharArray();
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLength = Integer.MIN_VALUE;
        // dp[i][j] 表示 i - j 范围内最长字串长度
        for (int j = 0; j < ch.length; j++) {
            // 遍历 0 - j 范围内每个字串
            for (int i = 0; i <= j; i++) {
                if (((j - i <= 1) || (dp[i + 1][j - 1] == true)) && ch[i] == ch[j]) {
                    dp[i][j] = true;
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }

        return result;
    }

    // 参考：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
    // 中心扩散法
    public String longestPalindrome2(String s) {

        int len = s.length();
        if (len < 2)
            return s;

        int maxLen = 0;
        // 数组第一位记录起始位置，第二位记录长度
        int[] res = new int[2];
        for (int i = 0; i < s.length() - 1; i++) {
            // 如果传入重合的下标，进行中心扩散，此时得到的回文子串的长度是奇数
            int[] odd = centerSpread(s, i, i);
            // 如果传入相邻的下标，进行中心扩散，此时得到的回文子串的长度是偶数
            int[] even = centerSpread(s, i, i + 1);
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen) {
                res = max;
                maxLen = max[1];
            }
        }

        return s.substring(res[0], res[0] + res[1]);
    }

    private int[] centerSpread(String s, int left, int right) {

        int len = s.length();

        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }

        return new int[]{left + 1, right - left - 1};
    }

    // Manacher算法
    // 参考：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
    public String longestPalindrome3(String s) {
        return s;
    }


}
