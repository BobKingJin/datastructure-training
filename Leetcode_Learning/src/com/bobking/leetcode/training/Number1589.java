package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-06-28 8:08
 */
public class Number1589 {

    // 参考：https://leetcode.cn/problems/maximum-sum-obtained-of-any-permutation/solutions/441184/suo-you-pai-lie-zhong-de-zui-da-he-by-leetcode-sol/
    //      https://www.bilibili.com/video/BV1WU4y1L7Su/?spm_id_from=333.337.search-card.all.click&vd_source=d03a75be00793d738878cc7fec840522
    public int maxSumRangeQuery(int[] nums, int[][] requests) {

        // 差分数组
        // counts[i] = nums[i], i = 0
        // counts[i] = nums[i] − nums[i − 1], i != 0
        // 对于最开始的 count[] 数组而言，它记录的不是频次，而是后一个数相对于前一个数 多/少
        // 例如对 nums[1] - nums[4] 范围内进行加一，那么其实 1 - 4 范围内的数都 +1，相当于这范围内的数是没有加的
        // nums[1] 比 nums[0] 多 1，而 nums[5] 比 nums[4] 少 1
        // 例如 nums[]:  0  1  2  3  4  5  6(角标位置)
        // [1 - 3]范围:    +1        -1
        // [2 - 5]范围:       +1           -1
        //             0  1  1  0  -1  0 -1  第一次count[]得到的不是频次，而是 后一个位置上的数频次 相对于 前一个位置的数频次差距
        //             0  1  2  2  1  1  0   通过counts[i] += counts[i - 1] 得到频次

        final int MOD = 1000000007;
        int length = nums.length;
        int[] counts = new int[length];

        for (int[] request : requests) {
            int start = request[0];
            int end = request[1];
            counts[start]++;
            if (end + 1 < length)
                counts[end + 1]--;
        }
        for (int i = 1; i < length; i++)
            // 反向递推得到nums中每个位置上的频次
            counts[i] += counts[i - 1];

        Arrays.sort(counts);
        Arrays.sort(nums);
        long sum = 0;

        for (int i = length - 1; i >= 0 && counts[i] > 0; i--)
            sum += (long) nums[i] * counts[i];

        return (int) (sum % MOD);
    }
}
