package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Number907 {

    private Integer MOD = 1000000007;

    public int sumSubarrayMins1(int[] arr) {

        // 本质在于找到数组中的每一个数作为最小值的范围，比如对于某个数 nums[i] 能够最小值以这种形式表示
        // 左边连续 m 个数比 nums[i] 大，右边连续 n 个数比 nums[i] 大
        // 目标是找到每一个数 nums[i] 的左右两边第一个小于等于的数 nums[prev]，这两个数之间的数则均是大于 nums[i]
        // 这样便可以计算出 m 和 n

        Stack<Integer> stack = new Stack<Integer>();
        // 哨兵，保证栈中所有元素都会被弹出计算
        int[] help = new int[arr.length + 1];
        help[help.length - 1] = 0;
        for(int i = 0; i < arr.length; i++)
            help[i] = arr[i];

        int len = help.length;
        long res = 0;

        for (int i = 0; i < len; ++i) {

            while (!stack.isEmpty() && help[i] <= help[stack.peek()]) {
                
                int index = stack.peek(); 
                stack.pop();
                // 初始化 -1
                int preIndex = -1;
                
                if (!stack.isEmpty())
                    preIndex = stack.peek();
                // 数量 m
                int prevCount = index - preIndex - 1;
                // 数量 n
                int nextCount = i - index - 1;
                res += (long)((help[index]) * (prevCount + 1) * (nextCount + 1)) % MOD;
                res %= MOD;
            }

            stack.push(i);
        }

        return (int) res;
    }

    public int sumSubarrayMins2(int[] arr) {

        // 处理边界情况
        if (arr == null || arr.length == 0)
            return 0;

        int n = arr.length;
        // 每个元素辐射范围的左边界
        int[] left = new int[n];
        // 每个元素辐射范围的右边界
        int[] right = new int[n];
        Deque<Integer> stack = new LinkedList<Integer>();

        // 第一次循环先找到所有元素的左边界
        for (int i = 0; i < n; i++) {
            // 向左找第一个小于等于的元素
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i])
                stack.pop();

            // 设立一个最左边界-1
            if (stack.isEmpty()) {
                left[i] = -1;
            } else {
                left[i] = stack.peek();
            }

            stack.push(i);
        }

        // 第二次循环找到所有元素的右边界
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                stack.pop();

            if (stack.isEmpty()) {
                right[i] = n;
            } else {
                right[i] = stack.peek();
            }

            stack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++)
            ans = (ans + (long) (i - left[i]) * (right[i] - i) * arr[i]) % MOD;

        return (int) ans;
    }
}
