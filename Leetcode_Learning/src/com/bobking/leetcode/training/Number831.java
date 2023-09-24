package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-02 10:34
 */
public class Number831 {

    String[] country = {"", "+*-", "+**-", "+***-"};

    // 参考：https://leetcode.cn/problems/masking-personal-information/solution/yin-cang-ge-ren-xin-xi-by-leetcode-solut-2enf/
    public String maskPII(String s) {
        int at = s.indexOf("@");
        if (at > 0) {
            s = s.toLowerCase();
            return (s.charAt(0) + "*****" + s.substring(at - 1)).toLowerCase();
        }
        s = s.replaceAll("[^0-9]", "");
        return country[s.length() - 10] + "***-***-" + s.substring(s.length() - 4);
    }
}
