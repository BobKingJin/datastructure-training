package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author BobKing
 * @create 2023-02-14 10:45
 */
public class Number1124 {

    // 参考：https://leetcode.cn/problems/longest-well-performing-interval/solution/liang-chong-zuo-fa-liang-zhang-tu-miao-d-hysl/
    public int longestWPI(int[] hours) {

        // 前缀和
        int[] sum = new int[hours.length + 1];
        // 单调栈 保存单调递减的前缀和的下标
        Deque<Integer> stack = new LinkedList<Integer>();
        // sum[0] 0
        stack.push(0);
        for (int i = 1; i <= hours.length; i++) {
            sum[i] = sum[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (sum[i] < sum[stack.peek()])
                stack.push(i);
        }
        int ans = 0;
        for (int i = hours.length; i > 0; i--) {
            while (!stack.isEmpty() && sum[i] > sum[stack.peek()])
                ans = Math.max(ans, i - stack.pop());
        }
        return ans;
    }
}
