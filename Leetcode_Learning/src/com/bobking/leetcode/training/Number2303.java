package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-23 9:37
 */
public class Number2303 {

    public double calculateTax(int[][] brackets, int income) {

        double totalTax = 0;
        int lower = 0;

        for (int[] bracket : brackets) {

            int upper = bracket[0];
            int percent = bracket[1];
            int tax = (Math.min(income, upper) - lower) * percent;
            totalTax += tax;

            if (income <= upper)
                break;

            lower = upper;
        }
        return (double) totalTax / 100.0;
    }
}
