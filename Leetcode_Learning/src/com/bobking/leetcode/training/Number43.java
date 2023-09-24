package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-27 16:10
 */
public class Number43 {

    // 参考：https://leetcode-cn.com/problems/multiply-strings/solution/you-hua-ban-shu-shi-da-bai-994-by-breezean/
    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0"))
            return "0";

        // 保存计算结果
        String res = "0";

        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {

            // 进位
            int ca = 0;
            // 保存 num2 第 i 位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();

            // 补 0
            // nums2 中每移动一位，其相乘的结果也需要先向左边移动一位
            for (int j = 0; j < num2.length() - 1 - i; j++)
                temp.append(0);

            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            // 注意当 j = 0时，此时可能还有最后一位进位(ca != 0)
            for (int j = num1.length() - 1; j >= 0 || ca != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + ca) % 10;
                temp.append(product);
                ca = (n1 * n2 + ca) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }

        return res;
    }

    // 对两个字符串数字进行相加，返回字符串形式的和
    private String addStrings(String num1, String num2) {

        StringBuilder builder = new StringBuilder();
        int ca = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || ca != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + ca) % 10;
            builder.append(sum);
            ca = (x + y + ca) / 10;
        }

        return builder.reverse().toString();
    }
}
