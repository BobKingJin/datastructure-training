package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-03 22:49
 */
public class Number1053 {

    public int[] prevPermOpt1(int[] arr) {

        int n = arr.length;

        for (int i = n - 1; i > 0; --i) {
            if (arr[i - 1] > arr[i]) {
                for (int j = n - 1; j > i - 1; --j) {
                    // 注意这个位置的 arr[j] != arr[j - 1] 是为了字典序更小，对于重复的数字要找到在最左边的
                    if (arr[j] < arr[i - 1] && arr[j] != arr[j - 1]) {
                        int t = arr[i - 1];
                        arr[i - 1] = arr[j];
                        arr[j] = t;
                        return arr;
                    }
                }
            }
        }
        return arr;
    }
}
