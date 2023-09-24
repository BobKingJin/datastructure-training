package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-05-15 7:56
 */
public class Number1509 {

    public int minDifference1(int[] nums) {

        int n = nums.length;
        if (n <= 4)
            return 0;

        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;

        // 只需要找到最大的四个数与最小的四个数
        // 当删去最小的 k(0 ≤ k ≤ 3) 个数，还需要删去 3 − k 个最大值
        for (int i = 0; i < 4; i++)
            res = Math.min(res, nums[n - 4 + i] - nums[i]);

        return res;
    }

    public int minDifference2(int[] nums) {
        
        int n = nums.length;
        if (n <= 4)
            return 0;
        
        int[] max = new int[4];
        int[] min = new int[4];
        Arrays.fill(max, -1000000000);
        Arrays.fill(min, 1000000000);
        
        for (int i = 0; i < n; i++) {

            int add = 0;
            while (add < 4 && max[add] > nums[i])
                add++;
            
            if (add < 4) {
                for (int j = 3; j > add; j--)
                    max[j] = max[j - 1];
                max[add] = nums[i];
            }
            
            add = 0;
            
            while (add < 4 && min[add] < nums[i])
                add++;
            
            if (add < 4) {
                for (int j = 3; j > add; j--)
                    min[j] = min[j - 1];
                min[add] = nums[i];
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++)
            res = Math.min(res, max[i] - min[3 - i]);
        
        return res;
    }
}
