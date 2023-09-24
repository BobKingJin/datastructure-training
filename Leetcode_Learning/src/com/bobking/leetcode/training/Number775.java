package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-16 9:59
 */
public class Number775 {

    // 参考：https://leetcode.cn/problems/global-and-local-inversions/solution/quan-ju-dao-zhi-yu-ju-bu-dao-zhi-by-leet-bmjp/
    public boolean isIdealPermutation(int[] nums) {

        // 一个局部倒置一定是一个全局倒置，因此要判断数组中局部倒置的数量是否与全局倒置的数量相等，只需要检查有没有非局部倒置就可以了
        // 这里的非局部倒置指的是 nums[i] > nums[j]，其中 i < j - 1
        // 朴素的判断方法需要两层循环，设 n 是 nums 的长度，那么该方法的时间复杂度为 O(n^2)，无法通过
        // 进一步的，对于每一个 nums[i] 判断是否存在一个 j > i + 1 使得 nums[i] > nums[j] 即可
        // 这和检查 nums[i] > min(nums[i + 2], … ,nums[n − 1]) 是否成立是一致的
        // 因此维护一个变量 minSuffix = min(nums[i + 2], … , nums[n − 1])
        // 倒序遍历 [0, n-3] 中的每个 i，如有一个 i 使得 nums[i] > minSuffix 成立，返回 false
        // 若结束遍历都没有返回 false，则返回 true

        int n = nums.length;
        // minSuff 维护从当前位置到 (nums.length - 1) 位置的最小值
        int minSuff = nums[n - 1];

        for (int i = n - 3; i >= 0; i--) {

            if (nums[i] > minSuff)
                return false;

            minSuff = Math.min(minSuff, nums[i + 1]);
        }

        return true;
    }
}
