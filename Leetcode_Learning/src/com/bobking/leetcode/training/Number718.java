package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-04-30 22:24
 */
public class Number718 {

    // 参考：程序猿代码指南P223
    public int findLength1(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return 0;

        // dp[i][j] 表示 nums1[i] 结尾 nums2[j] 结尾 公共子串长度
        int[][] dp = new int[nums1.length][nums2.length];
        int max = 0;
        // 第一列
        for (int i = 0; i < nums1.length; i++) {
            dp[i][0] = (nums1[i] == nums2[0]) ? 1 : 0;
            if (dp[i][0] > max)
                max = dp[i][0];
        }
        // 第一行
        for (int j = 0; j < nums2.length; j++) {
            dp[0][j] = (nums1[0] == nums2[j]) ? 1 : 0;
            if (dp[0][j] > max)
                max = dp[0][j];
        }

        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > max)
                        max = dp[i][j];
                }
            }
        }

        return max;
    }

    // 参考：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/solution/wu-li-jie-fa-by-stg-2/
    public int findLength2(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return 0;

        return nums1.length < nums2.length ? slidingWindow(nums1, nums2) : slidingWindow(nums2, nums1);
    }

    private int slidingWindow(int[] A, int[] B) {

        int aLength = A.length;
        int bLength = B.length;
        int max = 0;

        for (int len = 1; len <= aLength; len++)
            max = Math.max(max, maxLength(A, 0, B, bLength - len, len));

        for (int bStart = bLength - aLength; bStart >= 0; bStart--)
            max = Math.max(max, maxLength(A, 0, B, bStart, aLength));

        for (int aStart = 1; aStart < aLength; aStart++)
            max = Math.max(max, maxLength(A, aStart, B, 0, aLength - aStart));

        return max;
    }

    private int maxLength(int[] A, int aStart, int[] B, int bStart, int len) {

        int max = 0;
        int count = 0;

        for (int i = 0; i < len; i++) {
            if (A[aStart + i] == B[bStart + i]) {
                count++;
            } else if (count > 0) {
                max = Math.max(max, count);
                count = 0;
            }
        }
        // 最后一次判断有可能 count > 0
        return count > 0 ? Math.max(max, count) : max;
    }

    // 参考：程序猿代码指南P225
    public int findLength3(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return 0;

        // 右上角
        int row = 0;
        int col = nums2.length - 1;
        int max = 0;

        while (row < nums1.length) {
            // 从(i, j)位置向右下方移动
            int i = row;
            int j = col;
            int len = 0;

            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] != nums2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                if (len > max) {
                    max = len;
                }
                i++;
                j++;
            }

            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }

        return max;
    }

}
