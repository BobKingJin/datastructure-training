package com.bobking.leetcode.training;

public class Number115 {

    // 参考：https://leetcode-cn.com/problems/distinct-subsequences/solution/xiang-jie-zi-fu-chuan-pi-pei-wen-ti-de-t-wdtk/
    public int numDistinct(String s, String t) {

        if(s == null || s.length() == 0 || t == null || s.length() < t.length())
            return 0;

        if(t.length() == 0)
            return 1;

        // 定义 f[i][j] 为考虑 s 中 [0, i]个字符，t 中 [0, j]个字符的匹配个数
        // 那么显然对于某个 f[i][j] 而言，从「最后一步」的匹配进行分析，包含两类决策：
        // 不让 s[i] 参与匹配，也就是需要让 s 中 [0, i - 1] 个字符去匹配 t 中的 [0, j] 字符 此时匹配值为 f[i - 1][j]
        // 让 s[i] 参与匹配，这时候只需要让 s 中 [0, i - 1]个字符去匹配 t 中的 [0, j - 1]字符即可
        // 同时满足 s[i] = t[j]，此时匹配值为 f[i - 1][j - 1]
        // 最终 f[i][j] 就是两者之和

        // 技巧：往原字符头部插入空格，这样得到 char 数组是从 1 开始
        // 同时由于往头部插入相同的（不存在的）字符，不会对结果造成影响，而且可以使得 f[i][0] = 1，可以将 1 这个结果滚动下去
        int n = s.length();
        int m = t.length();
        s = " " + s;
        t = " " + t;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        // f(i, j) 代表考虑「s 中的下标为 0 ~ i 字符」和「t 中下标为 0 ~ j 字符」是否匹配
        int[][] f = new int[n + 1][m + 1];
        // 原字符只有小写字符，当往两个字符插入空格之后，f[i][0] = 1 是一个显而易见的初始化条件
        for (int i = 0; i <= n; i++)
            f[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 包含两种决策：
                // 不使用 cs[i] 进行匹配，则有 f[i][j] = f[i - 1][j]
                f[i][j] = f[i - 1][j];
                // 使用 cs[i] 进行匹配，则要求 cs[i] == ct[j]，然后有  f[i][j] += f[i - 1][j - 1]
                if (cs[i] == ct[j])
                    f[i][j] += f[i - 1][j - 1];
            }
        }

        return f[n][m];
    }
}
