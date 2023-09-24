package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-05 6:24
 */
public class Number795 {

    // 参考：https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum/solution/tu-jie-yi-ci-bian-li-jian-ji-xie-fa-pyth-n75l/
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {

        int n = nums.length;
        int ans = 0;
        int i0 = -1;
        int i1 = -1;

        for (int i = 0; i < n; ++i) {
            if (nums[i] > right)
                i0 = i;
            if (nums[i] >= left)
                i1 = i;
            ans += i1 - i0;
        }

        return ans;
    }
}
