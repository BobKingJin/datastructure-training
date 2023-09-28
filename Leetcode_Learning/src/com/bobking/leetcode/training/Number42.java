package com.bobking.leetcode.training;

import java.util.Stack;

public class Number42 {

    // 参考：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
    public int trap1(int[] height) {

        int sum = 0;
        // 找到最大的高度，以便遍历
        int max = getMax(height);
        for (int i = 1; i <= max; i++) {
            // 标记是否开始更新 temp
            // 第一次遇到高度大于等于 i 的时候，开始更新 temp
            boolean isStart = false;
            // tmp记录符合条件，可以接雨水的两个柱子之间的总雨水的值
            int tempSum = 0;
            // 找到每根柱子左边和右边离其最近的大于该柱子高度的柱子
            for (int j = 0; j < height.length; j++) {
                if (isStart && height[j] < i)
                    tempSum++;
                if (height[j] >= i) {
                    sum = sum + tempSum;
                    tempSum = 0;
                    isStart = true;
                }
            }
        }

        return sum;
    }

    private int getMax(int[] height) {

        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max)
                max = height[i];
        }
        return max;
    }

    // 参考：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
    public int trap2(int[] height) {

        int sum = 0;
        // 最两端的列不用考虑，因为一定不会有水，所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            int maxLeft = 0;
            // 找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > maxLeft)
                    maxLeft = height[j];
            }
            int maxRight = 0;
            // 找出右边最高
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > maxRight)
                    maxRight = height[j];
            }
            // 找出两端较小的
            int min = Math.min(maxLeft, maxRight);
            // 只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    // 参考：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
    public int trap3(int[] height) {

        int sum = 0;
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        // 第 i 列左或右边最高的墙，是不包括自身的
        for (int i = 1; i < height.length - 1; i++)
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);

        for (int i = height.length - 2; i >= 0; i--)
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);

        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (min > height[i])
                sum = sum + (min - height[i]);
        }
        return sum;
    }

    // 参考：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
    public int trap4(int[] height) {

        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        // 加右指针进去
        int right = height.length - 2;
        for (int i = 1; i < height.length - 1; i++) {
            // 从左到右更
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;
                // 从右到左更
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

    // 参考：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
    // 单调栈
    public int trap5(int[] height) {

        int sum = 0;
        Stack<Integer> stack = new Stack<Integer>();
        int current = 0;

        while (current < height.length) {
            // 如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            // 注意这里不能取等
            // 栈顶 -> 栈底  小 -> 大
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                // 取出要出栈的元素
                int h = height[stack.peek()];
                // 出栈
                stack.pop();
                // 栈空就出去
                if (stack.empty())
                    break;

                // 两堵墙之前的距离
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                sum += distance * (min - h);
            }
            // 当前指向的墙入栈
            stack.push(current);
            // 指针后移
            current++;
        }

        return sum;
    }


}
