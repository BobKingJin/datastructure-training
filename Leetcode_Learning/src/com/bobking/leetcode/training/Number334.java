package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-05-03 20:06
 */
public class Number334 {

    // 参考：https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/gong-shui-san-xie-zui-chang-shang-sheng-xa08h/
    public boolean increasingTriplet1(int[] nums) {
        
        int n = nums.length;
        int res = 1;
        int[] f = new int[n + 1];
        Arrays.fill(f, 0x3f3f3f3f);

        // 就是在遍历每个数 nums[i] 的同时，维护一个具有单调性的 f[] 数组，其中 f[len] = x 代表长度为 len 的最长上升子序列最小结尾元素为 x
        // 因此每次可以通过二分找到小于 nums[i] 的最大下标，来作为 nums[i] 的前一个数
        for (int i = 0; i < n; i++) {
            int t = nums[i];
            int l = 1;
            int r = i + 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (f[mid] >= t) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            f[r] = t;
            res = Math.max(res, r);
        }

        return res >= 3;
    }

    // 参考：https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/di-zeng-de-san-yuan-zi-xu-lie-by-leetcod-dp2r/
    public boolean increasingTriplet2(int[] nums) {

        int n = nums.length;
        if (n < 3)
            return false;

        // 可以维护数组 nums 中的每个元素左边的最小值和右边的最大值
        // 对于 0 ≤ i < n，leftMin[i] 表示 nums[0] 到 nums[i] 中的最小值，rightMax[i] 表示 nums[i] 到 nums[n − 1] 中的最大值
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++)
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);

        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);

        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1])
                return true;
        }

        return false;
    }

    // 参考：https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/di-zeng-de-san-yuan-zi-xu-lie-by-leetcod-dp2r/
    public boolean increasingTriplet3(int[] nums) {

        int n = nums.length;
        if (n < 3)
            return false;

        int first = nums[0];
        int second = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }

        return false;
    }



}
