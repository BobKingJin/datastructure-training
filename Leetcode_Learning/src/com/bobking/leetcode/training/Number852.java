package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-08-12 18:06
 */
public class Number852 {

    // 参考：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/solution/gong-shui-san-xie-er-fen-san-fen-cha-zhi-5gfv/
    public int peakIndexInMountainArray(int[] arr) {

        // 因为题目必有解，所以至少存在一个单调递增区间，既然存在单调递增区间，那么山顶的角标至少为 1
        int l = 1;
        int r = arr.length - 1;
        while (l < r) {

            int mid = (l + r + 1) >> 1;
            // 因为 mid 至少为 1 了，不存在 mid - 1会越界的情况
            if (arr[mid - 1] < arr[mid]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

}
