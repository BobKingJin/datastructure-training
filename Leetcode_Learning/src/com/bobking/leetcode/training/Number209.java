package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number209 {

    // 参考：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
    public int minSubArrayLen1(int target, int[] nums) {

        // 前缀和：sums[i] 表示从 nums[0] 到 nums[i − 1] 的元素和
        // 对于每个开始下标 i，可通过二分查找得到大于或等于 i 的最小下标 bound，使得 sums[bound] − sums[i − 1] ≥ target
        // 并更新子数组的最小长度（此时子数组的长度是 bound − (i − 1)）

        // 数组中每个元素都为正，所以前缀和一定是递增的，这一点保证了二分的正确性。如果没有说明数组中每个元素都为正
        // 这里就不能使用二分来查找这个位置了

        int n = nums.length;
        if (n == 0)
            return 0;
        
        int res = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        for (int i = 1; i <= n; i++)
            sums[i] = sums[i - 1] + nums[i - 1];

        for (int i = 1; i <= n; i++) {

            int count = target + sums[i - 1];

            // 若 count 在 sums 中不存在，则 bound 为 -本应该插入的位置 - 1
            // 例如 int[] arr = {1, 3, 5, 8, 10}  count = 11 则 bound = -6
            //                                   count = 6 则 bound = -4
            // 即返回的是 大于或等于 count 的最小下标 
            int bound = Arrays.binarySearch(sums, count);
            // 因为题目可以保证必定存在一个子数组的和等于 target，即 nums[i] ... nums[j] 之和等于 target
            // 那么利用二分找到大于或等于 count，而不必要求必须等于，因为即使当前大于 count
            // 然后 Math.min(res, bound - (i - 1))对 res 做了更新(尽管此时 res 不正确)，但是因为之前或后续肯定存在
            // 累加和是等于 target 的，那么最终结果肯定是正确的
            if (bound < 0)
                bound = -bound - 1;

            if (bound <= n)
                res = Math.min(res, bound - (i - 1));
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // 参考：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
    public int minSubArrayLen2(int target, int[] nums) {

        int n = nums.length;
        if (n == 0)
            return 0;

        int res = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= target) {
                res = Math.min(res, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
