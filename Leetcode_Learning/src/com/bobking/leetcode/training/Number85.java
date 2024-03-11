package com.bobking.leetcode.training;

import java.util.Stack;

public class Number85 {

    // 参考：程序猿代码指南P26
    public int maximalRectangle(char[][] matrix) {

        if (matrix == null || matrix.length == 0)
            return 0;

        int[] height = new int[matrix[0].length];
        int res = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            res = Math.max(res, maxRecFromBottom(height));
        }
        return res;
    }

    private int maxRecFromBottom(int[] height) {

        if (height == null || height.length == 0)
            return 0;

        int maxArea = 0;

        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                // 注意当 stack 为空的时候，默认左边的角标为 -1
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            // 默认左边的角标为 heights.length
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
}
