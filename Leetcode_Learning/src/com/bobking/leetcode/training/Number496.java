package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Number496 {

    // 参考：程序猿代码指南P20的单调栈结构
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || nums2.length < nums1.length)
            return nums1;

        // 单调栈：从栈顶到栈底 从小到大
        Stack<Integer> stack = new Stack<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        // nums1 和 nums2 中所有整数互不相同，这点很重要
        for (int i = 0; i < nums2.length; i++) {

            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {

                int popIndex = stack.pop();
                map.put(nums2[popIndex], nums2[i]);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            map.put(nums2[popIndex], -1);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++)
            res[i] = map.get(nums1[i]);

        return res;
    }
}
