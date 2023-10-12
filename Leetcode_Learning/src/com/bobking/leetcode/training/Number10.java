package com.bobking.leetcode.training;

public class Number10 {

    // 参考：程序猿代码指南P317
    public boolean isMatch1(String s, String p) {

        if (s == null || p == null)
            return false;

        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();

        return recursion(sChar, 0, pChar, 0);
    }

    private boolean recursion(char[] sChar, int sIndex, char[] pChar, int pIndex) {

        // pChar.length - 1 和 sChar.length - 1 都可以取到，所以递归结束条件为  pIndex == pChar.length 和 sIndex == sChar.length
        if (pIndex == pChar.length)
            return sIndex == sChar.length;

        // 如果 pChar[pIndex + 1] != '*'，那么当前字符必须匹配
        if (pIndex == pChar.length - 1 || (pChar[pIndex + 1] != '*')) {
            //                      注意 pChar[pIndex] == '.'情况
            return sIndex != sChar.length && (sChar[sIndex] == pChar[pIndex] || pChar[pIndex] == '.')
                    // 注意 s 和 p 的角标都要向前移动一位
                    && recursion(sChar, sIndex + 1, pChar, pIndex + 1);
        }
        // pChar[pIndex + 1] = '*'
        // 并且当前字符匹配，例如 aaaaaaXXXX   a*YYYY
        // 注意这里是 while循环
        while (sIndex != sChar.length && (sChar[sIndex] == pChar[pIndex] || pChar[pIndex] == '.')) {
            // 只要有一种情况符合即可
            if (recursion(sChar, sIndex, pChar, pIndex + 2))
                return true;
            sIndex++;
        }
        // 当 pChar[pIndex + 1] = '*'，并且当前字符不匹配，例如 aXXXXX  b*YYYYYY
        return recursion(sChar, sIndex, pChar, pIndex + 2);
    }

    // 参考：程序猿代码指南P318
    public boolean isMatch2(String s, String p) {

        if (s == null || p == null)
            return false;

        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        int sLen = sChar.length;
        int pLen = pChar.length;

        boolean[][] dp = initDP(sChar, pChar);

        for (int i = sLen - 1; i >= 0; i--) {
            for (int j = pLen - 2; j >= 0; j--) {

                if (pChar[j + 1] != '*') {
                    dp[i][j] = (sChar[i] == pChar[j] || pChar[j] == '.') && dp[i + 1][j + 1];
                } else {

                    int k = i;
                    while (k != sLen && (sChar[i] == pChar[j] || pChar[j] == '.')) {
                        if (dp[k][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        k++;
                    }
                    if (dp[i][j] != true)
                        dp[i][j] = dp[k][j + 2];
                }
            }
        }

        return dp[0][0];
    }

    private boolean[][] initDP(char[] sChar, char[] pChar) {

        int sLen = sChar.length;
        int pLen = pChar.length;
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[sLen][pLen] = true;
        for (int j = pLen - 2; j >= 0; j = j - 2) {
            if (pChar[j] != '*' && pChar[j + 1] == '*') {
                dp[sLen][j] = true;
            } else {
                break;
            }
        }

        if (sLen > 0 && pLen > 0) {
            if (pChar[pLen - 1] == '.' || (sChar[sLen - 1] == pChar[pLen - 1]))
                dp[sLen - 1][pLen - 1] = true;
        }

        return dp;
    }
}
