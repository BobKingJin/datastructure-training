package com.bobking.leetcode.training;

/**
 * @Date: 2025/7/27 9:44
 * @Author: BobKing
 * @Description:
 */
public class Number1550 {

    public boolean threeConsecutiveOdds(int[] arr) {
        for (int i = 2; i < arr.length; i++) {
            if (arr[i - 2] % 2 != 0 && arr[i - 1] % 2 != 0 && arr[i] % 2 != 0) {
                return true;
            }
        }
        return false;
    }

}
