package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-12-07 11:54
 */
public class Number1775 {

    // 参考：https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations/solution/mei-xiang-ming-bai-yi-ge-dong-hua-miao-d-ocuu/
    public int minOperations(int[] nums1, int[] nums2) {

        if (6 * nums1.length < nums2.length || 6 * nums2.length < nums1.length)
            return -1;

        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();
        if (sum1 == sum2)
            return 0;
        if (sum1 > sum2)
            return minOperations(nums2, nums1);

        int[] cnt = new int[6];
        int d = sum2 - sum1;
        int ans = 0;

        for (int x : nums1)
            cnt[6 - x]++;

        for (int x : nums2)
            cnt[x - 1]++;

        for (int i = 5; i >= 0; i--) {
            if (i * cnt[i] >= d)
                return ans + (d + i - 1) / i;
            ans += cnt[i];
            d -= i * cnt[i];
        }

        return ans;
    }
}
