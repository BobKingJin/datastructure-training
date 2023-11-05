package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-05 6:40
 */
public class Number1287 {

    public int findSpecialInteger(int[] arr) {

        int threshold = arr.length / 4;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i + threshold] == arr[i])
                return arr[i];

        }
        return 0;
    }
}
