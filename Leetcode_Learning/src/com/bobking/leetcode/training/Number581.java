package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Stack;

public class Number581 {

    // 参考：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leetcode/
    public int findUnsortedSubarray1(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int prev = Integer.MIN_VALUE;

                for (int k = i; k <= j; k++) {
                    min = Math.min(min, nums[k]);
                    max = Math.max(max, nums[k]);
                }

                if ((i > 0 && nums[i - 1] > min) || (j < nums.length - 1 && nums[j + 1] < max)) {
                    continue;
                }

                int m = 0;
                while (m < i && prev <= nums[m]) {
                    prev = nums[m];
                    m++;
                }
                if (m != i) {
                    continue;
                }

                m = j + 1;
                while (m < nums.length && prev <= nums[m]) {
                    prev = nums[m];
                    m++;
                }

                if (m == nums.length) {
                    res = Math.min(res, j - i + 1);
                }
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leetcode/
    // 思路类似于 程序猿代码指南P371
    public int findUnsortedSubarray2(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        int l = nums.length;
        int r = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    l = Math.min(l, i);
                    r = Math.max(r, j);
                }
            }
        }

        return l > r ? 0 : r - l + 1;
    }

    // 参考：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leetcode/
    public int findUnsortedSubarray3(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        int[] copy = nums.clone();
        Arrays.sort(copy);
        int l = nums.length;
        int r = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != copy[i]) {
                l = Math.min(l, i);
                r = Math.max(r, i);
            }
        }

        return l > r ? 0 : r - l + 1;
    }

    // 参考：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leetcode/
    public int findUnsortedSubarray4(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int l = nums.length;
        int r = -1;

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                l = Math.min(l, stack.pop());
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                r = Math.max(r, stack.pop());
            }
            stack.push(i);
        }

        return l > r ? 0 : r - l + 1;
    }

    // 参考：程序猿代码指南P371
    public int findUnsortedSubarray5(int[] nums) {

        if (nums == null || nums.length < 1) {
            return 0;
        }

        int noMinIndex = -1;
        int min = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > min) {
                noMinIndex = i;
            } else {
                // 从 i - (nums.length - 1) 的最小值
                min = Math.min(min, nums[i]);
            }
        }

        if (noMinIndex == -1) {
            return 0;
        }

        int noMaxIndex = -1;
        int max = nums[0];
        for (int i = 1; i <= nums.length - 1; i++) {
            if (nums[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, nums[i]);
            }
        }

        return noMaxIndex - noMinIndex + 1;
    }

}
