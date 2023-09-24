package com.bobking.leetcode.training;

public class Number713 {

    // 参考：https://leetcode-cn.com/problems/subarray-product-less-than-k/solution/by-ac_oier-3w08/
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        int n = nums.length;
        int res = 0;

        if (k <= 1)
            return 0;

        // 使用一个变量 cur 记录当前窗口的乘积，使用两个变量 j 和 i 分别代表当前窗口的左右端点
        for (int i = 0, j = 0, cur = 1; i < n; i++) {

            // 当 cur >= k 时，考虑将左端点 j 右移，同时消除原来左端点元素 nums[j] 对 cur 的贡献，直到 cur >= k 不再满足
            // 这样就可以得到每个右端点 nums[i] 的最远左端点 nums[j]
            // 从而得知以 nums[i] 为结尾（固定右端点）的合法子数组个数（左端点个数）为 i - j + 1
            cur *= nums[i];

            while (cur >= k)
                cur /= nums[j++];

            res += i - j + 1;
        }

        return res;
    }
}
