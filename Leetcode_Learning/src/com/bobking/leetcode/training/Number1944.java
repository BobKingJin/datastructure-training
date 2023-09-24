package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

public class Number1944 {

    public int[] canSeePersonsCount(int[] heights) {

        int n = heights.length;
        int[] ans = new int[n];
        // 单调递减栈，保存右边的可能看到的人的下标值
        Deque<Integer> stack = new ArrayDeque<Integer>();
        // 倒序遍历 能看到多少只与右边的数值有关系
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty()) {
                ans[i]++;
                // 比单调栈中当前值小，证明看不到这个值右边的了，结束比较
                if (heights[i] <= heights[stack.peek()])
                    break;

                // 比单调栈中的当前值大，左边的人肯定都看不到这个值之后的人了
                // 出栈，接着循环
                stack.pop();
            }
            // 单调栈中放入下标，保证单调递减（栈为空或者 h[i] < h[j]）
            stack.push(i);
        }

        return ans;
    }
}
