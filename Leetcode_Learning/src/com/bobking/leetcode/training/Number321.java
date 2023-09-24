package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @author BobKing
 * @create 2021-06-19 9:16
 */
public class Number321 {

    // 参考：https://leetcode-cn.com/problems/create-maximum-number/solution/pin-jie-zui-da-shu-by-leetcode-solution/
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return (nums1 == null || nums1.length == 0) ? nums2 : nums1;

        int m = nums1.length;
        int n = nums2.length;

        int[] res = new int[k];
        // 数组 nums1 中的开始位置和结束位置
        int start = Math.max(0, k - n);
        int end = Math.min(k, m);

        for (int i = start; i <= end; i++) {
            // 得到数组 nums1 和 nums2 中的最大子序列
            int[] subSequence1 = maxSubSequence1(nums1, i);
            int[] subSequence2 = maxSubSequence1(nums2, k - i);

            int[] curMaxSubsequence = merge(subSequence1, subSequence2);

            if (compare(curMaxSubsequence, 0, res, 0) > 0)
                System.arraycopy(curMaxSubsequence, 0, res, 0, k);
        }

        return res;
    }

    // 求数组中 k 个顺序不变的最大子序列
    // 栈顶到栈底：小 -> 大
    private int[] maxSubSequence1(int[] nums, int k) {

        int length = nums.length;
        // 单调栈使用数组实现，数组最左侧为栈底
        // 使用数组实现，可以直接从左到右遍历数组得到最大子序列
        int[] stack = new int[k];
        // 栈顶角标
        int top = -1;
        // 代表最多可以丢弃几个数
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }

            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    // 求数组中 k 个顺序不变的最大子序列
    // 单调栈
    private int[] maxSubSequence2(int[] nums, int k) {

        int len = nums.length;
        if (len <= k)
            return nums;

        // 代表最多可以丢弃几个数
        int drop = len - k;
        int[] ans = new int[k];
        if (k == 0)
            return ans;

        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < len; i++) {
            while (!stack.empty() && nums[i] > stack.peek() && drop-- > 0)
                stack.pop();

            stack.push(nums[i]);
        }

        // 裁剪大小
        while (stack.size() > k)
            stack.pop();

        for (int i = k - 1; i >= 0; i--)
            ans[i] = stack.pop();

        return ans;
    }

    // 归并排序
    private int[] merge(int[] subSequence1, int[] subSequence2) {

        int len1 = subSequence1.length;
        int len2 = subSequence2.length;

        if (len1 == 0)
            return subSequence2;
        if (len2 == 0)
            return subSequence1;

        int mergeLength = len1 + len2;
        int[] merged = new int[mergeLength];
        int index1 = 0;
        int index2 = 0;

        for (int i = 0; i < mergeLength; i++) {
            if (compare(subSequence1, index1, subSequence2, index2) > 0) {
                merged[i] = subSequence1[index1++];
            } else {
                merged[i] = subSequence2[index2++];
            }
        }

        return merged;
    }

    // 比较两个数的大小
    private int compare(int[] subSequence1, int index1, int[] subSequence2, int index2) {

        int len1 = subSequence1.length;
        int len2 = subSequence2.length;
        while (index1 < len1 && index2 < len2) {

            int difference = subSequence1[index1] - subSequence2[index2];
            if (difference != 0)
                return difference;

            index1++;
            index2++;
        }

        return (len1 - index1) - (len2 - index2);
    }

}
