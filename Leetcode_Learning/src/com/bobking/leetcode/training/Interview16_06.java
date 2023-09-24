package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-05-02 8:13
 */
public class Interview16_06 {

    public int smallestDifference1(int[] a, int[] b) {

        // 对两个数组进行排序 双指针
        // int diff = a[i] - b[j]
        // 如果 diff < 0 ，表示 a[i] 小于 b[j] ，a 尽可能接近 b，那么 i++
        // 如果 diff > 0 ，表示 a[i] 大于 b[j] ，b 尽可能接近 a，那么 j++
        // 特殊情况：
        // a = {1, 2, 3, 4, 5}
        // b = {6, 7, 8, 9, 10}
        // 如果 a 数组最大值比 b 数组最小值还小，那么 a 数组 i 会一直右移，直到到达边界 break

        int alen = a.length;
        int blen = b.length;
        Arrays.sort(a);
        Arrays.sort(b);
        int minVal = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        while (i < alen && j < blen) {
            // 使用 long，防止 -2147483648 转正数后还是 -2147483648
            long diff = a[i] - b[j];
            minVal = (int) Math.min(Math.abs(diff), minVal);
            if (diff < 0) {
                i++;
            } else {
                j++;
            }
        }
        return minVal;
    }

    public int smallestDifference2(int[] a, int[] b) {

        Arrays.sort(a);
        Arrays.sort(b);
        long res = Integer.MAX_VALUE;

        for (int num : a) {
            int left = 0;
            int right = b.length - 1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                long diff = (long) num - b[mid];
                res = Math.min(res, Math.abs(diff));

                if (diff == 0)
                    return (int) res;

                if (diff > 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return (int) res;
    }
}
