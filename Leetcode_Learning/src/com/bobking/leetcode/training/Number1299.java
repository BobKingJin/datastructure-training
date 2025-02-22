package com.bobking.leetcode.training;

public class Number1299 {

    public int[] replaceElements(int[] arr) {

        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int x = arr[i];
            arr[i] = max;
            max = Math.max(max, x);
        }
        return arr;
    }
}
