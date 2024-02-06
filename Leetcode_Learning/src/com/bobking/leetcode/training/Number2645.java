package com.bobking.leetcode.training;

/**
 * @Date: 2024/1/11 8:05
 * @Author: BobKing
 * @Description:
 */
public class Number2645 {

    // 参考: https://leetcode.cn/problems/minimum-additions-to-make-valid-string/solutions/2229526/kao-lu-xiang-lin-zi-mu-pythonjavacgo-by-m9yrc/?envType=daily-question&envId=2024-01-11
    public int addMinimum1(String word) {

        char[] s = word.toCharArray();

        int ans = s[0] + 2 - s[s.length - 1];

        for (int i = 1; i < s.length; i++)
            ans += (s[i] + 2 - s[i - 1]) % 3;

        return ans;
    }

    // 参考: https://leetcode.cn/problems/minimum-additions-to-make-valid-string/solutions/2229526/kao-lu-xiang-lin-zi-mu-pythonjavacgo-by-m9yrc/?envType=daily-question&envId=2024-01-11
    public int addMinimum2(String word) {

        char[] s = word.toCharArray();

        int t = 1;

        for (int i = 1; i < s.length; i++) {
            // 必须生成一个新的 abc
            if (s[i - 1] >= s[i])
                t++;
        }
        return t * 3 - s.length;
    }
}
