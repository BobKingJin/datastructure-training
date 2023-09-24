package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-09-18 6:38
 */
public class Number2605 {

    public int minNumber1(int[] nums1, int[] nums2) {

        Set<Integer> set = new HashSet<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int min1 = nums1[0];
        int min2 = nums2[0];
        for (int num : nums1)
            set.add(num);
        for (int num : nums2) {
            if (set.contains(num))
                return num;
        }

        return Math.min(10 * min1 + min2, 10 * min2 + min1);
    }

    public int minNumber2(int[] nums1, int[] nums2) {

        int mask1 = 0;
        int mask2 = 0;

        for (int x : nums1)
            mask1 |= 1 << x;
        for (int x : nums2)
            mask2 |= 1 << x;

        int m = mask1 & mask2;

        // 有交集
        if (m > 0)
            return Integer.numberOfTrailingZeros(m);

        int x = Integer.numberOfTrailingZeros(mask1);
        int y = Integer.numberOfTrailingZeros(mask2);
        return Math.min(x * 10 + y, y * 10 + x);
    }
}
