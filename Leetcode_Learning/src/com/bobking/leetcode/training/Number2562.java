package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-12 7:16
 */
public class Number2562 {

    public long findTheArrayConcVal(int[] nums) {

        long ans = 0;
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int x = nums[i];
            int y = nums[j];
            while (y != 0) {
                x *= 10;
                y /= 10;
            }
            ans += (x + nums[j]);
            i++;
            j--;
        }
        //最后只剩一个元素
        if (i == j)
            ans += nums[i];

        return ans;
    }
}
