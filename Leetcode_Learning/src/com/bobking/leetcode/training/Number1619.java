package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-03-30 22:37
 */
public class Number1619 {

    public double trimMean(int[] arr) {

        Arrays.sort(arr);
        int n = arr.length;
        int tot = 0;
        for (int i = n / 20; i < n - n / 20; i++)
            tot += arr[i];
        return tot * 1.0 / (n * 0.9);
    }
}
