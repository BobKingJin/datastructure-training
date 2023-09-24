package com.bobking.leetcode.training;

public class Number1071 {

    // 参考：https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/solution/zi-fu-chuan-de-zui-da-gong-yin-zi-by-leetcode-solu/
    public String gcdOfStrings1(String str1, String str2) {

        int len1 = str1.length();
        int len2 = str2.length();
        // 从长度大的开始枚举
        for (int i = Math.min(len1, len2); i >= 1; i--) {
            if (len1 % i == 0 && len2 % i == 0) {
                String X = str1.substring(0, i);
                if (check(X, str1) && check(X, str2))
                    return X;
            }
        }

        return "";
    }

    private boolean check(String t, String s) {

        int lenx = s.length() / t.length();
        StringBuffer ans = new StringBuffer();
        for (int i = 1; i <= lenx; i++)
            ans.append(t);

        return ans.toString().equals(s);
    }

    // 参考：程序猿代码指南P448
    // 参考：https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/solution/zi-fu-chuan-de-zui-da-gong-yin-zi-by-leetcode-solu/
    public String gcdOfStrings2(String str1, String str2) {

        if (!str1.concat(str2).equals(str2.concat(str1)))
            return "";

        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {

        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }
}
