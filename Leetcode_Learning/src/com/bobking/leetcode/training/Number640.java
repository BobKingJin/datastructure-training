package com.bobking.leetcode.training;

public class Number640 {

    // 参考：https://leetcode.cn/problems/solve-the-equation/solution/qiu-jie-fang-cheng-by-leetcode-solution-knct/
    public String solveEquation(String equation) {

        // 将等式右边的项都移到等式左边，那么等式右边的项的默认系数为 −1。依次解析方程的项，并将同类项进行合并
        // 使用 factor 表示变量的系数，val 表示常量值
        // 初始时默认系数 sign1 = 1，当解析到等号时，说明解析到等式右边的项，令 sign = −1
        // 使用变量 sign2 表示项的符号，初始时 sign2 = sign1​
        // 如果解析到 ‘+’ 或 ‘-’，那么相应的更改 sign2​
        // 使用 number 记录数字，valid 表示 number 是否有效（变量 x 前面可能没有数字）
        // 如果解析到的项是变量项，那么相应的更改 factor，如果解析到的项是常量项，那么相应的更改 val
        // 如果 factor = 0 成立，说明变量 x 对方程无影响，然后判断 val = 0 是否成立，成立则说明方程有无数解
        // 返回 Infinite solutions，否则返回 No solution

        int factor = 0;
        int val = 0;
        int index = 0;
        int n = equation.length();
        // 等式左边默认系数为正
        int sign1 = 1;
        while (index < n) {
            if (equation.charAt(index) == '=') {
                // 等式右边默认系数为负
                sign1 = -1;
                index++;
                continue;
            }

            int sign2 = sign1;
            int number = 0;
            // 记录 number 是否有效
            boolean valid = false;
            // 去掉前面的符号
            if (equation.charAt(index) == '-' || equation.charAt(index) == '+') {
                sign2 = (equation.charAt(index) == '-') ? -sign1 : sign1;
                index++;
            }

            while (index < n && Character.isDigit(equation.charAt(index))) {
                number = number * 10 + (equation.charAt(index) - '0');
                index++;
                valid = true;
            }

            // 变量
            if (index < n && equation.charAt(index) == 'x') {
                factor += valid ? sign2 * number : sign2;
                index++;
            } else { // 数值
                val += sign2 * number;
            }
        }

        if (factor == 0)
            return val == 0 ? "Infinite solutions" : "No solution";

        if (val % factor != 0)
            return "No solution";

        return "x=" + (-val / factor);
    }
}


