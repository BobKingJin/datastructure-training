package com.bobking.leetcode.training;

public class Number1004 {

    // 参考：https://leetcode.cn/problems/max-consecutive-ones-iii/solution/fen-xiang-hua-dong-chuang-kou-mo-ban-mia-f76z/
    public int longestOnes(int[] nums, int k) {

        int N = nums.length;
        int res = 0;
        int left = 0;
        int right = 0;
        int zeros = 0;

        while (right < N) {

            if (nums[right] == 0)
                zeros++;

            while (zeros > k) {
                if (nums[left++] == 0)
                    zeros--;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }
}
