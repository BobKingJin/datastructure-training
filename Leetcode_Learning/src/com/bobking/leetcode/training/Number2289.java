package com.bobking.leetcode.training;

import java.util.ArrayDeque;

/**
 * @Date: 2024/3/17 23:16
 * @Author: BobKing
 * @Description:
 */
public class Number2289 {

    // 参考: https://leetcode.cn/problems/steps-to-make-array-non-decreasing/solutions/1524614/by-endlesscheng-s2yc/
    public int totalSteps(int[] nums) {

        int ans = 0;
        ArrayDeque<int[]> st = new ArrayDeque<int[]>();

        for (int num : nums) {
            int maxT = 0;
            while (!st.isEmpty() && st.peek()[0] <= num)
                maxT = Math.max(maxT, st.pop()[1]);
            maxT = st.isEmpty() ? 0 : maxT + 1;
            ans = Math.max(ans, maxT);
            st.push(new int[]{num, maxT});
        }
        return ans;
    }
}
