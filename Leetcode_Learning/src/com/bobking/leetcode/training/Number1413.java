package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number1413 {

    // 参考：https://leetcode.cn/problems/minimum-value-to-get-positive-step-by-step-sum/solution/zhu-bu-qiu-he-de-dao-zheng-shu-de-zui-xi-vyrt/
    public int minStartValue1(int[] nums) {

        // 要保证所有的累加和 accSum 满足 accSum + startValue ≥ 1
        // 只需保证累加和的最小值 accSumMin 满足 accSumMin + startValue ≥ 1
        // 那么startValue 的最小值即可取 −accSumMin + 1

        int accSum = 0;
        int accSumMin = 0;

        for (int num : nums) {
            accSum += num;
            accSumMin = Math.min(accSumMin, accSum);
        }

        return -accSumMin + 1;
    }

    // 参考：https://leetcode.cn/problems/minimum-value-to-get-positive-step-by-step-sum/solution/zhu-bu-qiu-he-de-dao-zheng-shu-de-zui-xi-vyrt/
    public int minStartValue2(int[] nums) {

        // 当 nums 所有元素均为非负数时，可以直接返回 1
        // 当有负数时，可以当某个数字满足 startValue 的要求时，比它大的数字肯定也都满足，比它小的数字则不一定能满足
        // 因此 startValue 的性质具有单调性，此题可以用二分查找来解决
        // 二分查找的左起始点为 1，右起始点可以设为 nums 的最小值的相反数乘上长度后再加 1
        // 这样可以保证右端点一定满足 startValue 的要求
        // 判断某个数字是否满足 startValue 的要求时，可以将 nums 的数字逐步加到这个数字上，判断是否一直为正即可

        int m = Arrays.stream(nums).min().getAsInt();
        if (m >= 0)
            return 1;

        int left = 1;
        int right = -m * nums.length + 1;

        while (left < right) {
            int medium = (left + right) / 2;
            if (valid(medium, nums)) {
                right = medium;
            } else {
                left = medium + 1;
            }
        }
        return left;
    }

    private boolean valid(int startValue, int[] nums) {

        for (int num : nums) {
            startValue += num;
            if (startValue <= 0)
                return false;
        }

        return true;
    }
}
