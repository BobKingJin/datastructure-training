package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-24 11:28
 */
public class Number1031 {

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {

        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++)
            sums[i] = sums[i - 1] + nums[i - 1];

        return Math.max(getMaxSumTwoLap(sums, firstLen, secondLen), getMaxSumTwoLap(sums, secondLen, firstLen));
    }

    private int getMaxSumTwoLap(int[] sums, int firstLen, int secondLen) {

        int res = 0;
        int len = sums.length;
        int maxF = 0;
        int maxS = 0;

        for (int i = firstLen + 1; i <= len - secondLen; i++) {
            maxF = getMaxSum(sums, 0, i, firstLen);
            maxS = getMaxSum(sums, i - 1, len, secondLen);
            res = Math.max(res, maxF + maxS);
        }
        return res;
    }

    private int getMaxSum(int[] sums, int s, int e, int len) {

        int maxV = 0;
        for (int i = s; i < e - len; i++)
            maxV = Math.max(maxV, sums[i + len] - sums[i]);

        return maxV;
    }

}
