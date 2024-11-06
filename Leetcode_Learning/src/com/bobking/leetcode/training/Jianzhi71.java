package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/6 10:11
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi71 {

    // 题目: https://www.nowcoder.com/practice/22243d016f6b47f2a6928b4313c85387?tpId=13&tqId=23262&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
    public int jumpFloorII(int number) {

        // 因为 n 级台阶，第一步有 n 种跳法：跳 1 级、跳 2 级、... 到跳 n 级
        // 跳 1 级，剩下 n - 1 级，则剩下跳法是 f(n - 1)
        // 跳 2 级，剩下 n - 2 级，则剩下跳法是 f(n - 2)
        // 所以 f(n) = f(n - 1) + f(n - 2) + ... + f(1)
        // 因为 f(n - 1) = f(n - 2) + f(n - 3) +... + f(1)
        // 所以 f(n) = 2 * f(n - 1)

        if (number <= 0)
            return 0;
        return 1 << (number - 1);
    }
}
