package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2022-09-09 15:08
 */
public class Number946 {

    // 参考：https://leetcode.cn/problems/validate-stack-sequences/solution/by-ac_oier-84qd/
    public boolean validateStackSequences1(int[] pushed, int[] popped) {

        Deque<Integer> d = new ArrayDeque<Integer>();

        for (int i = 0, j = 0; i < pushed.length; i++) {
            d.addLast(pushed[i]);
            while (!d.isEmpty() && d.peekLast() == popped[j] && ++j >= 0)
                d.pollLast();
        }

        return d.isEmpty();
    }

    // 参考：https://leetcode.cn/problems/validate-stack-sequences/solution/by-ac_oier-84qd/
    public boolean validateStackSequences2(int[] pushed, int[] popped) {

        int n = pushed.length;
        int idx = 0;

        for (int i = 0, j = 0; i < n; i++) {
            pushed[idx++] = pushed[i];
            while (idx > 0 && pushed[idx - 1] == popped[j] && ++j >= 0)
                idx--;
        }

        return idx == 0;
    }


}
