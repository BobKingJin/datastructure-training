package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2024/5/18 10:00
 * @Author: BobKing
 * @Description:
 */
public class Number2644 {

    public int maxDivScore1(int[] nums, int[] divisors) {

        int ans = 0;
        int maxCnt = -1;

        for (int d : divisors) {
            int cnt = 0;
            for (int x : nums) {
                if (x % d == 0)
                    cnt++;
            }

            if (cnt > maxCnt || cnt == maxCnt && d < ans) {
                maxCnt = cnt;
                ans = d;
            }
        }
        return ans;
    }

    public int maxDivScore2(int[] nums, int[] divisors) {

        Arrays.sort(nums);
        int ans = 0;
        int maxCnt = -1;

        for (int d : divisors) {
            int cnt = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                int x = nums[i];
                if (x < d)
                    break;
                if (x % d == 0)
                    cnt++;
            }
            if (cnt > maxCnt || cnt == maxCnt && d < ans) {
                maxCnt = cnt;
                ans = d;
            }
        }
        return ans;
    }

}
