package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Date: 2024/3/19 23:32
 * @Author: BobKing
 * @Description:
 */
public class Number1793 {

    // 参考: https://leetcode.cn/problems/maximum-score-of-a-good-subarray/solutions/2695415/liang-chong-fang-fa-dan-diao-zhan-shuang-24zl/?envType=daily-question&envId=2024-03-19
    public int maximumScore(int[] nums, int k) {

        int n = nums.length;
        int[] left = new int[n];
        Deque<Integer> st = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!st.isEmpty() && x <= nums[st.peek()])
                st.pop();
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        int[] right = new int[n];
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            while (!st.isEmpty() && x <= nums[st.peek()])
                st.pop();
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int h = nums[i];
            int l = left[i];
            int r = right[i];
            if (l < k && k < r)
                ans = Math.max(ans, h * (r - l - 1));
        }
        return ans;
    }
}
