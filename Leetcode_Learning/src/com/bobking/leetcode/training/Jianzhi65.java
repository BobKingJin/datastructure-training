package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/12 10:19
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi65 {

    // 题目: https://www.nowcoder.com/practice/59ac416b4b944300b617d4f7f111b215?tpId=13&tqId=23249&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
    public int Add(int num1, int num2) {
        // 位运算中两数进行 异或 运算可以提供两数加和后二进制非进位信息
        // 位运算中的两数进行 与 运算的结果可以提供两数加和后的二进制进位信息
        while (num2 != 0) {
            int temp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return num1;
    }
}
