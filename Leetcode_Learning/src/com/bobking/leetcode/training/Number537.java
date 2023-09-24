package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-09 15:15
 */
public class Number537 {

    // 参考：https://leetcode.cn/problems/complex-number-multiplication/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-avlh/
    public String complexNumberMultiply(String num1, String num2) {

        String[] ss1 = num1.split("\\+|i");
        String[] ss2 = num2.split("\\+|i");
        int a = parse(ss1[0]);
        int b = parse(ss1[1]);
        int c = parse(ss2[0]);
        int d = parse(ss2[1]);
        int A = a * c - b * d;
        int B = b * c + a * d;
        return A + "+" + B + "i";
    }

    private int parse(String s) {
        return Integer.parseInt(s);
    }
}
