package com.bobking.leetcode.training;

import java.util.LinkedList;

public class Number1438 {

    // 参考：程序猿代码指南P31
    public int longestSubarray(int[] nums, int limit) {

        if (nums == null || nums.length < 1 || limit < 0)
            return 0;

        // 最大窗口
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        // 最小窗口
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        int i = 0;
        int j = 0;
        int res = 0;
        int length = 0;

        while (i < nums.length) {
            while (j < nums.length) {

                if (qmin.isEmpty() || qmin.peekLast() != j) {

                    while (!qmin.isEmpty() && nums[qmin.peekLast()] >= nums[j])
                        qmin.pollLast();
                    qmin.addLast(j);

                    while (!qmax.isEmpty() && nums[qmax.peekLast()] <= nums[j])
                        qmax.pollLast();
                    qmax.addLast(j);
                }
                if (nums[qmax.peekFirst()] - nums[qmin.peekFirst()] > limit)
                    break;
                j++;
            }

            length = j - i;
            res = Math.max(res, length);

            if (qmin.peekFirst() == i)
                qmin.pollFirst();
            if (qmax.peekFirst() == i)
                qmax.pollFirst();
            i++;
        }

        return res;
    }
}
