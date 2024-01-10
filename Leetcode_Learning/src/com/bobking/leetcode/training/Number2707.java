package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2024/1/9 6:56
 * @Author: BobKing
 * @Description:
 */
public class Number2707 {

    // 参考: https://leetcode.cn/problems/extra-characters-in-a-string/solutions/2286613/dong-tai-gui-hua-cong-ji-yi-hua-sou-suo-wtd7a/?envType=daily-question&envId=2024-01-09
    public int minExtraChar(String s, String[] dictionary) {

        Set<String> set = new HashSet<String>(Arrays.asList(dictionary));

        int n = s.length();
        // f[i] 表示 s 的前 i 个字符
        int[] f = new int[n + 1];

        for (int i = 0; i < n; i++) {
            f[i + 1] = f[i] + 1;
            for (int j = 0; j < i + 1; j++) {
                if (set.contains(s.substring(j, i + 1)))
                    f[i + 1] = Math.min(f[i + 1], f[j]);
            }
        }

        return f[n];
    }
}
