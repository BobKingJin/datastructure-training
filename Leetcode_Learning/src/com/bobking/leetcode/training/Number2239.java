package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-24 8:27
 */
public class Number2239 {

    public int findClosestNumber(int[] nums) {

        int ans = Integer.MAX_VALUE;

        for(int num : nums) {
            int absNum = Math.abs(num);
            if(absNum < Math.abs(ans) || (absNum == Math.abs(ans) && num > ans))
                ans = num;
        }

        return ans;
    }
}
