package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-05-10 6:48
 */
public class Number1491 {

    public double average(int[] salary) {

        Arrays.sort(salary);
        int len = salary.length;
        int sum = 0;

        for (int i = 1; i < len - 1; i++)
            sum += salary[i];

        return 1.0 * sum / (len - 2);
    }
}
