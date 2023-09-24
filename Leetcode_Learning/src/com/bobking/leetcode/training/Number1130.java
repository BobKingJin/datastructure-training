package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @author BobKing
 * @create 2023-05-10 6:55
 */
public class Number1130 {

    // 参考：https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values/solution/wei-shi-yao-dan-diao-di-jian-zhan-de-suan-fa-ke-xi/
    public int mctFromLeafValues(int[] arr) {

        Stack<Integer> st = new Stack<Integer>();
        st.push(Integer.MAX_VALUE);
        int mct = 0;

        for (int i = 0; i < arr.length; i++) {

            while (arr[i] >= st.peek())
                mct += st.pop() * Math.min(st.peek(), arr[i]);

            st.push(arr[i]);
        }

        while (st.size() > 2)
            mct += st.pop() * st.peek();

        return mct;
    }
}
