package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-24 14:33
 */
public class Number1300 {

    // 参考：https://leetcode.cn/problems/sum-of-mutated-array-closest-to-target/solution/er-fen-cha-zhao-by-liweiwei1419-2/
    public int findBestValue(int[] arr, int target) {

        int left = 0;
        int right = 0;

        for (int num : arr)
            right = Math.max(right, num);

        while (left < right) {
            int mid = left + (right - left) / 2;
            int sum = calculateSum(arr, mid);
            // 计算第 1 个使得转变后数组的和大于等于 target 的阈值 threshold
            if (sum < target) {
                // 严格小于的一定不是解
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 比较阈值线分别定在 left - 1 和 left 的时候与 target 的接近程度
        int sum1 = calculateSum(arr, left - 1);
        int sum2 = calculateSum(arr, left);
        if (target - sum1 <= sum2 - target)
            return left - 1;

        return left;
    }

    private int calculateSum(int[] arr, int threshold) {

        int sum = 0;
        for (int num : arr)
            sum += Math.min(num, threshold);

        return sum;
    }
}
