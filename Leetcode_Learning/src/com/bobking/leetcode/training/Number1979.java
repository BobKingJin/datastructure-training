package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-02 8:54
 */
public class Number1979 {

    public int findGCD(int[] nums) {

        int maxNum = nums[0];
        int minNum = nums[0];

        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
            minNum = Math.min(minNum, num);
        }

        return gcd(maxNum, minNum);
    }

    private int gcd(int numA, int numB) {

        int max = Math.max(numA, numB);
        int min = Math.min(numA, numB);

        if (max % min != 0)
            return gcd(max - min, min);

        return min;
    }
}
