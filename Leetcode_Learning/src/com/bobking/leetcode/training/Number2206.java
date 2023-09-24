package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-12 22:51
 */
public class Number2206 {

    public boolean divideArray(int[] nums) {

        int[] arr = new int[505];
        int odd = 0;

        for (int i = 0; i < nums.length; i++) {

            arr[nums[i]] ^= 1;
            if (arr[nums[i]] == 1) {
                odd++;
            } else {
                odd--;
            }
        }
        return odd == 0;
    }
}
