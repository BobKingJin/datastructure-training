package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-27 16:30
 */
public class Number44 {

    // 参考：https://leetcode-cn.com/problems/wildcard-matching/solution/yi-ge-qi-pan-kan-dong-dong-tai-gui-hua-dpsi-lu-by-/
    // 参考：https://leetcode-cn.com/problems/wildcard-matching/solution/tong-pei-fu-pi-pei-by-leetcode-solution/
    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();
        // f[i][j] 表示 s 中的 0 - (i - 1) 字符能否匹配 p 中的 0 - (j - 1) 字符
        boolean[][] f = new boolean[m + 1][n + 1];

        // 空串
        f[0][0] = true;
        for(int i = 1; i <= n; i++)
            f[0][i] = f[0][i - 1] && p.charAt(i - 1) == '*';

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){

                // 当前字符需要匹配
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
                    f[i][j] = f[i - 1][j - 1];

                if(p.charAt(j - 1) == '*')
                    // 这个位置不是 f[i][j] = f[i][j - 1] || f[i - 1][j - 1] 的原因是因为 * 可以匹配任意多个前面的字符
                    // 例如： s = adceb p = a*b 那么考虑的是 f[i - 1][j] 即 * 可以匹配字符串 dce
                    f[i][j] = f[i][j - 1] || f[i - 1][j];
            }
        }

        return f[m][n];
    }
}
