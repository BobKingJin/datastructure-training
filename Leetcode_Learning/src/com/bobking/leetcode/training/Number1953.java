package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-02-14 11:19
 */
public class Number1953 {

    // 参考：https://leetcode.cn/problems/maximum-number-of-weeks-for-which-you-can-work/solution/ezi-zai-fei-hua-e-bi-jiao-hao-li-jie-de-8in32/
    public long numberOfWeeks(int[] milestones) {

        int[] ms = milestones;
        long total = 0;
        for (int x : ms)
            total += x;

        Arrays.sort(ms);
        long max = ms[ms.length - 1];
        if (total - max < max)
            return (total - max) * 2 + 1;

        return total;
    }
}
