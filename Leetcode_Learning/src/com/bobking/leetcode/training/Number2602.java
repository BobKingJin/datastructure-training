package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2024/3/8 22:17
 * @Author: BobKing
 * @Description:
 */
public class Number2602 {

    // 参考: https://leetcode.cn/problems/minimum-operations-to-make-all-array-elements-equal/solutions/2191417/yi-tu-miao-dong-pai-xu-qian-zhui-he-er-f-nf55/
    public List<Long> minOperations(int[] nums, int[] queries) {

        Arrays.sort(nums);
        int n = nums.length;
        // 前缀和
        long[] sum = new long[n + 1];

        for (int i = 0; i < n; ++i)
            sum[i + 1] = sum[i] + nums[i];

        List<Long> ans = new ArrayList<Long>(queries.length);

        for (int q : queries) {
            int j = lowerBound(nums, q);
            long left = (long) q * j - sum[j]; // 蓝色面积
            long right = sum[n] - sum[j] - (long) q * (n - j); // 绿色面积
            ans.add(left + right);
        }
        return ans;
    }

    // 返回第一个大于等于 target 的角标
    private int lowerBound(int[] nums, int target) {
        // 开区间 (left, right)
        int left = -1;
        int right = nums.length;
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right;
    }
}
