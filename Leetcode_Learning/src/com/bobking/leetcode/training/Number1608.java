package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-12 16:24
 */
public class Number1608 {

    // 参考：https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/solution/by-ac_oier-z525/
    public int specialArray1(int[] nums) {

        Arrays.sort(nums);
        int n = nums.length;

        for (int x = 0; x < 1010; x++) {

            int l = 0;
            int r = n - 1;

            // 需要快速知道 nums 中比 x 大的数的个数
            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] >= x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            if (nums[r] >= x && x == n - r)
                return x;
        }

        return -1;
    }

    int[] nums;

    // 参考：https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/solution/by-ac_oier-z525/
    public int specialArray2(int[] nums) {

        this.nums = nums;
        Arrays.sort(nums);

        int l = 0;
        int r = (int) 1e9;

        while (l < r) {
            int mid = l + r >> 1;
            if (getCnt(mid) <= mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return getCnt(r) == r ? r : -1;
    }

    private int getCnt(int x) {

        int n = nums.length;
        int l = 0;
        int r = n - 1;

        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return nums[r] >= x ? n - r : 0;
    }
}
