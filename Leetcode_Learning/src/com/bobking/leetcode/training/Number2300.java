package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-11-10 8:22
 */
public class Number2300 {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        int[] ans = new int[spells.length];
        Arrays.sort(potions);
        long[] arr = new long[potions.length];

        for (int i = 0; i < arr.length; i++)
            arr[i] = potions[i];

        for (int i = 0; i < spells.length; i++) {
            int index = search(arr, spells[i], success);
            ans[i] = potions.length - index;
        }

        return ans;
    }

    private int search(long[] arr, long x, long target) {

        if (arr[arr.length - 1] * x < target)
            return arr.length;

        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] * x >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }
}
