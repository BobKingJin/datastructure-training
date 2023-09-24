package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2023-05-03 7:52
 */
public class Number1498 {

    private final int MOD = (int)1e9+7;
    private long res = 0;

    // 参考：https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/solution/python-pai-xu-shuang-zhi-zhen-by-irruma/
    public int numSubseq1(int[] nums, int target) {
        Arrays.sort(nums);
        backtrack(nums, 0,  new ArrayDeque<Integer>(), target);
        return (int) res % MOD;
    }

    private void backtrack(int[] nums, int idx, Deque<Integer> path, int target) {

        if (path.size() > 0) {
            int min = Collections.min(path);
            int max = Collections.max(path);
            if (min + max <= target) {
                res += 1;
            } else {
                return;
            }
        }

        for (int i = idx; i < nums.length; i++) {
            if (!path.isEmpty() && path.getFirst() + nums[i] > target)
                break;
            path.addLast(nums[i]);
            backtrack(nums, i + 1, path, target);
            path.removeLast();
        }
    }

    // 参考：https://leetcode.cn/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/solution/man-zu-tiao-jian-de-zi-xu-lie-shu-mu-by-leetcode-s/
    public int numSubseq2(int[] nums, int target) {

        Arrays.sort(nums);
        int n = nums.length;
        int[] tmp = new int[n];
        tmp[0] = 1;
        for (int i = 1; i < n; i++)
            tmp[i] = (tmp[i - 1] << 1) % MOD;

        int res = 0;
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else {
                res = (res + tmp[r - l]) % MOD;
                l++;
            }
        }
        return res;
    }
}
