package com.bobking.leetcode.training;

public class Number592 {

    // 参考：https://leetcode.cn/problems/fraction-addition-and-subtraction/solution/fen-shu-jia-jian-yun-suan-by-leetcode-so-2mto/
    public String fractionAddition(String expression) {

        // 分子
        long denominator = 0;
        // 分母
        long numerator = 1;
        int index = 0;
        int n = expression.length();
        while (index < n) {
            // 读取分子
            long denominator1 = 0;
            long sign = 1;
            if (expression.charAt(index) == '-' || expression.charAt(index) == '+') {
                sign = expression.charAt(index) == '-' ? -1 : 1;
                index++;
            }
            while (index < n && Character.isDigit(expression.charAt(index))) {
                denominator1 = denominator1 * 10 + expression.charAt(index) - '0';
                index++;
            }
            denominator1 = sign * denominator1;
            index++;

            // 读取分母
            long numerator1 = 0;
            while (index < n && Character.isDigit(expression.charAt(index))) {
                numerator1 = numerator1 * 10 + expression.charAt(index) - '0';
                index++;
            }

            denominator = denominator * numerator1 + denominator1 * numerator;
            numerator *= numerator1;
        }

        if (denominator == 0)
            return "0/1";
        // 获取最大公约数
        long g = gcd(Math.abs(denominator), numerator);
        return Long.toString(denominator / g) + "/" + Long.toString(numerator / g);
    }

    private long gcd(long a, long b) {
        long remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }
}
