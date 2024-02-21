package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.List;

/**
 * @Date: 2024/2/20 23:57
 * @Author: BobKing
 * @Description:
 */
public class Number2865 {

    // 参考: https://leetcode.cn/problems/beautiful-towers-i/solutions/2456565/on-qian-hou-zhui-fen-jie-dan-diao-zhan-p-w3g0/?envType=daily-question&envId=2024-02-20
    public long maximumSumOfHeights(List<Integer> maxHeights) {

        int[] a = maxHeights.stream().mapToInt(i -> i).toArray();
        int n = a.length;
        long[] suf = new long[n + 1];
        ArrayDeque<Integer> st = new ArrayDeque<Integer>();
        // 哨兵
        st.push(n);
        long sum = 0;

        for (int i = n - 1; i >= 0; i--) {
            int x = a[i];
            while (st.size() > 1 && x <= a[st.peek()]) {
                int j = st.pop();
                // 撤销之前加到 sum 中的
                sum -= (long) a[j] * (st.peek() - j);
            }
            // 从 i 到 st.peek() - 1 都是 x
            sum += (long) x * (st.peek() - i);
            suf[i] = sum;
            st.push(i);
        }

        long ans = sum;
        st.clear();
        // 哨兵
        st.push(-1);
        long pre = 0;

        for (int i = 0; i < n; i++) {
            int x = a[i];
            while (st.size() > 1 && x <= a[st.peek()]) {
                int j = st.pop();
                // 撤销之前加到 pre 中的
                pre -= (long) a[j] * (j - st.peek());
            }
            // 从 st.peek() + 1 到 i 都是 x
            pre += (long) x * (i - st.peek());
            ans = Math.max(ans, pre + suf[i + 1]);
            st.push(i);
        }
        return ans;
    }
}
