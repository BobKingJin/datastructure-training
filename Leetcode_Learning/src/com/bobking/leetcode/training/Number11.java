package com.bobking.leetcode.training;

public class Number11 {

    // 参考：https://leetcode-cn.com/problems/container-with-most-water/solution/container-with-most-water-shuang-zhi-zhen-fa-yi-do/
    public int maxArea(int[] height) {

        if (height == null || height.length < 2) {
            return 0;
        }

        int res = Integer.MIN_VALUE;
        // 长方形的面积 = 长 * 高，所以要想面积最大，即长和高要尽可能的大，所以这里用双指针
        int left = 0;
        int right = height.length - 1;
        int multiply = 0;

        while (left < right) {
            multiply = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, multiply);
            // 移动短板，不能移动长板
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
