package com.bobking.leetcode.training;

import java.util.LinkedList;

public class Number239 {

    // 参考：程序猿代码指南P18
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || k < 1 || nums.length < k) {
            return null;
        }

        int[] res = new int[nums.length - k + 1];
        // 插入数组中的角标位置
        int index = 0;
        // 双端队列
        LinkedList<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            // 判断队头角标是否过期
            if (queue.peekFirst() == i - k) {
                queue.pollFirst();
            }
            // 如果形成了一个窗口
            if (i >= k - 1) {
                res[index++] = nums[queue.peekFirst()];
            }
        }

        return res;
    }
}
