package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2024/11/13 11:25
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi61 {

    // 题目: https://www.nowcoder.com/practice/762836f4d43d43ca9deb273b3de8e1f4?tpId=13&tqId=23252&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
    public boolean IsContinuous(int[] numbers) {

        if (numbers == null || numbers.length == 0)
            return false;

        Set<Integer> set = new HashSet<Integer>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int a : numbers) {
            if (!set.add(a) && a != 0)
                return false;
            if (a != 0) {
                max = Math.max(max, a);
                min = Math.min(min, a);
            }
        }
        if (max - min <= 4)
            return true;
        return false;
    }
}
