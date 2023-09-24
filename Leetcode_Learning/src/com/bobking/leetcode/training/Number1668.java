package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-11-03 10:34
 */
public class Number1668 {

    public int maxRepeating1(String sequence, String word) {

        int count = 0;
        StringBuilder sb = new StringBuilder(word);

        while(sequence.contains(sb)) {
            count++;
            sb.append(word);
        }
        return count;
    }

    // 参考：https://leetcode.cn/problems/maximum-repeating-substring/solution/zui-da-zhong-fu-zi-zi-fu-chuan-by-leetco-r4cp/
    public int maxRepeating2(String sequence, String word) {

        // 可以将给定字符串 sequence 的每一个位置作为结束位置
        // 判断前面的若干个字符是否恰好是字符串 word
        // 如果第 i 个位置是，那么可以记录 valid[i] 的值为 1，否则为 0

        int n = sequence.length();
        int m = word.length();
        if (n < m)
            return 0;

        // f[i] 表示以第 i 个位置为结尾时，word出现的最大重复值
        int[] f = new int[n];

        for (int i = m - 1; i < n; ++i) {
            boolean valid = true;
            for (int j = 0; j < m; ++j) {
                if (sequence.charAt(i - m + j + 1) != word.charAt(j)) {
                    valid = false;
                    break;
                }
            }
            if (valid)
                f[i] = (i == m - 1 ? 0 : f[i - m]) + 1;
        }

        return Arrays.stream(f).max().getAsInt();
    }
}
