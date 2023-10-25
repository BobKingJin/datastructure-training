package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-23 9:03
 */
public class Number2678 {

    public int countSeniors(String[] details) {

        // 遍历每个字符串 s，如果 s[11] 和 s[12] 组成的数字大于 60，则答案加一
        int ans = 0;

        for (String s : details) {
            if ((s.charAt(11) - '0') * 10 + s.charAt(12) - '0' > 60)
                ans++;
        }

        return ans;
    }
}
