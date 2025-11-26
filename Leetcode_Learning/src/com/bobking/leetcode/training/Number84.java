package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author BobKing
 * @create 2021-05-28 23:11
 */
public class Number84 {

    // 参考：程序猿代码指南P26
    // 同时参考Number85
    // 单调栈
    public int largestRectangleArea1(int[] heights) {

        if (heights == null || heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();

        // 以当前柱子向左右扩展的最大矩形面积
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int j = stack.pop();
                // 注意当 stack 为空的时候，默认左边的角标为 -1
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - 1 - k) * heights[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            // 默认左边的角标为 heights.length
            int curArea = (heights.length - 1 - k) * heights[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    // 参考：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/
    // 解法中的哨兵解法，即在输入数组的两端加上两个高度为 0 （或者是 0.5，只要比 1 严格小都行）的柱形
    public int largestRectangleArea2(int[] heights) {

        if (heights == null || heights.length == 0) {
            return 0;
        }

        if (heights.length == 1) {
            return heights[0];
        }

        int res = 0;
        int length = heights.length;

        int[] newHeights = new int[length + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, length);
        newHeights[length + 1] = 0;
        length += 2;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<Integer>(length);
        // 先放入哨兵，在循环里就不用做非空判断
        stack.addLast(0);

        for (int i = 1; i < length; i++) {
            while (heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
    }

}
