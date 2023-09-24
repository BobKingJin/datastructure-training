package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @author BobKing
 * @create 2021-07-24 12:29
 */
public class Number1673 {

    // 不是简单地用小根堆取前 K 大的数
    // 参考：https://leetcode-cn.com/problems/find-the-most-competitive-subsequence/solution/java-dan-diao-zhan-by-thedesalizes/
    public int[] mostCompetitive(int[] nums, int k) {

        int[] res = new int[k];

        if (nums == null || nums.length == 0 || k < 0)
            return res;

        Stack<Integer> stack = new Stack<Integer>();
        // 后面栈就不需要判空，因为 nums[i] >= 0，所以栈永远不为空
        stack.add(-1);

        // 单调栈 大 -> 小
        for (int i = 0; i < nums.length; i++) {
            // 当前元素比栈顶元素小，将栈顶元素出栈
            // 此处需要另外判断数组剩余长度够不够填满栈，不然最后答案长度可能会小于 k
            while (nums[i] < stack.peek() && k - stack.size() < nums.length - i - 1)
                stack.pop();
            if (stack.size() < k + 1)
                stack.add(nums[i]);
        }

        while (k > 0)
            res[--k] = stack.pop();

        return res;
    }
}
