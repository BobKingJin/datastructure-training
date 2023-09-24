package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-20 8:13
 */
public class Number2275 {

    // 参考：https://leetcode.cn/problems/largest-combination-with-bitwise-and-greater-than-zero/solution/by-hu-li-hu-wai-6kum/-
    public int largestCombination(int[] candidates) {

        int[] cnt = new int[32];
        int max = 0;
        for (int c : candidates) {
            for (int i = 0; i < 32; i++) {
                if (((1 << i) & c) > 0)
                    cnt[i]++;
            }
        }
        for (int i = 0; i < 32; i++)
            max = Math.max(max, cnt[i]);

        return max;
    }
}
