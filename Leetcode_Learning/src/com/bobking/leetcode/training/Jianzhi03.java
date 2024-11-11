package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/11 10:36
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi03 {

    // 题目: https://www.nowcoder.com/practice/6fe361ede7e54db1b84adc81d09d8524?tpId=13&tqId=1375279&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
    public int duplicate(int[] numbers) {

        if (numbers == null || numbers.length == 0)
            return -1;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]])
                    return numbers[i];
                int temp = numbers[numbers[i]];
                numbers[numbers[i]] = numbers[i];
                numbers[i] = temp;
                i--;
            }
        }
        return -1;
    }
}
