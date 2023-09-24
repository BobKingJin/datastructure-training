package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-04-19 7:48
 */
public class Number2418 {

    public String[] sortPeople(String[] names, int[] heights) {

        int n = names.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = heights[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] res = new String[n];
        for (int i = 0; i < n; i++)
            res[i] = names[arr[i][1]];

        return res;
    }
}
