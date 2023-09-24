package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-25 8:22
 */
public class Number1920 {

    public int[] buildArray1(int[] nums) {

        int n = nums.length;

        int[] res = new int[n];

        for (int i = 0; i < n; ++i)
            res[i] = nums[nums[i]];

        return res;
    }

    // 参考：https://leetcode.cn/problems/build-array-from-permutation/solution/ji-yu-pai-lie-gou-jian-shu-zu-by-leetcod-gjcn/
    public int[] buildArray2(int[] nums) {

        int n = nums.length;

        // 注意到 nums 中元素的取值范围为 [0,999] 闭区间，因此这个位置才用 1000
        for (int i = 0; i < n; ++i)
            nums[i] += 1000 * (nums[nums[i]] % 1000);

        for (int i = 0; i < n; ++i)
            nums[i] /= 1000;

        return nums;
    }
}
