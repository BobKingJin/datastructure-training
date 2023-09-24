package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2022-09-09 16:22
 */
public class Number456 {

    // 参考：https://leetcode.cn/problems/132-pattern/solution/xiang-xin-ke-xue-xi-lie-xiang-jie-wei-he-95gt/
    public boolean find132pattern(int[] nums) {

        int n = nums.length;
        Deque<Integer> d = new ArrayDeque<Integer>();
        // 使用 k 记录所有出栈元素的最大值
        int k = Integer.MIN_VALUE;

        // 从后往前
        for (int i = n - 1; i >= 0; i--) {

            if (nums[i] < k)
                return true;

            while (!d.isEmpty() && d.peekLast() < nums[i])
                // 事实上，k 的变化也具有单调性，直接使用 k = pollLast() 也是可以的
                k = Math.max(k, d.pollLast());

            d.addLast(nums[i]);
        }

        return false;
    }
}
