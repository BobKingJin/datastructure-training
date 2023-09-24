package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-10-09 10:32
 */
public class Number1818 {

    // 参考：https://leetcode.cn/problems/minimum-absolute-sum-difference/solution/jue-dui-chai-zhi-he-by-leetcode-solution-gv78/
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {

        final int MOD = 1000000007;
        int n = nums1.length;
        int[] arr = new int[n];
        System.arraycopy(nums1, 0, arr, 0, n);
        Arrays.sort(arr);

        // 使用 sum 记录所有的差值和
        int sum = 0;
        // 用 maxn 记录最大的改变前后的差值
        int maxn = 0;

        for (int i = 0; i < n; i++) {

            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;
            // 使用二分查找的方法快速找到 nums1 数组中尽可能接近 nums2[i] 的元素
            int j = binarySearch(arr, nums2[i]);

            if (j < n)
                maxn = Math.max(maxn, diff - (arr[j] - nums2[i]));

            if (j > 0)
                maxn = Math.max(maxn, diff - (nums2[i] - arr[j - 1]));
        }

        return (sum - maxn + MOD) % MOD;
    }

    private int binarySearch(int[] arr, int target) {
        
        int low = 0;
        int high = arr.length - 1;
        
        if (arr[high] < target)
            return high + 1;
        
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
