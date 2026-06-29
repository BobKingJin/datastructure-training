package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/11 10:36
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi03 {

    // 题目: https://www.nowcoder.com/practice/6fe361ede7e54db1b84adc81d09d8524?tpId=13&tqId=1375279&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
    public int duplicate(int[] numbers) {

        if (numbers == null || numbers.length == 0) {
            return -1;
        }

        int i = 0;
        int n = numbers.length;
        while (i < n) {
            if (numbers[i] == i) {
                i++;
            } else {
                if (numbers[i] == numbers[numbers[i]]) {
                    return numbers[i];
                } else {
                    swap(numbers, i, numbers[i]);
                }
            }
        }
        return -1;
    }

    private void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
