package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-17 10:51
 */
public class Number1567 {

    // 参考：https://leetcode.cn/problems/maximum-length-of-subarray-with-positive-product/solution/cheng-ji-wei-zheng-shu-de-zui-chang-zi-shu-zu-ch-3/
    public int getMaxLen(int[] nums) {

        // 可以使用动态规划得到乘积为正数的最长子数组长度
        // 定义两个数组 positive 和 negative
        // positive[i] 表示以下标 i 结尾的乘积为正数的最长子数组长度
        // negative[i] 表示乘积为负数的最长子数组长度

        int length = nums.length;
        int[] positive = new int[length];
        int[] negative = new int[length];

        if (nums[0] > 0) {
            positive[0] = 1;
        } else if (nums[0] < 0) {
            negative[0] = 1;
        }

        int maxLength = positive[0];

        for (int i = 1; i < length; i++) {
            if (nums[i] > 0) {
                positive[i] = positive[i - 1] + 1;
                negative[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
            } else if (nums[i] < 0) {
                positive[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
                negative[i] = positive[i - 1] + 1;
            } else {
                positive[i] = 0;
                negative[i] = 0;
            }
            maxLength = Math.max(maxLength, positive[i]);
        }

        return maxLength;
    }
}
